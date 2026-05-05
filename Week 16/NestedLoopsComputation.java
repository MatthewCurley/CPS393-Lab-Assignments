import java.util.Random;

public class NestedLoopsComputation {

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000};

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

            long start = System.currentTimeMillis();

            long[] results = new long[n];

            for (int j = 0; j < n; j++) {

                long sum = 0;

                for (int i = 0; i < n; i++) {
                    sum += a[i] * b[j][i];
                }

                results[j] = sum;
            }

            long end = System.currentTimeMillis();

            System.out.println("n = " + n);
            System.out.println("Nested loop time: " + (end - start) + " ms");
            System.out.println();
        }
    }
}