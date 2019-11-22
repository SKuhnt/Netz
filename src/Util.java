import java.util.Random;

public class Util {

    static Random random = new Random(2);
    //1, 2 nan


    public static double getGaussWeight(int weightRange){
        return (random.nextGaussian()*weightRange)*(1.0*random.nextInt(2)==1?1:-1);
    }

    public static double calcSigmoid(double input){
        return (1.0/(1+Math.exp(-input)));
    }

    public static double calcSigmoidDerivate(double input){
        double a = calcSigmoid(input);
        double b = 1 - calcSigmoid(input);
        double x = a * b;
        return x;
    }
}
