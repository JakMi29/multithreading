package exercises.mergeSort;


public class MergeSortParallel {
    public static void sort(int[] array, int numberOfThreads) {
        if (array == null || array.length < 2) {
            return;
        }
        int[] temp = new int[array.length];
        sort(array, temp, 0, array.length - 1, numberOfThreads);
    }

    private static void sort(int[] array, int[] temp, int left, int right, int numberOfThreads) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        if (numberOfThreads > 1) {
            Thread leftThread = new Thread(() -> sort(array, temp, left, mid, numberOfThreads / 2));
            Thread rightThread = new Thread(() -> sort(array, temp, mid + 1, right, numberOfThreads / 2));

            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            MergeSort.sort(array,temp, left, mid);
            MergeSort.sort(array,temp, mid + 1, right);
        }

        merge(array, temp, left, mid, right);
    }

    private static void merge(int[] array, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            temp[k++] = (array[i] <= array[j]) ? array[i++] : array[j++];
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (i = left; i <= right; i++) {
            array[i] = temp[i];
        }
    }
}
