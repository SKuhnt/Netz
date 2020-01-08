import java.util.Arrays;

public class Neuron {

    private double[] weights;
    private double[] weightUpdates;
    private double value;
    private double error;
    private double bias;
    private double biasUpdate;

    Neuron(int nextNeuronsAmount){
        weights = new double[nextNeuronsAmount];
        Arrays.setAll(weights, p -> Util.getGaussWeight(2));
        bias = Util.getGaussWeight(2);
        weightUpdates=new double[nextNeuronsAmount];
        biasUpdate=0;
    }

    public void resetWeightAndBiasUpdates(){
        for(int i=0;i<weightUpdates.length;i++){
            weightUpdates[i]=0;
        }
        biasUpdate = 0;
    }

    public double[] getWeightUpdates() {
        return weightUpdates;
    }

    public void setWeightUpdates(int index, double weightUpdate) {
        this.weightUpdates[index] = weightUpdate;
    }

    public double getBiasUpdate() {
        return biasUpdate;
    }

    public void setBiasUpdate(double biasUpdate) {
        this.biasUpdate = biasUpdate;
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

    public void setWeightAt(int index,double value){
        weights[index]=value;
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
