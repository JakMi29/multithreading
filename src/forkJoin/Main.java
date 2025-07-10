package forkJoin;

import exercises.mergeSort.MergeSort;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Main {

    static Random random = new Random();

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
//        RecursionActionExample action=new RecursionActionExample(600);
//        RecursionTaskExample action1=new RecursionTaskExample(600);
//        pool.invoke(action);
//        System.out.println((pool.invoke(action1)));
        int[] data = createRandomArray();
        ParallelMaxTask action2 = new ParallelMaxTask(data, 0, 1000000000);


        long start1 = System.nanoTime();
        pool.invoke(action2);
        long end1 = System.nanoTime();
        System.out.printf("ParallelMaxTask time: %.2f ms%n", (end1 - start1) / 1_000_000.0);

        start1 = System.nanoTime();
        action2.sequentialMax();
        end1 = System.nanoTime();
        System.out.printf("SequentialMax time: %.2f ms%n", (end1 - start1) / 1_000_000.0);

    }

    public static int[] createRandomArray() {
        int[] a = new int[1000000000];
        for (int i = 0; i < 1000000000; i++) {
            a[i] = random.nextInt(10000);
        }
        return a;
    }
}
