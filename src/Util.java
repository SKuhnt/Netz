import java.util.Random;

public class Util {

    public static double getGaussWeight(int weightRange){
        return (new Random().nextGaussian()*weightRange)*(1.0*new Random().nextInt(2)==1?1:-1);
    }

    public static double calcSigmoid(double input){
        return (1.0/(1+Math.exp(4.9 * -input)));
    }

//    public static double calcDerivatedSigmoid(double input){
//        double sigmoidResult = calcSigmoid(input);
//        return sigmoidResult*(1-sigmoidResult);
//    }

    public static double calcDerivatedSigmoid(double output){
        //derivative of a neuron output
        return output*(1-output);
    }

    public static double calcOutputLayerError(double output,double expected){
        //error for each node
        return expected-output*calcDerivatedSigmoid(output);
    }

    public static double calcHiddenLayerError(double weight,double errorInNext,double outputCurrent){
        //error for each node
        return weight*errorInNext*calcDerivatedSigmoid(outputCurrent);
    }

    public static double updateWeight(double oldWeight, double learningrate, double error, double input){
        return oldWeight + learningrate * error * input;
    }




    public static double calcCrossEntropyCost(double[] values, double[] targets, int dataAmt){
        //target,value,dataAmt
        double sum = 0;
        for(int i = 0; i<values.length;i++){
            sum+=targets[i]* Math.log(values[i])+((1-targets[i]* Math.log(1-values[i])));
        }
        return -(1.0/dataAmt)*sum;
    }

    public static double calcCrossEntropyCost(double[] values, double[] targets){
        return calcCrossEntropyCost(values,targets,1);
    }

//    public static double calcDerivatedCrossEntropyCost(double[] values, double[] targets, int dataAmt){
//        //target,value,dataAmt
//        double sum = 0;
//        for(int i = 0; i<values.length;i++){
//            sum+=targets[i]* Math.log(values[i])+((1-targets[i]* Math.log(1-values[i])));
//        }
//        return -(1.0/dataAmt)*sum;
//    }

    public static double calcBasicCost(double claculated, double target){
        return target-claculated;
    }


}
