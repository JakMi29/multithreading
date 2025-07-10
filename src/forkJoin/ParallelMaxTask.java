package forkJoin;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxTask extends RecursiveTask<Integer> {

    private final int[] array;
    private final int lowIndex;
    private final int highIndex;

    public ParallelMaxTask(int[] array, int lowIndex, int highIndex) {
        this.array = array;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Integer compute() {
        if (highIndex - lowIndex > 10000) {
            ParallelMaxTask leftTask = new ParallelMaxTask(array, lowIndex, (lowIndex + highIndex) / 2);
            ParallelMaxTask rightTask = new ParallelMaxTask(array, (lowIndex + highIndex) / 2 + 1, highIndex);

            invokeAll(leftTask, rightTask);
            return Math.max(leftTask.join(), rightTask.join());
        } else {
            return sequentialMax();
        }
    }

    public Integer sequentialMax() {
        int max = array[lowIndex];
        for (int i = lowIndex + 1; i < highIndex; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
