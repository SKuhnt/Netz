import java.util.Arrays;

public class Neuron {

    private double[] weights;
    private double value;
    private double error;

    Neuron(int nextNeuronsAmount){
        weights = new double[nextNeuronsAmount];
        Arrays.setAll(weights, p -> Util.getGaussWeight(2));
    }

    public double calculateNextValue(int k){
        return weights[k] * value;
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

    public double valueDerivative(){
        return value * (1.0 - value);
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
