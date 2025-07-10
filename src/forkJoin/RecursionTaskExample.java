package forkJoin;

import java.util.concurrent.RecursiveTask;

public class RecursionTaskExample extends RecursiveTask<Integer> {

    private int simulationWork;

    public RecursionTaskExample(int simulationWork) {
        this.simulationWork = simulationWork;
    }

    @Override
    protected Integer compute() {
        if (simulationWork > 100) {

            System.out.println("Splitting work: " + simulationWork);

            RecursiveTask<Integer> action1 = new RecursionTaskExample(simulationWork / 2);
            RecursiveTask<Integer> action2 = new RecursionTaskExample(simulationWork / 2);

            action1.fork();
            action2.fork();

            Integer sum = 0;
            sum += action1.join();
            sum += action2.join();
            return sum;
        } else {
            return simulationWork;
        }
    }
}
