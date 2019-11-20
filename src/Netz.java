import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Netz {

    public static void main(String[] args){
        Netz net = new Netz(2, 1);

        double[][] tests = {{1,1,0},{1,0,1},{0,0,0},{0,1,1}};
        Random rand = new Random();
        for(int i = 0; i<1000;i++){
            double[] set = tests[0];//tests[rand.nextInt(3)];
            double[] x = new double[2];
            x[0]=set[0];
            x[1]=set[1];

            double[] y = new double[1];
            y[0]=set[2];
            net.runBatch(x,y);
        }

//        double[] t = {1,1};
//        double[] e = {0};
//        net.runBatch(t,e);
//
//        double[] t2 = {0,0};
//        double[] e2 = {0};
//        net.runBatch(t2,e2);
//
//        double[] t3 = {1,0};
//        double[] e3 = {1};
//        net.runBatch(t3,e3);
//
//        double[] t4 = {0,1};
//        double[] e4 = {1};
//        net.runBatch(t4,e4);

        //System.out.println(net.toString());
    }

    private int inputLayerAmout;
    private int outputLayerAmout;
    private int layersAmount = 5;
    private int hiddenNeuronAmount = 5;
    private List<Layer> layers;

    Netz(int inputLayerAmout, int outputLayerAmout){
        this.inputLayerAmout = inputLayerAmout;
        this.outputLayerAmout = outputLayerAmout;
        layers = new ArrayList<>();
        Layer tmp = new InputLayer(inputLayerAmout,hiddenNeuronAmount,null);
        layers.add(tmp);
        for(int i = 0; i<layersAmount;i++){
            Layer next = new Layer(hiddenNeuronAmount,hiddenNeuronAmount,tmp,null);
            tmp.setNext(next);
            layers.add(next);
            tmp = next;
        }
        Layer compareResultLayer = new Layer(outputLayerAmout,outputLayerAmout,null,null);
        //output layer
        Layer out = new Layer(outputLayerAmout,0,tmp,null);
        tmp.setNext(out);
        tmp.setOutputLayerAmt(outputLayerAmout);
        out.setNext(compareResultLayer);

        out.setOutPutlayer();
        layers.add(out);


    }

    public void runBatch(double[] input, double[] expected){
        if(input.length!=inputLayerAmout||expected.length!=outputLayerAmout) throw new IllegalArgumentException("Data not matching to input/output neurons");
        //TODO batch num
        setExpected(expected);
        propagate(input);

        backPropagate();
        resetValuesAndErrors();

    }

    private void setExpected(double[] expected){
        layers.get(layers.size()-1).getNext().setInputs(expected);
    }

    private void propagate(double[] input){
        layers.get(0).setInputs(input);
        for(int i=0;i<layers.size();i++){
            layers.get(i).propagate();
        }
        System.out.println("");
        System.out.println("Final result: ");
        Arrays.stream(layers.get(layers.size() - 1).getNeurons()).forEach(n->System.out.print(n.getValue()+" // "));
        System.out.println("");
        System.out.println("Expected result: ");
        Arrays.stream(layers.get(layers.size() - 1).getNext().getNeurons()).forEach(n->System.out.print(n.getValue()+" // "));
        System.out.println("");
    }

    private void backPropagate(){
        for(int i=0;i<layers.size();i++){
            layers.get(i).backPropagate();
        }
    }
    private void resetValuesAndErrors(){
        layers.get(0).setInputs(null);
        layers.get(layers.size()-1).getNext().setInputs(null);
        layers.stream().forEach(l -> l.setInputs(null));
    }

    @Override
    public String toString(){
        String start = "digraph G {\n";
        String end = "}";
        String arrow = " -> ";
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        for(int i = 0;i<layers.size();i++){
            Layer layer = layers.get(i);
            String layerString=String.valueOf((char)('a'+i));
            String nextLayerString=String.valueOf((char)('a'+i+1));
            for(int t = 0; t<layer.getNeurons().length;t++){
                Neuron[] neurons = layer.getNeurons();
                for(int n=0;n<neurons.length;n++){
                    Neuron neuronFrom = neurons[t];
                    if(layer.getNext().getNeurons().length>n){
                        Neuron neuronTo = layer.getNext().getNeurons()[n];

                        String neuronFromStr = layerString+t;
                        String neuronToStr = nextLayerString+n;
                        sb.append(neuronFromStr);
                        sb.append(arrow);
                        sb.append(neuronToStr);
                        if(neuronFrom.getWeights().length>0){
                            sb.append(embedinGraphWizlabel(neuronFrom.getWeights()[n]));
                        }

                        sb.append("\n");
                    }
                }
            }
        }
        sb.append(end);
        return sb.toString();
    }

    private String embedinGraphWizlabel(double weight){
        return " [ label = \""+Math.round(weight*100)/100.0+"\" ];";
    }

}
