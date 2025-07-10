import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduled {

    static class task implements Runnable {
        private int id;

        public task(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task " + id + " is running on thread " + Thread.currentThread().getName());

        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(new task(1), 1000,2000, TimeUnit.MILLISECONDS);
    }
}
