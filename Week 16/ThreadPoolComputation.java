import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolComputation {

    public static void main(String[] args) throws InterruptedException {

        int[] sizes = {1000, 5000, 10000};

        int cores = Runtime.getRuntime().availableProcessors();

        System.out.println("Cores available: " + cores);
        System.out.println();

        for (int n : sizes) {

            int[] a = new int[n];
            int[][] b = new int[n][n];

            Random rand = new Random();

            for (int i = 0; i < n; i++) {

                a[i] = rand.nextInt(10);

                for (int j = 0; j < n; j++) {
                    b[i][j] = rand.nextInt(10);
                }
            }

            long[] results = new long[n];

            ExecutorService pool = Executors.newFixedThreadPool(cores);

            long start = System.currentTimeMillis();

            for (int j = 0; j < n; j++) {

                final int row = j;

                pool.execute(() -> {

                    long sum = 0;

                    for (int i = 0; i < n; i++) {
                        sum += a[i] * b[row][i];
                    }

                    results[row] = sum;
                });
            }

            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.HOURS);

            long end = System.currentTimeMillis();

            System.out.println("n = " + n);
            System.out.println("Thread pool time: " + (end - start) + " ms");
            System.out.println();
        }
    }
}