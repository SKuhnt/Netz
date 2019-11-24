import java.util.Arrays;
import java.util.Map;

public class Netz {

    public static void main(String[] args){
        Netz n1 = new Netz();
        n1.train(new TrainingData().inputOutputMap, 10000);
        System.out.println(Arrays.toString(n1.run(new double[]{0.5, 0.25})));
        System.out.println(Arrays.toString(n1.run(new double[]{0.25, 0.25})));
        System.out.println(Arrays.toString(n1.run(new double[]{0.1, 0.1})));


        System.out.println(Arrays.toString(n1.run(new double[]{0.1, 0.1})));
    }

    public double trainingsRate = 0.61;
    public double[] target;
    public int inputLayerNeuron = 2;
    public int outputLayerNeuron = 1;
    public int hiddenLayerAmount = 3;
    public int hiddenLayerNeuron = 5;
    public Layer inputLayer;

    public Netz(){
        createLayers();
    }

    public double[] run(double[] input){
        return inputLayer.run(input, false);
    }

    public void train(Map<double[], double[]> inputOutputMap, int numberOfTrainings){
        for (int i = 0; i < numberOfTrainings; i++) {
            for (Map.Entry<double[], double[]> trainingData : inputOutputMap.entrySet()) {
                this.target = trainingData.getValue();
                inputLayer.run(trainingData.getKey(), true);
            }
        }
    }

    private void createLayers(){
        Layer nextLayer = createLayer(outputLayerNeuron, null, LayerType.Output);
        for(int i = 0; i < hiddenLayerAmount; i++){
            nextLayer = createLayer(hiddenLayerNeuron, nextLayer, LayerType.Hidden);
        }
        this.inputLayer = createLayer(inputLayerNeuron, nextLayer, LayerType.Input);
    }

    private Layer createLayer(int inputNeurons, Layer nextLayer, LayerType layerType){
        Layer layer = new Layer(this, layerType);
        Neuron[] neurons = new Neuron[inputNeurons];
        if(nextLayer != null){
            Arrays.setAll(neurons, array -> new Neuron(nextLayer.getNeurons().length));
        } else {
            Arrays.setAll(neurons, array -> new Neuron(0));
        }
        layer.setNeurons(neurons);
        layer.setNextLayer(nextLayer);
        return layer;
    }

}
