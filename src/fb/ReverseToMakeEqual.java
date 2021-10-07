package fb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// Add any extra import statements you may need here

/*
Reverse to Make Equal
Given two arrays A and B of length N, determine if there is a way to make A equal to B by reversing any subarrays from array B any number of times.
Signature
bool areTheyEqual(int[] arr_a, int[] arr_b)
Input
All integers in array are in the range [0, 1,000,000,000].
Output
Return true if B can be made equal to A, return false otherwise.
Example
A = [1, 2, 3, 4]
B = [1, 4, 3, 2]
output = true
After reversing the subarray of B from indices 1 to 3, array B will equal array A.
 */
class ReverseToMakeEqual {

  /*
    edge cases:
    1. A == B
    2. A.length != B.length
    3. A == B == []
    4. A == B == [x]
    --
    pre-check:
    Map<Integer, Integer> map; //number-to-count: A {1: 3, 2: 1}, B {1: 2, 2: 2} -> A != B -> false

    --
    A = [1, 2, 3, 4]
    B = [1, 4, 3, 2]
    if A[i] == B[i] -> continue
    else A[i] != B[i] -> try to reverse until A[i] == B[i]

    1. [4, 3, 2] -> [2, 3, 4] //one action

    --
    A = [1, 2, 3, 4]
    B = [1, 4, 2, 3]
    1. [4, 2, 3] -> [1, [3, 2, 4]]
    2. [3, 2] -> [1, [2, 3], 4]
    2 steps, but possible
  */

    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new ReverseToMakeEqual().run();
    }

    boolean areTheyEqual(int[] array_a, int[] array_b) {
        // Write your code here
        //TODO add edge cases when done!!
        if (Arrays.equals(array_a, array_b)) {
            return true;
        }
        if (array_a.length != array_b.length) {
            return false;
        }

        Map<Integer, Integer> mapA = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();

        for (int i = 0; i < array_a.length; i++) {
            int a = array_a[i];
            int b = array_b[i];

            mapA.merge(a, 1, Integer::sum);
            mapB.merge(b, 1, Integer::sum);
        }

        return mapA.equals(mapB);
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

    public void run() {
        int[] array_a_1 = { 1, 2, 3, 4 };
        int[] array_b_1 = { 1, 4, 3, 2 };
        check(true, areTheyEqual(array_a_1, array_b_1));

        int[] array_a_2 = { 1, 2, 3, 4 };
        int[] array_b_2 = { 1, 4, 3, 3 };
        check(false, areTheyEqual(array_a_2, array_b_2));

        int[] array_a_3 = { 1, 2, 3, 4 };
        int[] array_b_3 = { 1, 4, 2, 3 };
        check(true, areTheyEqual(array_a_3, array_b_3));
    }
}
