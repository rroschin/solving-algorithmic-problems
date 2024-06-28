package fb;

import java.util.Arrays;

public class LeftRotation {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int d = 2;
        System.out.println(Arrays.toString(new LeftRotation().rotate(arr, d)));
    }

    private int[] rotate(int[] arr, int d) {
        int[] newarr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int el = arr[i];
            int pos = i + d + 1;
            newarr[pos % arr.length] = el;
        }

        return newarr;
    }
}
