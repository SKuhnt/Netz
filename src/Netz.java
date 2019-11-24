public class Netz {

    public static void main(String[] args){
        new Netz(2, 2).run();
    }

    private int inputLayerAmout;
    private int outputLayerAmout;
    private int layersAmount = 3;
    private int hiddenNeuronAmount = 5;
    private Layer layers;

    Netz(int inputLayerAmout, int outputLayerAmout){
        this.inputLayerAmout = inputLayerAmout;
        this.outputLayerAmout = outputLayerAmout;
    }

    public void run(){
        layers = new Layer(inputLayerAmout, outputLayerAmout, hiddenNeuronAmount, layersAmount);
        TrainingData trainingData = new TrainingData();
        trainingData.train(layers, 500);
        System.out.println("testRun");
        layers.setInputs(new double[]{0.5, 0.8});
        layers.run();
        layers.setInputs(new double[]{0.8, 0.5});
        layers.run();

    }

}
