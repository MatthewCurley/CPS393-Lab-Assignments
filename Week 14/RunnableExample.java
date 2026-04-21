class SumRunnable implements Runnable {
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

public class RunnableExample {
    public static void main(String[] args) {
        SumRunnable task = new SumRunnable();
        Thread t = new Thread(task);

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sum (Runnable): " + task.getSum());
    }
}
