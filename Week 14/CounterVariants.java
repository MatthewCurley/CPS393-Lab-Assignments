class Counter {
    private int count = 0;
    private boolean useSync;

    public Counter(boolean useSync) {
        this.useSync = useSync;
    }

    public void increment() {
        if (useSync) {
            synchronized (this) {
                count++;
            }
        } else {
            count++; // NOT synchronized
        }
    }

    public int getCount() {
        return count;
    }
}

class CounterThread extends Thread {
    private Counter counter;
    private boolean slowDown;

    public CounterThread(Counter counter, boolean slowDown) {
        this.counter = counter;
        this.slowDown = slowDown;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();

            // Only slow down Mode 2 (no join)
            if (slowDown) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class CounterVariants {
    public static void main(String[] args) {

        /*
         * Modes:
         * 1 = synchronized + join      (correct → 10000)
         * 2 = synchronized + NO join   (may be < 10000)
         * 3 = NOT synchronized + join  (race condition → < 10000)
         */

        int mode = 1; // CHANGE THIS (1, 2, or 3)

        boolean useSync = (mode == 1 || mode == 2);
        boolean useJoin = (mode == 1 || mode == 3);
        boolean slowDown = (mode == 2); // only slow Mode 2

        Counter counter = new Counter(useSync);
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new CounterThread(counter, slowDown);
            threads[i].start();
        }

        if (useJoin) {
            for (int i = 0; i < 10; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Mode: " + mode);
        System.out.println("Final Counter: " + counter.getCount());
    }
}