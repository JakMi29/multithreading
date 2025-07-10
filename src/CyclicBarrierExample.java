import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(5);

        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            System.out.println("All workers have reached the barrier, proceeding with the next step.");
        });

        for(int i = 0; i < 10; i++) {
            service.execute(new Worker(i, barrier));
        }
    }

    public static class Worker implements Runnable{
        private int id;
        private Random random;
        private CyclicBarrier barrier;

        public Worker(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
            this.random= new Random();
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000+ random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                barrier.await();
            } catch (InterruptedException|BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Worker " + id + " finish the work.");
        }
    }
}
