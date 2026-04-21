import java.util.Random;

class SumTask extends Thread {
    private int[] arr;
    private int start, end;
    private long sum = 0;

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            sum += arr[i] * arr[i];
        }
    }

    public long getSum() {
        return sum;
    }
}

public class SumSquares {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] arr = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(100) + 1;
        }

        long start1 = System.nanoTime();

        long sum1 = 0;
        for (int i = 0; i < n; i++) {
            sum1 += arr[i] * arr[i];
        }

        long end1 = System.nanoTime();

        long start2 = System.nanoTime();

        SumTask t1 = new SumTask(arr, 0, n / 2);
        SumTask t2 = new SumTask(arr, n / 2, n);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long sum2 = t1.getSum() + t2.getSum();
        long end2 = System.nanoTime();

        System.out.println("n = " + n);
        System.out.println("Non-threaded time: " + (end1 - start1));
        System.out.println("Threaded time: " + (end2 - start2));
    }
}
