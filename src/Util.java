import java.util.Arrays;
import java.util.Random;

public class Util {

    public static double getGaussWeight(int weightRange){
        return (new Random().nextGaussian()*weightRange)*(1.0*new Random().nextInt(2)==1?1:-1);
    }

    public static double calcSigmoid(double input){
        return (1.0/(1+Math.exp(1.0*-input)));
    }

//    public static double calcDerivatedSigmoid(double input){
//        double sigmoidResult = calcSigmoid(input);
//        return sigmoidResult*(1-sigmoidResult);
//    }

    public static double calcDerivatedSigmoid(double output){
        //derivative of a neuron output
        //double deriv = calcSigmoid(output);
        return output*(1.0-output);
        //return deriv;
    }

    public static double calcOutputLayerError(double output,double expected){
        //error for each node

        double error = (expected-output)*calcDerivatedSigmoid(output);
        //System.out.println("SIMPLE ERROR ="+(expected-output));
        //System.out.println("ERROR CALC ="+error);
        return error;
    }

    public static double calcHiddenLayerError(double[] weights,double[] errorsInNext,double outputCurrent){
        //error for each node
        double sum = 0;
        for(int i = 0; i<weights.length;i++) {
            sum+=weights[i]*errorsInNext[i];
        }

        return sum*calcDerivatedSigmoid(outputCurrent);
    }

    public static double getUpdateWeight(double value , double learningrate, double error){
        double update =  -learningrate * value * error;
        //System.out.println("error in next: "+error);
        //System.out.println("value: "+value);
        //System.out.println("update: "+update);
        return update;
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
