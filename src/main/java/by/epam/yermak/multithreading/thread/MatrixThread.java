package by.epam.yermak.multithreading.thread;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixThread extends Thread {
    private final int[][] matrix;
    private final int[] results;

    ReentrantLock locker;
    Semaphore semaphore;
    CyclicBarrier barrier;

    public MatrixThread(String name, int[][] matrix, int[] results,
                        ReentrantLock locker, Semaphore semaphore, CyclicBarrier barrier) {
        super(name);
        this.matrix = matrix;
        this.results = results;
        this.locker = locker;
        this.semaphore = semaphore;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            int name = Integer.parseInt(getName());
            for (int i = 0; i < matrix.length; i++) {
                synchronized ((Object) matrix[i][i]) {
                    matrix[i][i] = name;
                }
                int random = new Random().nextInt(matrix.length);
                synchronized ((Object) matrix[i][random]) {
                    matrix[i][random] = name;
                }
            }
            int sum = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (i == name % matrix.length || j == name % matrix.length)
                        sum += matrix[i][j];
                }
            }
            locker.lock();
            results[name % matrix.length] = sum;
            locker.unlock();
            barrier.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            barrier.reset();
        } finally {
            semaphore.release();
        }
    }
}
