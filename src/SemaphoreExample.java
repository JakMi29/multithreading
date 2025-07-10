import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public enum Enum {
        INSTANCE;
        private Semaphore semaphore = new Semaphore(3, true);

        public void doSomething() {
            try {
                semaphore.acquire();
                System.out.println("Doing something...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service= Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    Enum.INSTANCE.doSomething();
                }
            });
        }
    }
}
