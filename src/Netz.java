import java.util.*;

public class Netz {

    public static void main(String[] args) throws InterruptedException {
        Netz net = new Netz(2, 1);

//        double[][] tests = {{1,1,0},{1,0,1},{0,0,0},{0,1,1}};
//        Random rand = new Random();
//
//        //System.out.println(net.toString());
//        for(int i = 0; i<100;i++){
//            double[] set = tests[rand.nextInt(3)];
//            double[] x = new double[2];
//            x[0]=set[0];
//            x[1]=set[1];
//
//            double[] y = new double[1];
//            y[0]=set[2];
//            System.out.println("input: "+x[0]+" "+x[1]);
//            net.runBatch(x,y);
//        }
        Random rand = new Random();

//        for(int i = 0; i<1000;i++){
//
//            double[] x = new double[2];
//            x[0]=rand.nextDouble();
//            x[1]=rand.nextDouble();
//
//            double[] y = new double[1];
//            y[0]=x[0]*x[1];
//            System.out.println("input: "+x[0]+" "+x[1]);
//            net.runBatch(x,y);
//        }



        boolean serviceRequested = true;
        Scanner sc = new Scanner(System.in);
        while(serviceRequested){
            System.out.println("input1:");
            String in1 = sc.nextLine();
            if(in1.startsWith("q")) {
                System.out.println(net.toString());
                break;
            }
            System.out.println("input2:");
            String in2 = sc.nextLine();
            if(in2.startsWith("q")) {
                System.out.println(net.toString());
                break;
            }

            double[] x = new double[2];
            x[0]=Double.parseDouble(in1);
            x[1]=Double.parseDouble(in2);



            System.out.println("expected : ");
            String exp = sc.nextLine();
            if(exp.startsWith("q")) {
                System.out.println(net.toString());
                break;
            }

            double[] y = new double[1];
            y[0]=Double.parseDouble(exp);


            net.runBatch(x,y);

            System.out.println(net.toString());
//            System.out.println("print? y/N:");
//            String print = sc.next();
//            if(print.startsWith("y")){
//                System.out.println(net.toString());
//            }
//            else if(print.startsWith("q")){
//                System.out.println(net.toString());
//                break;
//            }

        }
    }

    private int inputLayerAmout;
    private int outputLayerAmout;
    private int layersAmount = 1;
    private int hiddenNeuronAmount = 1;
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
            layers.get(layers.size()-1-i).backPropagate();
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
                Neuron neuronFrom = neurons[t];
                for(int n=0;n<neuronFrom.getWeights().length;n++){
                    double weight = neuronFrom.getWeights()[n];
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
