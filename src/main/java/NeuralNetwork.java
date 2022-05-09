import java.util.List;

public class NeuralNetwork {
    Matrix weightsInputHidden, weightsOutputHidden, biasHidden, biasOutput;
    double learnRate = 0.01;

    public NeuralNetwork(int input, int hidden, int output){
        //input - number of input nodes
        //hidden - number of hidden nodes
        //output - number of output nodes
        weightsInputHidden = new Matrix(hidden, input);
        weightsOutputHidden = new Matrix(output, hidden);

        biasHidden = new Matrix(hidden, 1);
        biasOutput = new Matrix(output, 1);
    }

    public List<Double> predict(double[] array){
        Matrix input = Matrix.transformFromArray(array);
        Matrix hidden = Matrix.multiply(weightsInputHidden, input);
        hidden.addMatrix(biasHidden);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weightsOutputHidden, hidden);
        output.addMatrix(biasOutput);
        output.sigmoid();

        return output.transformToArray();
    }

    public void train(double[] trainInput, double[] expectedOutput){
        Matrix input = Matrix.transformFromArray(trainInput);
        Matrix hidden = Matrix.multiply(weightsInputHidden, input);
        hidden.addMatrix(biasHidden);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weightsOutputHidden, hidden);
        output.addMatrix(biasOutput);
        output.sigmoid();

        Matrix target = Matrix.transformFromArray(expectedOutput);

        Matrix error = Matrix.subtract(target, output);
        Matrix gradient = output.derivativeSigmoid();
        gradient.multiplyByMatrix(error);
        gradient.multiplyByNumber(learnRate);

        Matrix hiddenTranspose = Matrix.transpose(hidden);
        Matrix whoDelta = Matrix.multiply(gradient, hiddenTranspose);

        weightsOutputHidden.addMatrix(whoDelta);
        biasOutput.addMatrix(gradient);

        Matrix whoTranspose = Matrix.transpose(weightsOutputHidden);
        Matrix hiddenErrors = Matrix.multiply(whoTranspose, error);

        Matrix hiddenGradient = hidden.derivativeSigmoid();
        hiddenGradient.multiplyByMatrix(hiddenErrors);
        hiddenGradient.multiplyByNumber(learnRate);

        Matrix inputTranspose = Matrix.transpose(input);
        Matrix wihDelta = Matrix.multiply(hiddenGradient, inputTranspose);

        weightsInputHidden.addMatrix(wihDelta);
        biasHidden.addMatrix(hiddenGradient);
    }

    public void fit(double[][] input, double[][] output, int times){
        for(int i = 0; i < times; ++i){
            int sampleNumber = (int)(Math.random() * input.length);
            this.train(input[sampleNumber], output[sampleNumber]);
        }
    }
}