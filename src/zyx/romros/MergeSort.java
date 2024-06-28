package zyx.romros;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MergeSort().sort(new int[]{4, 1, 3, 2, 0, -1, 7, 10, 9, 20})));
    }

    private int[] sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);

        mergeTwo(arr, lo, mid, hi);
    }

    private void mergeTwo(int[] arr, int lo, int mid, int hi) {
        int[] tmp = new int[hi - lo + 1];

        int i = 0;
        int a1 = lo;
        int a2 = mid + 1;

        while (i < tmp.length) {
            if (a1 > mid) {
                tmp[i] = arr[a2];
                a2++;
            } else if (a2 > hi) {
                tmp[i] = arr[a1];
                a1++;
            } else if (arr[a1] <= arr[a2]) {
                tmp[i] = arr[a1];
                a1++;
            } else if (arr[a1] > arr[a2]){
                tmp[i] = arr[a2];
                a2++;
            }
            i++;
        }

        for (int k = lo; k <= hi; k++) {
            arr[k] = tmp[k - lo];
        }
    }
}
