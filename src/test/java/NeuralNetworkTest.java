import org.junit.jupiter.api.Test;

import java.util.List;

public class NeuralNetworkTest {

    @Test
    public void testNeuralNetwork(){
        double[][] trainInput = {{0, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 0, 1}};
        double[][] expectedOutput = {{1}, {0}, {1}, {0}};

        NeuralNetwork neuralNetwork = new NeuralNetwork(3, 10, 1);
        neuralNetwork.fit(trainInput, expectedOutput, 50000);

        double[][] input = {{0, 0, 1}, {1, 1, 0}, {0, 1, 0}, {1, 0, 1}};
        List<Double> output;
        for(double[] d : input){
            output = neuralNetwork.predict(d);
            System.out.println(output.toString());
        }
    }

}
