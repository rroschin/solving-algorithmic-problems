package zyx.romros;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new InsertionSort().sort(new int[]{14, 8, 12, 6, 2, 0, 11, 23, 1, 7})));
    }

    private int[] sort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int pos = i;
            int tmp = arr[i];

            while (pos > 0 && arr[pos - 1] > tmp) {
                arr[pos] = arr[pos - 1];
                pos = pos - 1;
            }
            arr[pos] = tmp;
        }

        return arr;
    }


}
