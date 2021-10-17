package fb;

import java.util.Arrays;
// Add any extra import statements you may need here

class BalancedSplit {

    // Add any helper functions you may need here


  /*

  arr = [1, 5, 7, 1]

  arr = [1, 1, 5, 7]
  sum = 1 + 1 + 5 + 7 = 14

  sumA = 0
  a   = []
  b   = [1, 1, 5, 7] = sum = 14
  --
  sumA = 1
  a   = [1]
  b   = [0, 1, 5, 7] = sum = 13
  if (sumA == sum) { true } -> false
  --
  sumA = 2
  a   = [1, 1]
  b   = [0, 0, 5, 7] = sum = 12
  if (sumA == sum) { true } -> false
  --
  sumA = 7
  a   = [1, 1, 5]
  b   = [0, 0, 0, 7] = sum = 7
  if (sumA == sum) { true } -> true
  -----------

  arr = [12, 7, 6, 7, 6]
  arr = [6, 6, 7, 7, 12]
  sum = 38
  --
  sumA = 0
  a   = []
  arr = [6, 6, 7, 7, 12]
  --
  sumA = 6
  a   = [6, ]
  arr = [0, 6, 7, 7, 12]
  sum = 32
  6 != 32
  --
  sumA = 12
  a   = [6, 6]
  arr = [0, 0, 7, 7, 12]
  sum = 26
  12 != 26
  --
  sumA = 19
  a   = [6, 6, 7]
  arr = [0, 0, 0, 7, 12]
  sum = 19
  19 != 19
  if (!(a[a.length -1] < arr[i])) {
    return false;
  }

  */

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new BalancedSplit().run();
    }

    boolean balancedSplitExists(int[] arr) {
        if (arr.length < 3) {
            return false;
        }

        Arrays.sort(arr); //O(nlogn)
        int[] a = new int[arr.length];
        int sumA = 0;

        int sumB = 0;
        for (Integer i : arr) {
            sumB += i;
        }

        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            a[j] = arr[i];
            arr[i] = 0;
            sumB -= a[j];
            sumA += a[j];
            if (sumB == sumA) {
                if (i < arr.length - 1 && a[j] < arr[i + 1]) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;

    }

    void check(boolean expected, boolean output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            System.out.print(expected);
            System.out.print(" Your output: ");
            System.out.print(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printString(String str) {
        System.out.print("[" + str + "]");
    }

    public void run() {
        int arr_1[] = { 2, 1, 2, 5 };
        boolean expected_1 = true;
        boolean output_1 = balancedSplitExists(arr_1);
        check(expected_1, output_1);

        int arr_2[] = { 3, 6, 3, 4, 4 };
        boolean expected_2 = false;
        boolean output_2 = balancedSplitExists(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
