public class Netz {

    public static void main(String[] args){
        new Netz(3, 3).run();
    }

    private int inputLayerAmout;
    private int outputLayerAmout;
    private int layersAmount = 2;
    private int hiddenNeuronAmount = 2;
    private Layer layers;

    Netz(int inputLayerAmout, int outputLayerAmout){
        this.inputLayerAmout = inputLayerAmout;
        this.outputLayerAmout = outputLayerAmout;
    }

    public void run(){
        layers = new Layer(inputLayerAmout, outputLayerAmout, hiddenNeuronAmount, layersAmount);
        layers.setInputs(new double[]{20, 0, 300});
        for (int i =0; i < 1000; i++){
            layers.run();
            layers.learn();
        }
    }

}
