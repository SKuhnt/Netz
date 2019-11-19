public class Layer {

    private Neuron[] neurons;
    private Layer nextLayer;
    private Layer prevLayer;

    public Layer(Neuron[] neurons, Layer nextLayer, Layer prevLayer){
        this.neurons = neurons;
        this.nextLayer = nextLayer;
        this.prevLayer = prevLayer;
    }

    private void calculateNextValues(){
        double [] values = new double[nextLayer.neurons.length];

    }


}
