public class OutputLayer  {

//    OutputLayer(int inputLayerAmout,int hiddenNeuronAmount, Layer prev){
//        super(inputLayerAmout,hiddenNeuronAmount,prev, null);
//    }
//
//    @Override
//    public void backPropagate(){
//        for(int i=0;i<weights.length;i++){
//            //calc errors
//            if(isOutputLayer){
//                error += Util.calcHiddenLayerError(weights[i], parentLayer.next.getNeuron(i).getError(),getValue());
//            }
//            else{
//                error = Util.calcOutputLayerError(value,getExpectedValue(i));
//            }
//        }
//        for(int i=0;i<weights.length;i++){
//            weights[i]=Util.getUpdateWeight(weights[i],getLearningrate(),error,value);
//        }
//
//    }
}
