package exercises.mergeSort;

import java.util.Arrays;

public class MergeSort {
    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int[] temp = new int[array.length];
        sort(array, temp, 0, array.length - 1);
    }

    public static void sort(int[] array, int[] temp, int left, int right) {
        if (left >= right) {

            return;
        }

        int mid = left + (right - left) / 2;
        sort(array, temp, left, mid);
        sort(array, temp, mid + 1, right);
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
