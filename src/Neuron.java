import java.util.Arrays;
import java.util.Random;

public class Neuron {

    private double[] gewichte;

    Neuron(int nextNeuronsAmount){
        gewichte = new double[nextNeuronsAmount];
        Arrays.setAll(gewichte, p -> Util.getGaussWeight(2));
    }

}
