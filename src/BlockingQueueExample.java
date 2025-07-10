import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        FirstWorker firstWorker = new FirstWorker(queue);
        SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }

    static class FirstWorker implements Runnable {
        private BlockingQueue<Integer> queue;

        public FirstWorker(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int counter = 0;

            while (true) {
                try {
                    queue.put(counter);
                    System.out.println("Add item to the queue: " + counter);
                    counter++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    static class SecondWorker implements Runnable {
        private BlockingQueue<Integer> queue;

        public SecondWorker(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int counter = queue.take();
                    System.out.println("Taking item from the queue: " + counter);
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
