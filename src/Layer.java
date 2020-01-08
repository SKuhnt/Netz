import java.util.Arrays;

public class Layer {

    private Neuron[] neurons;
    private Layer nextLayer;
    private Netz netz;
    private LayerType layerType;

    public void setNeurons(Neuron[] neurons) {
        this.neurons = neurons;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    public Neuron[] getNeurons() {
        return neurons;
    }

    public LayerType getLayerType() {
        return layerType;
    }

    public Layer(Netz netz, LayerType layerType){
        this.netz = netz;
        this.layerType = layerType;
    }

    private void setInputs(double[] inputs){
        if (inputs.length != neurons.length){
            throw new IllegalArgumentException("müssen gleich lang sein");
        } else {
            for (int i = 0; i < inputs.length; i++){
                neurons[i].initiateValue(inputs[i]);//.setValue(inputs[i]);
            }
        }
    }

    public double[] run(double[] inputs, boolean isLearning){
        if(layerType.equals(LayerType.Input)){
            setInputs(inputs);
            double[] result = calculateAndBacktrack(isLearning);
            if (isLearning){
                learn();
            }
            return result;
        } else{
            throw new IllegalArgumentException("inputs can only be given to the input Layer");
        }
    }

    private double[] calculateAndBacktrack(boolean isLearning){
        double[] result = null;
        if(nextLayer != null) {
            calculateNextValues();
            result = nextLayer.calculateAndBacktrack(isLearning);
        }
        if(isLearning){
            backtrackError();
        }
        if (layerType.equals(LayerType.Output)){
            result = Arrays.stream(neurons).mapToDouble(Neuron::getValue).toArray();
        }
        return result;
    }

    private void calculateNextValues(){
        for(int nextLayerNeuronIndex = 0; nextLayerNeuronIndex < nextLayer.neurons.length; nextLayerNeuronIndex++){
            double currentValue = 0;
            for (Neuron neuron : neurons) {
                currentValue += neuron.calculateNextValue(nextLayerNeuronIndex);
            }
            nextLayer.neurons[nextLayerNeuronIndex].setValue(Util.calcSigmoid(currentValue+nextLayer.neurons[nextLayerNeuronIndex].getBias()));
        }
    }

    private void backtrackError(){
        if(nextLayer==null){//outputlayer
            for(int neuronIndex = 0; neuronIndex < neurons.length; neuronIndex++){
                Neuron neuron = neurons[neuronIndex];
                //neuron.setError(0.5*Math.pow(netz.target[neuronIndex] - neuron.getValue(),2.0));// * neuron.valueDerivative());
                //neuron.setError((netz.target[neuronIndex]-neuron.getValue()>0?-1:1)*Math.pow((netz.target[neuronIndex]-neuron.getValue()),2.0) * neuron.valueDerivative());
                neuron.setError(-(netz.target[neuronIndex]-neuron.getValue()));
                //neuron.setError(netz.target[neuronIndex]-neuron.getValue()>0?-1:1*Math.pow(netz.target[neuronIndex]-neuron.getValue(),2.0));
//                double y = netz.target[neuronIndex];
//                double a = neuron.getValue();
//                neuron.setError((y*Math.log(a)+(1-y)*Math.log(1-a) ));
            }
        }
        else{
            nextLayer.backtrackError();
            for(int neuronIndex = 0; neuronIndex < neurons.length; neuronIndex++){
                Neuron neuron = neurons[neuronIndex];
                double sum = 0;
                for (int nextNeuronIndex = 0; nextNeuronIndex < nextLayer.neurons.length; nextNeuronIndex++){
                    //SUM of: weight of this neuron towards the next neuron * the error of the next neuron
                    sum += neuron.getWeightAt(nextNeuronIndex) * nextLayer.neurons[nextNeuronIndex].getError();
                }
                double error = sum * neuron.valueDerivative();
                neuron.setError(error);
            }
        }
    }

    private void learn() {
        if (nextLayer != null){
            for (Neuron neuron : neurons) {
                double trainingsValue = -netz.trainingsRate * neuron.getValue();
                for (int nextNeuronIndex = 0; nextNeuronIndex < nextLayer.neurons.length; nextNeuronIndex++) {
                    neuron.setWeightUpdates(nextNeuronIndex,neuron.getWeightUpdates()[nextNeuronIndex] + trainingsValue * nextLayer.neurons[nextNeuronIndex].getError());
                }
                neuron.setBiasUpdate(neuron.getBiasUpdate()+-netz.trainingsRate*(neuron.getError()));
            }
            nextLayer.learn();
        }
    }

    public void applyLearned() {
        if (nextLayer != null){
            for (Neuron neuron : neurons) {
                for (int nextNeuronIndex = 0; nextNeuronIndex < nextLayer.neurons.length; nextNeuronIndex++) {
                    neuron.setWeightAt(nextNeuronIndex,neuron.getWeightAt(nextNeuronIndex)+neuron.getWeightUpdates()[nextNeuronIndex]);
                }
                neuron.setBias(neuron.getBias()+neuron.getBiasUpdate());
                neuron.resetWeightAndBiasUpdates();
            }
            nextLayer.applyLearned();
        }
    }

    public void resetValuesAndErrors(){
        for (Neuron neuron : neurons) {
            neuron.setError(0);
            neuron.setValue(0);
        }
        if (nextLayer != null){
            nextLayer.resetValuesAndErrors();
        }
    }
}
