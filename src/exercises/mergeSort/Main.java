package exercises.mergeSort;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static Random random = new Random();

    public static void main(String[] args) {
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of available threads: " + numOfThreads);

        int[] data1 = createRandomArray();
        int[] data2 = createRandomArray();
        int[] data3 = createRandomArray();

        long start = System.nanoTime();
        MergeSort.sort(data1);
        long end = System.nanoTime();
        System.out.println("MergeSort time: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        MergeSortParallel.sort(data2, numOfThreads);
        end = System.nanoTime();
        System.out.println("MergeSortParallel time: " + (end - start) / 1_000_000.0 + " ms");

        ForkJoinPool pool = new ForkJoinPool(numOfThreads);
        ForkMergeSort forkMergeSort = new ForkMergeSort(data3, 0, data3.length - 1);
        start = System.nanoTime();
        pool.invoke(forkMergeSort);
        end = System.nanoTime();
        System.out.println("ForkMergeSort time: " + (end - start) / 1_000_000.0 + " ms");
    }

    public static int[] createRandomArray() {
        int[] a = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            a[i] = random.nextInt(10000);
        }
        return a;
    }

}
