package exercises.philosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EatingRoom {

    public static void main(String[] args) {
        int philosophersCount = 2;
        int maxEatingTime=5;
        ExecutorService service= Executors.newFixedThreadPool(5);
        Stick[] sticks = new Stick[philosophersCount];

        for (int i = 0; i < philosophersCount; i++) {
            sticks[i] = new Stick(i);
        }
        for (int i = 0; i < philosophersCount; i++) {
            new Thread(new Philosopher(i, sticks[i], sticks[(i + 1) % philosophersCount],maxEatingTime)).start();
        }
        for(int i = 0; i < philosophersCount; i++) {
            service.execute(new Philosopher(i, sticks[i], sticks[(i + 1) % philosophersCount],maxEatingTime));
        }
    }
}
