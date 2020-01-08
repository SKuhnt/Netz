public class OutputLayer extends Layer{

    public OutputLayer(Netz netz, LayerType layerType){
        super(netz,LayerType.Output);

    }

    //private override/shadow
   // @Override
    public void backtrackError(){
        throw new IllegalStateException("Backtracking should not be called in outputlayer");
    }

    public void calcAndSetOutputError(double[] targets){
        Neuron[] neurons = this.getNeurons();
        assert(targets.length==neurons.length);
        for(int i = 0; i<neurons.length;i++){
            Neuron neuron = neurons[i];
            double target = targets[i];
            double result = neuron.getValue();
            double currentError = neuron.getError();
            //neuron.setError(currentError+Math.pow((target-result),2.0));
            neuron.setError(currentError+((neuron.getValue() - target) * neuron.valueDerivative()));
        }
    }

    public void finalizeBatch(int numOfSamples){
        Neuron[] neurons = this.getNeurons();
        for(int i = 0; i<neurons.length;i++){
            Neuron neuron = neurons[i];
            double currentError = neuron.getError();
            neuron.setError((1/(2.0*numOfSamples))*currentError);
        }
    }

}
