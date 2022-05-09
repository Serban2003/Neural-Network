import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Matrix {
    double [][] data;
    int rows, cols;

    private static final Logger logger = LoggerFactory.getLogger(Matrix.class);

    public Matrix(int rows, int cols){
        data = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;

        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                data[i][j] = Math.random() * 2 - 1;
    }

    public void addNumber(double number){
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                this.data[i][j] += number;
    }

    public void addMatrix(Matrix matrix){
        if(matrix.rows != rows || matrix.cols != cols){
            logger.error("The matrices don't have the same dimensions!");
            return;
        }
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                this.data[i][j] += matrix.data[i][j];
    }

    public static Matrix subtract(Matrix a, Matrix b){
        if(a.rows != b.rows || a.cols != b.cols){
            logger.error("The matrices don't have the same dimensions!");
            return new Matrix(1, 1);
        }
        Matrix result = new Matrix(a.rows, a.cols);

        for (int i = 0; i < a.rows; ++i)
            for (int j = 0; j < a.cols; ++j)
                result.data[i][j] = a.data[i][j] - b.data[i][j];

        return result;
    }

    public static Matrix transpose(Matrix matrix){
        Matrix result = new Matrix(matrix.cols, matrix.rows);

        for(int i = 0; i < matrix.rows; ++i)
            for(int j = 0; j < matrix.cols; ++j)
                result.data[j][i] = matrix.data[i][j];

        return result;
    }

    public void multiplyByNumber(double number){
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                this.data[i][j] *= number;
    }

    public void multiplyByMatrix(Matrix matrix){
        if(matrix.rows != rows || matrix.cols != cols){
            logger.error("The matrices can't be multiplied!");
            return;
        }

        for(int i = 0; i < matrix.rows; ++i)
            for(int j = 0; j < matrix.cols; ++j)
                this.data[i][j] *= matrix.data[i][j];
    }

    public static Matrix multiply(Matrix a, Matrix b){
        if(a.cols != b.rows){
            logger.error("The matrices don't have the same dimensions!");
            return new Matrix(1, 1);
        }
        Matrix result = new Matrix(a.rows, b.cols);

        for(int i = 0; i < result.rows; ++i)
            for(int j = 0; j < result.cols; ++j){
                double sum = 0;
                for(int k = 0; k < a.cols; ++k)
                    sum += a.data[i][k] * b.data[k][j];

                result.data[i][j] = sum;
            }
        return result;
    }

    public void sigmoid(){
        //sigmoid activation function
        //f : [0, 1] -> [-1, 1]
        //f(x) = 1 / (1 + e^-x)

        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                this.data[i][j] = 1 / (1 + Math.exp(-this.data[i][j]));
    }

    public  Matrix derivativeSigmoid(){
        //derivative of sigmoid function
        //g : [0, 1] -> [0, 1 / 4]
        //g(x) = x * (1 - x)

        Matrix result = new Matrix(rows, cols);
        for(int  i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                result.data[i][j] = this.data[i][j] * (1 - this.data[i][j]);

        return result;
    }

    public static Matrix transformFromArray(double[] array){
        Matrix result = new Matrix(array.length, 1);
        for(int i = 0; i < array.length; ++i)
            result.data[i][0] = array[i];

        return result;
    }

    public List<Double> transformToArray(){
        List <Double> result = new ArrayList<>();

        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                result.add(data[i][j]);

        return result;
    }

    public void write(){
        for(int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j)
                System.out.print(data[i][j] + " ");
            System.out.println();
        }
    }
}