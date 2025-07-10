import java.util.ArrayList;
import java.util.List;

public class ListExample {

    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 10;
    private static final int LOWER_LIMIT = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("List is full, waiting for consumer to consume...");
                    lock.wait();
                } else {
                    value++;
                    list.add(value);
                    System.out.println("Produced: " + value);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("List is empty, waiting for producer to produce...");
                    lock.wait();
                } else {
                    value = list.remove(0);
                    System.out.println("Consumed: " + value);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
    public static void main(String[] args) {
        ListExample example = new ListExample();
        Thread producerThread = new Thread(() -> {
            try {
                example.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                example.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
