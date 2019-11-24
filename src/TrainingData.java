import java.util.HashMap;
import java.util.Map;

public class TrainingData {

    Map<double[], double[]> inputOutputMap = new HashMap<>();

    public void train(Layer layers, int numberOfTrainings){
        for (int i = 0; i < numberOfTrainings; i++){
            for (Map.Entry<double[], double[]> trainingData : inputOutputMap.entrySet()){
                layers.setInputs(trainingData.getKey());
                layers.setTarget(trainingData.getValue());
                layers.run();
                layers.learn();
            }
        }
    }

    public TrainingData(){

        inputOutputMap.put(new double[]{18, 0, 300}, new double[]{1});
        inputOutputMap.put(new double[]{19, 0, 180}, new double[]{1});
        inputOutputMap.put(new double[]{20, 0, 160}, new double[]{1});
        inputOutputMap.put(new double[]{21, 0, 250}, new double[]{1});
        inputOutputMap.put(new double[]{23, 0, 200}, new double[]{1});
        inputOutputMap.put(new double[]{25, 0, 180}, new double[]{1});
        inputOutputMap.put(new double[]{27, 0, 200}, new double[]{1});
        inputOutputMap.put(new double[]{28, 0, 320}, new double[]{1});
        inputOutputMap.put(new double[]{22, 0, 200}, new double[]{0});

    }

}
