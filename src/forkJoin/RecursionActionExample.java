package forkJoin;

import java.security.PublicKey;
import java.util.concurrent.RecursiveAction;

public class RecursionActionExample extends RecursiveAction {

    private int simulationWork;

    public RecursionActionExample(int simulationWork) {
        this.simulationWork = simulationWork;
    }

    @Override
    protected void compute() {
        if (simulationWork > 100) {

            System.out.println("Splitting work: " + simulationWork);

            RecursiveAction action1 = new RecursionActionExample(simulationWork / 2);
            RecursiveAction action2 = new RecursionActionExample(simulationWork / 2);

            invokeAll(action1, action2);
        }else{
            System.out.println("Doing work: " + simulationWork);
        }
    }
}
