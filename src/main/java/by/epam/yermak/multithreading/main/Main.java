package by.epam.yermak.multithreading.main;

import by.epam.yermak.multithreading.entity.Matrix;
import by.epam.yermak.multithreading.util.Resource;
import by.epam.yermak.multithreading.thread.MatrixThread;
import by.epam.yermak.multithreading.write.Write;


import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

        int N = new Resource().getN();
        int Y = new Resource().getY();

        int[][] matrix = new int[N][N];
        int[] results = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = 0;
            }
            results[i] = 0;
        }

        ReentrantLock locker = new ReentrantLock();
        Semaphore semaphore = new Semaphore(matrix.length);
        CyclicBarrier barrier = new CyclicBarrier(matrix.length, () -> {
            Write write = new Write();
            write.writeMatrix(new Matrix(matrix, results));
            Arrays.fill(results, 0);
        });

        for (int i = 0; i < N * Y; i++) {
            new MatrixThread(String.valueOf(i + 1), matrix, results, locker, semaphore, barrier).start();
        }
    }
}