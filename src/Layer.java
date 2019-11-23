import java.util.Arrays;

public class Layer {

    private Neuron[] neurons;
    private Layer nextLayer;
    private Layer prevLayer;
    private double trainingsRate = 0.5;

    public Layer(int inputNeurons, int outputNeurons, int hiddenNeurons, int hiddenLayers){
        if (hiddenLayers > 0){
            this.neurons = new Neuron[inputNeurons];
            this.nextLayer = new Layer(outputNeurons, hiddenNeurons, hiddenLayers, this);
            Arrays.setAll(neurons, array -> new Neuron(nextLayer.neurons.length));
        } else {
            throw new IllegalArgumentException("to few Layers");
        }
    }

    private Layer(int outputNeurons, int hiddenNeurons, int hiddenLayers, Layer prevLayer){
        this.prevLayer = prevLayer;
        if (hiddenLayers != 0){
            this.neurons = new Neuron[hiddenNeurons];
            this.nextLayer = new Layer(outputNeurons, hiddenNeurons, hiddenLayers - 1, this);
            Arrays.setAll(neurons, array -> new Neuron(nextLayer.neurons.length));
        } else {
            this.neurons = new Neuron[outputNeurons];
            Arrays.setAll(neurons, array -> new Neuron(0));
        }
    }

    public void setInputs(double[] inputs){
        if (inputs.length != neurons.length){
            throw new IllegalArgumentException("müssen gleich lang sein");
        } else {
            for (int i = 0; i < inputs.length; i++){
                neurons[i].setValue(inputs[i]);
            }
        }
    }

    public void run(){
        if(nextLayer == null) {
            System.out.println(neurons[0].getValue());
        } else {
            calculateNextValues();
            nextLayer.run();
        }
        //backtrack
        double[] y = new double[]{1, 0, 0, 0, 0};

        for(int neuronIndex = 0; neuronIndex < neurons.length; neuronIndex++){
            Neuron neuron = neurons[neuronIndex];
            if (nextLayer == null){
                neuron.setError((neuron.getValue() - y[neuronIndex]) * neuron.outoutDerivate());
            }else {
                double sum = 0;
                for (int nextNeuronIndex = 0; nextNeuronIndex < nextLayer.neurons.length; nextNeuronIndex++){
                    sum += neuron.getWeightAt(neuronIndex) * nextLayer.neurons[nextNeuronIndex].getError();
                }
                neuron.setError(sum * neuron.outoutDerivate());
            }
        }
    }

    private void calculateNextValues(){
        for(int nextLayerNeuronIndex = 0; nextLayerNeuronIndex < nextLayer.neurons.length; nextLayerNeuronIndex++){
            double currentValue = 0;
            for(int i = 0; i < neurons.length; i++){
                currentValue += neurons[i].calculateNextValue(nextLayerNeuronIndex);
            }
            nextLayer.neurons[nextLayerNeuronIndex].setValue(Util.calcSigmoid(currentValue));
        }
    }


    public void learn() {
        if (nextLayer != null){
            for(int neuronIndex = 0; neuronIndex < neurons.length; neuronIndex++) {
                Neuron neuron = neurons[neuronIndex];
                for (int nextNeuronIndex = 0; nextNeuronIndex < nextLayer.neurons.length; nextNeuronIndex++) {
                    double weightChange = -trainingsRate * neuron.getValue() * nextLayer.neurons[nextNeuronIndex].getError();
                    neuron.getWeights()[neuronIndex] += weightChange;
                }
            }
            nextLayer.learn();
        }
    }
}
