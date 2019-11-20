import java.util.Arrays;

public class Neuron {

    private double[] weights;
    private double value;
    private double error;
    private boolean isOutputLayer=false;

    private Layer parentLayer;

    Neuron(int nextNeuronsAmount, Layer parent){
        weights = new double[nextNeuronsAmount];
        Arrays.setAll(weights, p -> Util.getGaussWeight(2));
        this.parentLayer=parent;
        error=0;
    }

    public double calculateNextValue(int i){
        return weights[i] * value;
    }

    public void setOutputNeuron(){
        isOutputLayer=true;
    }

    public void setWeightsAmt(int i){
        weights = new double[i];
        Arrays.setAll(weights, p -> Util.getGaussWeight(2));
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

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public void backPropagate(){
        for(int i=0;i<weights.length;i++){
            //calc errors
            if(isOutputLayer){
                error += Util.calcHiddenLayerError(weights[i], parentLayer.getNext().getNeuron(i).getError(),getValue());
            }
            else{
                error = Util.calcOutputLayerError(value,getExpectedValue(i));
            }
        }
        for(int i=0;i<weights.length;i++){
            weights[i]=Util.updateWeight(weights[i],getLearningrate(),error,value);
        }

    }

    private double getExpectedValue(int i) {
        return parentLayer.getNext().getNeuron(i).getValue();
    }

    private double getLearningrate() {
        return 0.15;
    }
}
