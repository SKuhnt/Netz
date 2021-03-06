import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TrainingData {

    Map<double[], double[]> inputOutputMap = new HashMap<>();

    public TrainingData() {
        /*Random random = new Random(2);
        for (int i = 0; i < 500; i++){
            double a = random.nextDouble();
            double b = random.nextDouble();
            inputOutputMap.put(new double[]{a, b}, new double[]{Math.abs(a - b), a - b > 0 ? 1 : 0});
        }*/

        inputOutputMap.put(new double[]{0.18, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.18, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.18, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.18, 0.160}, new double[]{1});
        inputOutputMap.put(new double[]{0.18, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.18, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.18, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.20, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.20, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.20, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.20, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.20, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.20, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.20, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.23, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.23, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.23, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.23, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.23, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.23, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.23, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.26, 0.060}, new double[]{1});
        inputOutputMap.put(new double[]{0.26, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.26, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.26, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.26, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.26, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.26, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.30, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.30, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.30, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.30, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.30, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.30, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.30, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.33, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.33, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.33, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.33, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.33, 0.180}, new double[]{0});
        inputOutputMap.put(new double[]{0.33, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.33, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.36, 0.060}, new double[]{1});
        inputOutputMap.put(new double[]{0.36, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.36, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.36, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.36, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.36, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.36, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.39, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.39, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.39, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.39, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.39, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.39, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.39, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.42, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.42, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.42, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.42, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.42, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.42, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.42, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.45, 0.060}, new double[]{1});
        inputOutputMap.put(new double[]{0.45, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.45, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.45, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.45, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.45, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.45, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.48, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.48, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.48, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.48, 0.160}, new double[]{1});
        inputOutputMap.put(new double[]{0.48, 0.180}, new double[]{0});
        inputOutputMap.put(new double[]{0.48, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.48, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.51, 0.060}, new double[]{1});
        inputOutputMap.put(new double[]{0.51, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.51, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.51, 0.160}, new double[]{1});
        inputOutputMap.put(new double[]{0.51, 0.180}, new double[]{0});
        inputOutputMap.put(new double[]{0.51, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.51, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.54, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.54, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.54, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.54, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.54, 0.180}, new double[]{0});
        inputOutputMap.put(new double[]{0.54, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.54, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.57, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.57, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.57, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.57, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.57, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.57, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.57, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.60, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.60, 0.120}, new double[]{1});
        inputOutputMap.put(new double[]{0.60, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.60, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.60, 0.180}, new double[]{0});
        inputOutputMap.put(new double[]{0.60, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.60, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.63, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.63, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.63, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.63, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.63, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.63, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.63, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.66, 0.060}, new double[]{1});
        inputOutputMap.put(new double[]{0.66, 0.120}, new double[]{1});
        inputOutputMap.put(new double[]{0.66, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.66, 0.160}, new double[]{1});
        inputOutputMap.put(new double[]{0.66, 0.180}, new double[]{0});
        inputOutputMap.put(new double[]{0.66, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.66, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.69, 0.060}, new double[]{1});
        inputOutputMap.put(new double[]{0.69, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.69, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.69, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.69, 0.180}, new double[]{0});
        inputOutputMap.put(new double[]{0.69, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.69, 0.220}, new double[]{0});
        inputOutputMap.put(new double[]{0.72, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.72, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.72, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.72, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.72, 0.180}, new double[]{0});
        inputOutputMap.put(new double[]{0.72, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.72, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.75, 0.060}, new double[]{1});
        inputOutputMap.put(new double[]{0.75, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.75, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.75, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.75, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.75, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.75, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.78, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.78, 0.120}, new double[]{1});
        inputOutputMap.put(new double[]{0.78, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.78, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.78, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.78, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.78, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.81, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.81, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.81, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.81, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.81, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.81, 0.200}, new double[]{0});
        inputOutputMap.put(new double[]{0.81, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.84, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.84, 0.120}, new double[]{0});
        inputOutputMap.put(new double[]{0.84, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.84, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.84, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.84, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.84, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.87, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.87, 0.120}, new double[]{1});
        inputOutputMap.put(new double[]{0.87, 0.140}, new double[]{0});
        inputOutputMap.put(new double[]{0.87, 0.160}, new double[]{0});
        inputOutputMap.put(new double[]{0.87, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.87, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.87, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.90, 0.060}, new double[]{0});
        inputOutputMap.put(new double[]{0.90, 0.120}, new double[]{1});
        inputOutputMap.put(new double[]{0.90, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.90, 0.160}, new double[]{1});
        inputOutputMap.put(new double[]{0.90, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.90, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.90, 0.220}, new double[]{1});
        inputOutputMap.put(new double[]{0.100, 0.060}, new double[]{1});
        inputOutputMap.put(new double[]{0.100, 0.120}, new double[]{1});
        inputOutputMap.put(new double[]{0.100, 0.140}, new double[]{1});
        inputOutputMap.put(new double[]{0.100, 0.160}, new double[]{1});
        inputOutputMap.put(new double[]{0.100, 0.180}, new double[]{1});
        inputOutputMap.put(new double[]{0.100, 0.200}, new double[]{1});
        inputOutputMap.put(new double[]{0.100, 0.220}, new double[]{1});
    }

}
