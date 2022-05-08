import org.junit.jupiter.api.Test;

public class MatrixTest {
    @Test
    public void testAddNumber(){
        Matrix matrix = new Matrix(3, 3);
        matrix.write();
        System.out.println();
        matrix.addNumber(5);
        matrix.write();
    }

    @Test
    public void testAddMatrix(){
        Matrix matrix1 = new Matrix(3, 3);
        Matrix matrix2 = new Matrix(2, 3);
        matrix1.write();
        System.out.println();
        matrix2.write();
        System.out.println();

        matrix1.addMatrix(matrix2);
        matrix1.write();
    }

    @Test
    public void testSubtract(){
        Matrix matrix1 = new Matrix(3, 3);
        Matrix matrix2 = new Matrix(3, 3);
        matrix1.write();
        System.out.println();
        matrix2.write();
        System.out.println();

        Matrix result = new Matrix(3, 3);
        result = result.subtract(matrix1, matrix2);
        result.write();
    }

    @Test
    public void testTranspose(){
        Matrix matrix = new Matrix(3, 3);
        matrix.write();
        System.out.println();

        Matrix result = new Matrix(3, 3);
        result = result.transpose(matrix);
        result.write();
    }

    @Test
    public void testMultiplyByNumber(){
        Matrix matrix = new Matrix(3, 3);
        matrix.write();
        System.out.println();

        matrix.multiplyByNumber(4);
        matrix.write();
    }

    @Test
    public void testMultiplyByMatrix(){
        Matrix matrix1 = new Matrix(3, 3);
        Matrix matrix2 = new Matrix(3, 3);
        matrix1.write();
        System.out.println();
        matrix2.write();
        System.out.println();

        matrix1.multiplyByMatrix(matrix2);
        matrix1.write();
    }

    @Test
    public void testMultiply(){
        Matrix matrix1 = new Matrix(3, 3);
        Matrix matrix2 = new Matrix(2, 3);
        matrix1.write();
        System.out.println();
        matrix2.write();
        System.out.println();

        Matrix result = new Matrix(3, 3);
        result = result.multiply(matrix1, matrix2);
        result.write();
    }

}
