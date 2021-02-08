package Algorithm.MatrixOp;

import Algorithm.Exeptions.NotASquareException;

import java.util.Arrays;

public class Matrix {

    private int row;
    private int col;
    private double[][] data;

    public Matrix(double[][] data) {
        this.data = data;
        this.row = data.length;
        this.col = data[0].length;
    }

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        data = new double[row][col];
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double getValueAt(int row, int col) {
        return data[row][col];
    }

    public void setValueAt(int row, int col, double value) {
        data[row][col] = value;
    }

    public boolean isSquare() {
        return row == col;
    }

    public Matrix multiplyByConstant(double constant) {
        Matrix mat = new Matrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat.setValueAt(i, j, data[i][j] * constant);
            }
        }
        return mat;
    }

    public int size() {
        return isSquare() ? row : -1;
    }

    public Matrix multiplyByMatrix(Matrix matrix) {

        Matrix multipliedMatrix = new Matrix(row, matrix.getCol());

        for (int i = 0; i < multipliedMatrix.getRow(); i++) {
            for (int j = 0; j < multipliedMatrix.getCol(); j++) {
                double sum = 0.0;
                for (int k = 0; k < col; k++) {
                    sum += getValueAt(i, k) * matrix.getValueAt(k, j);
                }
                multipliedMatrix.setValueAt(i, j, sum);
            }
        }
        return multipliedMatrix;
    }

    /**
     * Creates a submatrix excluding the given row and column
     *
     * @param excluding_row int
     * @param excluding_col int
     * @return Matrix
     */
    private Matrix createSubMatrix(int excluding_row, int excluding_col) {
        Matrix mat = new Matrix(row - 1, col - 1);
        int r = -1;
        for (int i = 0; i < row; i++) {
            if (i == excluding_row)
                continue;
            r++;
            int c = -1;
            for (int j = 0; j < col; j++) {
                if (j == excluding_col)
                    continue;
                mat.setValueAt(r, ++c, getValueAt(i, j));
            }
        }
        return mat;
    }

    /**
     * The determinant is a scalar value that can be computed from the elements of a matrix.
     * The following method find the determinant from DeterminantCalc class.
     *
     * @return double
     */
    public double getDeterminant() {
        double[][] dataClone = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dataClone[i][j] = data[i][j];
            }
        }
        DeterminantCalc dC = new DeterminantCalc(dataClone);
        return dC.determinant().doubleValue();
    }

    /**
     * Determine the sign
     * i.e. even numbers have sign + and odds -
     *
     * @param i int
     * @return int
     */
    private int changeSign(int i) {
        return (i % 2 == 0) ? 1 : -1;
    }

    /**
     * Transpose of a matrix is produced by swapping the rows with columns.
     *
     * @return Matrix
     */
    public Matrix getTranspose() {
        Matrix transposedMatrix = new Matrix(col, row);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                transposedMatrix.setValueAt(j, i, getValueAt(i, j));
            }
        }
        return transposedMatrix;
    }

    /**
     * The cofactor of a matrix A is matrix C that the value of element Cij equals the determinant of a matrix created by removing row i and column j from matrix A.
     * The following method calculates the cofactor matrix.
     *
     * @return Matrix
     * @throws NotASquareException
     */
    public Matrix getCofactor() throws NotASquareException {
        Matrix mat = new Matrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat.setValueAt(i, j, changeSign(i) * changeSign(j) * createSubMatrix(i, j).getDeterminant());
            }
        }
        return mat;
    }

    /**
     * Inverse of a matrix A^(-1) * A = I where I is the identity matrix
     * A matrix that have inverse is called non-singular or invertible. If the matrix does not have inverse it is called singular.
     * For a singular matrix the values of the inverted matrix are either NAN or Infinity
     * Only square matrices have inverse and the following method will throw exception if the matrix is not square.
     *
     * @return Matrix
     * @throws NotASquareException
     */
    public Matrix getInverse() throws NotASquareException {
        return getCofactor().getTranspose().multiplyByConstant(1.0 / getDeterminant());
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < row; i++) {
            sB.append("[ ");
            for (int j = 0; j < col; j++) {
                sB.append(getValueAt(i, j)).append(" ");
            }
            sB.append(" ]");
            sB.append("\n");
        }
        return sB.toString();
    }
}
