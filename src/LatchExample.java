import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LatchExample {

    public static void main(String[] args) {
        CountDownLatch latch= new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);

        for(int i= 0; i < 5; i++) {
            service.execute(new Worker(i, latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        service.shutdown();
    }

    static class Worker implements Runnable {
        private Integer id;
        private CountDownLatch latch;

        public Worker(Integer id, CountDownLatch latch) {
            this.id = id;
            this.latch=latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Worker with id: " + id + " has finished work.");
            latch.countDown();
        }
    }
}
