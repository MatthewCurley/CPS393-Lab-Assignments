class SumThread extends Thread {
    private int sum = 0;

    public void run() {
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
    }

    public int getSum() {
        return sum;
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        SumThread t = new SumThread();

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sum (Thread): " + t.getSum());
    }
}
