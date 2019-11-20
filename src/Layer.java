import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Layer {

    private static int counter = 0;

    //private Map<Neuron, List<Neuron>> connections;
    private Neuron[] neurons;
    private Layer nextLayer;
    private Layer prevLayer;
    private boolean isOutputlayer = false;
    private int count;

    public Layer(int amount, int outputNeurons, Layer prev, Layer next){
        this.count = counter++;
        neurons = new Neuron[amount];
        nextLayer=next;
        prevLayer=prev;
        for(int i=0;i<neurons.length;i++){
            neurons[i]=new Neuron(outputNeurons,this);
        }
    }

    public void setNext(Layer next){
        this.nextLayer=next;
    }

    public void setOutPutlayer(){
        this.isOutputlayer=true;
        for(Neuron neuron:neurons){
            neuron.setOutputNeuron();
        }
    }

    public Neuron[] getNeurons(){
        return neurons;
    }

    public void setOutputLayerAmt(int i){
        for(Neuron neuron:neurons){
            neuron.setWeightsAmt(i);
        }
    }

    private void resetInputs(){
        for (int i = 0; i < neurons.length; i++){
            neurons[i].setValue(0);
            neurons[i].setError(0);
        }
    }

    public void setInputs(double[] inputs){
        if(inputs==null){
            resetInputs();
        }
        else if (inputs.length != neurons.length){
            throw new IllegalArgumentException("müssen gleich lang sein");
        } else {
            for (int i = 0; i < inputs.length; i++){
                neurons[i].setValue(inputs[i]);
            }
        }
    }

//    public void run(){
//        if(nextLayer == null){
//            for (Neuron neuron : neurons){
//                System.out.println(neuron.getValue());
//            }
//        } else{
//            calculateNextValues();
//            nextLayer.run();
//            //backtrack
//            int winnerNumber = 0;
//            for(int i = 0; i < neurons.length; i++){
//                double[] nextValues = neurons[i].calculateNextValues();
//                neurons[i].getWeights();
//                neurons[i].getValue();
//                for(int nextLayerNeuronIndex = 0; nextLayerNeuronIndex < nextLayer.neurons.length; nextLayerNeuronIndex++){
//                    neurons[i].calculateNextValue(nextLayerNeuronIndex);
//                }
//            }
//        }
//    }

    public void propagate(){
        if(!isOutputlayer){//todo: lass das array einfach eins weniger iterieren.. problem: backpropagate
            for(int nextLayerNeuronIndex = 0; nextLayerNeuronIndex < nextLayer.neurons.length; nextLayerNeuronIndex++){
                double currentValue = 0;
                for(int i = 0; i < neurons.length; i++){
                    //System.out.println("Propagate, layer: "+this.count+" next neuron: "+nextLayerNeuronIndex +" this neuron: "+i);
                    currentValue += neurons[i].calculateNextValue(nextLayerNeuronIndex);
                }
                nextLayer.neurons[nextLayerNeuronIndex].setValue(Util.calcSigmoid(currentValue));
            }
        }
    }

    public void backPropagate(){
        for(int i = 0; i < neurons.length; i++){
            neurons[i].backPropagate();
        }

    }

    public Neuron getNeuron(int i){
        return neurons[i];
    }

    public Layer getNext(){
        return nextLayer;
    }


}
