package zyx.romros;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BubbleSort().sort(new int[]{14, 8, 12, 6, 2, 0, 11, 23, 1, 7})));
    }

    int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr;
    }
}
