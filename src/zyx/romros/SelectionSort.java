package zyx.romros;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SelectionSort().sort(new int[]{14, 8, 12, 6, 2, 0, 11, 23, 1, 7})));
    }

    private int[] sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int loIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[loIdx]) {
                    loIdx = j;
                }
            }
            if (loIdx != i) {
                int tmp = arr[i];
                arr[i] = arr[loIdx];
                arr[loIdx] = tmp;
            }
        }

        return arr;
    }


}
