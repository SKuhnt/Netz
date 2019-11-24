public class Netz {

    public static void main(String[] args){
        new Netz(3, 1).run();
    }

    private int inputLayerAmout;
    private int outputLayerAmout;
    private int layersAmount = 3;
    private int hiddenNeuronAmount = 3;
    private Layer layers;

    Netz(int inputLayerAmout, int outputLayerAmout){
        this.inputLayerAmout = inputLayerAmout;
        this.outputLayerAmout = outputLayerAmout;
    }

    public void run(){
        layers = new Layer(inputLayerAmout, outputLayerAmout, hiddenNeuronAmount, layersAmount);
        TrainingData trainingData = new TrainingData();
        trainingData.train(layers, 10000);
        layers.setInputs(new double[]{22, 0, 300});
        System.out.println("testRun");
        layers.run();
    }

}
