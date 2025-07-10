package exercises.philosophers;

import java.util.Random;

public class Philosopher implements Runnable {
    private final Stick left;
    private final Stick right;
    private final int id;
    private static volatile boolean isFull;
    private final Random random;
    private int eatingCount;
    private final int maxEatingCount;

    public Philosopher(int id, Stick left, Stick right, int maxEatingCount) {
        this.id = id;
        this.maxEatingCount = maxEatingCount;
        this.eatingCount = 0;
        this.left = left;
        this.right = right;
        this.random = new Random();
    }

    private void think() {
        try {
            Thread.sleep(random.nextInt(3000));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void eat() {
        if (left.pickUp(this)) {
            System.out.println("Philosopher " + id + " picked up left stick.");
            if (right.pickUp(this)) {
                eatingCount++;
                if (eatingCount >= maxEatingCount) {
                    isFull = true;
                    System.out.println("Philosopher " + id + " is full and will stop eating.");
                    return;
                }
                System.out.println("Philosopher " + id + " picked up right stick.");
                try {
                    System.out.println("Philosopher " + id + " is eating.");
                    Thread.sleep(random.nextInt(2000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    right.putDown(this);
                    left.putDown(this);
                }
            } else {
                left.putDown(this);
            }
        }
    }

    @Override
    public void run() {
        while (!isFull) {
            think();
            eat();
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
