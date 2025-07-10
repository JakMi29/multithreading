package exercises.mergeSort;

import java.util.concurrent.RecursiveAction;

public class ForkMergeSort extends RecursiveAction {

    int[] array;
    int startIndex;
    int endIndex;

    public ForkMergeSort(int[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected void compute() {
        if (endIndex - startIndex <= 16) {
            sequentialMergeSort(startIndex, endIndex);
        } else {
            int mid = (startIndex + endIndex) / 2;
            ForkMergeSort leftTask = new ForkMergeSort(array, startIndex, mid);
            ForkMergeSort rightTask = new ForkMergeSort(array, mid + 1, endIndex);
            invokeAll(leftTask, rightTask);
            merge(startIndex, mid, endIndex);
        }
    }

    private void sequentialMergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sequentialMergeSort(left, mid);
            sequentialMergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = array[i] <= array[j] ? array[i++] : array[j++];
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        for (int l = 0; l < temp.length; l++) {
            array[left + l] = temp[l];
        }
    }
}
