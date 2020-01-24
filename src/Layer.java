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

    public Layer(Netz netz, LayerType layerType){
        this.netz = netz;
        this.layerType = layerType;
    }

    private void setInputs(double[] inputs){
        if (inputs.length != neurons.length){
            throw new IllegalArgumentException("müssen gleich lang sein");
        } else {
            for (int i = 0; i < inputs.length; i++){
                neurons[i].setValue(inputs[i]);
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
            nextLayer.neurons[nextLayerNeuronIndex].setValue(Util.calcSigmoid(currentValue));
        }
    }

    private void backtrackError(){
        for(int neuronIndex = 0; neuronIndex < neurons.length; neuronIndex++){
            Neuron neuron = neurons[neuronIndex];
            if (nextLayer == null){
                neuron.setError((neuron.getValue() - netz.target[neuronIndex]) * neuron.valueDerivative());
            } else {
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
                for (int nextNeuronIndex = 0; nextNeuronIndex < nextLayer.neurons.length; nextNeuronIndex++) {
                    neuron.getWeights()[nextNeuronIndex] += (-netz.trainingsRate) * nextLayer.neurons[nextNeuronIndex].getError();
                }
            }
            nextLayer.learn();
        }
    }
}
