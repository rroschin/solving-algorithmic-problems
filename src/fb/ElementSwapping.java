package fb;

// Add any extra import statements you may need here

class ElementSwapping {

    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new ElementSwapping().run();
    }

    /*
    arr = [9, 7, 1, 4]
    k = 1
    out = [9, 1, 7, 4]
    k = 2
    out = [1, 9, 7, 4]
    k = 3
    out = [1, 9, 4, 7]
    k = 4
    out = [1, 4, 9, 7]
    k = 5
    out = [1, 4, 7, 9]
    */
    int[] findMinArray(int[] arr, int k) {
        // Write your code here
        if (arr.length < 2 || k == 0) {
            return arr;
        }
        if (arr.length == 2) {
            return arr[0] <= arr[1] ? arr : new int[] { arr[1], arr[0] };
        }
        //at least 3 elements in arr and k >= 1

        int imin = findNextMinIndex(arr, 0, Math.min(k, arr.length - 1));

        while (k > 0) {
            if (imin > 0 && arr[imin] < arr[imin - 1]) {
                swap(arr, imin, imin - 1);
                imin -= 1;
            } else {
                imin = findNextMinIndex(arr, imin + 1, Math.min(k + imin + 1, arr.length - 1));
            }
            k--;
        }

        return arr;
    }

    private int findNextMinIndex(int[] arr, int start, int end) {
        int min = arr[start];
        int imin = start;
        for (int i = start + 1; i <= end; i++) {
            if (min >= arr[i]) {
                min = arr[i];
                imin = i;
            }
        }
        return imin;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    void check(int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printIntegerArray(int[] arr) {
        int len = arr.length;
        System.out.print("[");
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.print("]");
    }

    public void run() {
        int k_1 = 2;
        int[] arr_1 = { 5, 3, 1 };
        int[] expected_1 = { 1, 5, 3 };
        int[] output_1 = findMinArray(arr_1, k_1);
        check(expected_1, output_1);

        int k_2 = 3;
        int[] arr_2 = { 8, 9, 11, 2, 1 };
        int[] expected_2 = { 2, 8, 9, 11, 1 };
        int[] output_2 = findMinArray(arr_2, k_2);
        check(expected_2, output_2);

        int k_3 = 7;
        int[] arr_3 = { 9, 7, 1, 4 };
        int[] expected_3 = { 1, 4, 7, 9 };
        int[] output_3 = findMinArray(arr_3, k_3);
        check(expected_3, output_3);

        // Add your own test cases here

    }
}
