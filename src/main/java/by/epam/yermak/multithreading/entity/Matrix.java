package by.epam.yermak.multithreading.entity;

import java.util.Arrays;

public class Matrix {
    private final int[][] matrix;
    private final int[] results;

    public Matrix(int[][] matrix, int[] calculationsResults) {
        this.matrix = matrix;
        this.results = calculationsResults;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int[] getResults() {
        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return Arrays.deepEquals(matrix, matrix1.matrix) && Arrays.equals(results, matrix1.results);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(matrix);
        result = 31 * result + Arrays.hashCode(results);
        return result;
    }
}