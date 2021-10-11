package fb;

import java.util.Arrays;
// Add any extra import statements you may need here

class SlowSums {

    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new SlowSums().run();
    }

    /*
    a = [4, 2, 1, 3] -> 4 + 3 = 7
        [7, 2, 1]    -> 7 + 2 = 9
        [9, 1]       -> 9 + 1 = 10
                                --
                                26

    a = [4, 2, 1, 3] -> 1 + 2 = 3
        [3, 4, 3]    -> 3 + 4 = 7
        [7, 3]       -> 7 + 3 = 10
                                --
                                20

        [4, 2, 1, 3]
        [4, 3, 2, 1] -> max1 + max2 ->

    */
    int getTotalTime(int[] arr) {
        // Write your code here
        if (arr.length < 2) {
            return 0;
        }
        if (arr.length == 2) {
            return arr[0] + arr[1];
        }

        int penalties = 0;
        Arrays.sort(arr); //natural

        for (int i = arr.length - 2; i >= 0; i--) {
            int sum = arr[i] + arr[i + 1];
            penalties += sum;
            arr[i + 1] = 0;
            arr[i] = sum;
        }

        return penalties;
    }

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {
        int[] arr_1 = { 4, 2, 1, 3 };
        int expected_1 = 26;
        int output_1 = getTotalTime(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = { 2, 3, 9, 8, 4 };
        int expected_2 = 88;
        int output_2 = getTotalTime(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
