package zyx.romros;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        final int[] arr = {3, 5, 12, 8, 65, 32, 91, 4, 9};
        new QuickSort().sort(arr);
        System.out.println(Arrays.toString(arr));

        final int[] arr2 = {3, 5, 12, 8, 65, 32, 91, 4, 9};
        new QuickSort().quickSortAgain(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    void quickSortAgain(int[] arr) {
        doQuickSortAgain(arr, 0, arr.length - 1);
    }

    void doQuickSortAgain(int[] arr, int lo, int hi) {
        if (hi > lo) {
            int pivot = partitionAgain(arr, lo, hi);
            doQuickSortAgain(arr, lo, pivot - 1);
            doQuickSortAgain(arr, pivot + 1, hi);
        }
    }

    private int partitionAgain(int[] arr, int lo, int hi) {
        int pivotIdx = hi;
        int pivotVal = arr[pivotIdx];
        hi--;

        while (true) {
            while (lo < pivotIdx && arr[lo] <= pivotVal) {
                lo++;
            }
            while (hi > lo && arr[hi] > pivotVal) {
                hi--;
            }
            if (lo >= hi) {
                break;
            } else {
                swapAgain(arr, lo, hi);
                lo++;
            }
        }
        swapAgain(arr, lo, pivotIdx);
        return lo;
    }

    void swapAgain(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    void sort(int[] ints) {
        sortFor(ints, 0, ints.length - 1);
    }

    int partition(int[] arr, int lo, int hi) {
        int pivot = arr[hi];
        int idx = lo - 1;
        for (int i = lo; i < hi; i++) {
            if (arr[i] <= pivot) {
                idx++;
                int tmp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = tmp;
            }
        }
        idx++;
        arr[hi] = arr[idx];
        arr[idx] = pivot;

        return idx;
    }

    void sortFor(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int pivot = partition(arr, lo, hi);
        sortFor(arr, lo, pivot - 1);
        sortFor(arr, pivot + 1, hi);
    }

}
