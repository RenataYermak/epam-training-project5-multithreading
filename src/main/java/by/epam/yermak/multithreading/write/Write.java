package by.epam.yermak.multithreading.write;

import by.epam.yermak.multithreading.entity.Matrix;

import java.io.*;

public class Write {
    private  static final String FILE_NAME = "result.txt";

    public void writeMatrix(Matrix matrix) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            int[][] values = matrix.getMatrix();
            for (int[] value : values) {
                for (int j = 0; j < values.length; j++) {
                    writer.append(String.valueOf(value[j])).append('\t');
                }
                writer.append('\n');
            }
            writer.append("\n");
            int[] results = matrix.getResults();
            writer.append("sum of elements - ");
            for (int i = 0; i < results.length; i++) {
                writer.append(String.valueOf(results[i]));
                if (i < results.length - 1) {
                    writer.append(", ");
                }
            }
            writer.append("\n\n\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
