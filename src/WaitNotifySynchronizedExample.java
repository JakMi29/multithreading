public class WaitNotifySynchronizedExample {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the produce method");
            wait();
            System.out.println("Restore the produce method");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Running the consume method");
            notify();
        }
    }

    public static void main(String[] args) {
        WaitNotifySynchronizedExample process = new WaitNotifySynchronizedExample();
        Thread thread1 = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                process.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
