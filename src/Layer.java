import java.util.Arrays;

public class Layer {

    private Neuron[] neurons;
    private Layer nextLayer;
    private Layer prevLayer;

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
        if(nextLayer == null){
            for (Neuron neuron : neurons){
                System.out.println(neuron.getValue());
            }
        } else{
            calculateNextValues();
            nextLayer.run();
            //backtrack
            int winnerNumber = 0;
            for(int i = 0; i < neurons.length; i++){
                double[] nextValues = neurons[i].calculateNextValues();
                neurons[i].getWeights();
                neurons[i].getValue();
                for(int nextLayerNeuronIndex = 0; nextLayerNeuronIndex < nextLayer.neurons.length; nextLayerNeuronIndex++){
                    neurons[i].calculateNextValue(nextLayerNeuronIndex);
                }
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


}
