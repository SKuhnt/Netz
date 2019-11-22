import java.util.Arrays;

public class Neuron {

    private double[] weights;
    private double value;
    private double error;

    Neuron(int nextNeuronsAmount){
        weights = new double[nextNeuronsAmount];
        Arrays.setAll(weights, p -> Util.getGaussWeight(2));
    }

    public double calculateNextValue(int i){
        return weights[i] * value;
    }

    public double[] calculateNextValues(){
        double [] values = new double[weights.length];
        for (int i = 0; i < weights.length; i++){
            values[i] = calculateNextValue(i);
        }
        return values;
    }

    public void setValue(double currentValue) {
        this.value = currentValue;
    }

    public double getValue() {
        return value;
    }

    public double[] getWeights() {
        return weights;
    }

    public double getWeightAt(int index){
        return weights[index];
    }

    public double outoutDerivate(){
        return value * (1 - value);
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
