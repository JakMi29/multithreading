package exercises.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Stick {
    private final int id;
    private Lock lock;

    public Stick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    @Override
    public String toString() {
        return "Stick{" +
                "id=" + id +
                '}';
    }

    public boolean pickUp(Philosopher philosopher) {
        System.out.println(philosopher + " is trying to pick up " + this);
        return lock.tryLock();
    }

    public void putDown(Philosopher philosopher) {
        System.out.println(philosopher + " put down " + this);
        lock.unlock();
    }
}
