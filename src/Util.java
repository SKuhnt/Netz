import java.util.Random;

public class Util {

    public static double getGaussWeight(int weightRange){
        return (new Random().nextGaussian()*weightRange)*(1.0*new Random().nextInt(2)==1?1:-1);
    }

    public static double calcSigmoid(double input){
        return (1.0/(1+Math.exp(4.9 * -input)));
    }
}
