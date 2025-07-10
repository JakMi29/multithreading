import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitNotifyLockExample {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Running the produce method");
        condition.await();
        System.out.println("Restore the produce method");
        lock.unlock();
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Running the consume method");
        condition.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        WaitNotifyLockExample process = new WaitNotifyLockExample();
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
