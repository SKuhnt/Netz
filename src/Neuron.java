import java.util.Arrays;

public class Neuron {

    private double[] gewichte;
    private double value;

    Neuron(int nextNeuronsAmount){
        gewichte = new double[nextNeuronsAmount];
        Arrays.setAll(gewichte, p -> Util.getGaussWeight(2));
    }

    public double calculateNextValue(){

    }

    public double[] calculateNextValues(){

    }

}
