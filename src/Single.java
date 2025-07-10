import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Single {

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        for(int i = 0; i < 10; i++) {
            executorService.execute(new Task(i));
        }
    }

    public static class Task implements Runnable {
        private int id;

        public Task(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Task " + id + "is running on thread " + Thread.currentThread().getName());
        }
    }
}
