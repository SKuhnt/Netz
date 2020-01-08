import java.util.Arrays;
import java.util.Map;

public class Netz {

    public static void main(String[] args){
        Netz n1 = new Netz();
        n1.train(new TrainingData().inputOutputMap, 10000);

        System.out.println(Arrays.toString(n1.run(new double[]{1, 1})));
        System.out.println(Arrays.toString(n1.run(new double[]{0, 0})));
        System.out.println(Arrays.toString(n1.run(new double[]{1, 0})));
        System.out.println(Arrays.toString(n1.run(new double[]{0, 1})));

//        System.out.println(Arrays.toString(n1.run(new double[]{0.5, 0.25})));
//        System.out.println(Arrays.toString(n1.run(new double[]{0.25, 0.25})));
//        System.out.println(Arrays.toString(n1.run(new double[]{0.1, 0.1})));
//        System.out.println(Arrays.toString(n1.run(new double[]{0.1, 0.21})));
    }

    public double trainingsRate = 0.15;
    public double[] target;
    public int inputLayerNeuron = 2;
    public int outputLayerNeuron = 1;
    public int hiddenLayerAmount = 5;
    public int hiddenLayerNeuron = 7;
    public Layer inputLayer;
    //public Layer outputlayer;

    public Netz(){
        createLayers();
    }

    public double[] run(double[] input){
        return inputLayer.run(input, false);
    }

    public void train(Map<double[], double[]> inputOutputMap, int numberOfTrainings){
        for (int i = 0; i < numberOfTrainings; i++) {
            double error = 0;
            for (Map.Entry<double[], double[]> trainingData : inputOutputMap.entrySet()) {
                this.target = trainingData.getValue();
                double[] tmp = inputLayer.run(trainingData.getKey(), true);

                //((OutputLayer)outputlayer).calcAndSetOutputError(trainingData.getValue());

                for(int t = 0; t<target.length;t++){
                    error += Math.pow(tmp[t]-target[t],2.0);
                }


            }
//            ((OutputLayer)outputlayer).finalizeBatch(numberOfTrainings);

            inputLayer.applyLearned();
            inputLayer.resetValuesAndErrors();

            error = error/inputOutputMap.entrySet().size();
            if(error<0.001) {
                System.out.println("Error left: "+error);
                return;
            }
            System.out.println(i);
            System.out.println(error);
        }
    }

    private void createLayers(){
        Layer nextLayer = createLayer(outputLayerNeuron, null, LayerType.Output);
        //this.outputlayer = nextLayer;
        for(int i = 0; i < hiddenLayerAmount; i++){
            nextLayer = createLayer(hiddenLayerNeuron, nextLayer, LayerType.Hidden);
        }
        this.inputLayer = createLayer(inputLayerNeuron, nextLayer, LayerType.Input);
    }

    private Layer createLayer(int inputNeurons, Layer nextLayer, LayerType layerType){
        Layer layer;
//        if(layerType==LayerType.Output){
//            layer = new OutputLayer(this, layerType);
//        }
//        else{
            layer = new Layer(this, layerType);
//        }
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
