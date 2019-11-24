import java.util.Arrays;

public class Neuron {

    private double[] weights;
    private double value;
    private double error;
    private double bias;

    Neuron(int nextNeuronsAmount){
        weights = new double[nextNeuronsAmount];
        Arrays.setAll(weights, p -> Util.getGaussWeight(2));
        bias = Util.getGaussWeight(2);
    }

    public double calculateNextValue(int i){
        return weights[i] * value;
    }

    public void setBias(double currentValue) {
        this.bias = currentValue;
    }

    public double getBias() {
        return bias;
    }

    public void setValue(double currentValue) {
        this.value = currentValue;
    }

    public void initiateValue(double currentValue) {
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
