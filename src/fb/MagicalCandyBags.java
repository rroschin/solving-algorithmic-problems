package fb;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
// Add any extra import statements you may need here

class MagicalCandyBags {

    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new MagicalCandyBags().run();
    }

    int maxCandies(int[] arr, int k) {
        if (arr.length == 0 || k < 1) {
            return 0;
        }
        int maxCandies = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (final int j : arr) {
            pq.add(j);
        }
        while (k > 0) {
            final Integer max = pq.poll();
            maxCandies += max;
            pq.add(max / 2);
            k--;
        }

        return maxCandies;
    }

    int maxCandiesLoop(int[] arr, int k) {
        if (arr.length == 0 || k < 1) {
            return 0;
        }
        int maxCandies = 0;
        if (arr.length == 1) {
            while (k > 0) {
                maxCandies += arr[0];
                arr[0] = arr[0] / 2;
                k--;
            }
            return maxCandies;
        }

        //arr = [2, 1, 7, 4, 2]
        Arrays.sort(arr);
        //arr = [1, 2, 2, 4, 7] asc
    /*
    max = 0
    --
    k = 3
    n = 7
    max += 7 = 7
    n = 7/2 = 3 ?where to put you
    arr = [1, 2, 2, 4, 3]
    --
    k = 2
    n = if (3 > 4) -> take 3 else 4 (i = i OR i - 1); -> 4
    max += 4 = 11
    n = 4/2 = 2
    arr = [1, 2, 2, 2, 3]
    */

        int n = arr.length - 1;
        int currMax = arr[0];
        int currJ = 0;
        while (k > 0) {
            // Write your code here
            for (int j = n; j > 0; j--) {
                if (arr[j] >= arr[j - 1]) {
                    if (currJ > 0 && arr[currJ - 1] > arr[j]) {
                        j = currJ;
                        continue;
                    }
                    currMax = arr[j];
                    currJ = j;
                    break;
                }
            }
            maxCandies += currMax;
            arr[currJ] = currMax / 2;

            k--;
        }
        return maxCandies;
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
        int n_1 = 5, k_1 = 3;
        int[] arr_1 = { 2, 1, 7, 4, 2 };
        int expected_1 = 14;
        int output_1 = maxCandies(arr_1, k_1);
        check(expected_1, output_1);

        int n_2 = 9, k_2 = 3;
        int[] arr_2 = { 19, 78, 76, 72, 48, 8, 24, 74, 29 };
        int expected_2 = 228;
        int output_2 = maxCandies(arr_2, k_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
