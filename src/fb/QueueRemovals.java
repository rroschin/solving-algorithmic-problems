package fb;

import java.util.LinkedList;
import java.util.Queue;
// Add any extra import statements you may need here

class QueueRemovals {

    // Add any helper functions you may need here


  /*

  out = [,,,,] //length == x (5)

  Qi  = [0, 1, 2, 3, 4, 5]
  Q   = [1, 2, 2, 3, 4, 5]
  arr = [1, 2, 2, 3, 4, 5]
  i1:

  max = -1;
  maxi = -1;
  repeat x5: Q.poll() -> max = max(max, Q.poll()) -> maxi = indexOf(max);
                         4                           5
  out[i1] = maxi -> out = [5,,,,]
  [1, 2, 2, 3, 4, 5] -> [5] -> [5, 0, 1, 1, 2]



  i5:

  int x1 = x;
  while (x1 > 0) {



    x1--;
  }

  */

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new QueueRemovals().run();
    }

    int[] findPositions(int[] arr, int x) {
        // Write your code here
        if (x < 1 || arr.length == 0) {
            return new int[0];
        }

        int[] out = new int[x];

        Queue<Integer> qi = new LinkedList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            q.add(arr[i]);
            qi.add(i);
        }

        int x1 = 0;
        while (x1 < x) {

            int max = -1;
            int imax = -1;
            int i = 0;
            int[] local = new int[Math.min(x, q.size())];
            int[] ilocal = new int[local.length];
            while (i < local.length) {
                local[i] = q.poll();
                ilocal[i] = qi.poll();
                if (max < local[i]) {
                    max = local[i];
                    imax = ilocal[i];
                }
                i++;
            }

            boolean removedMax = false;
            for (int j = 0; j < local.length; j++) {
                if (!removedMax && local[j] == max) {
                    out[x1] = imax + 1;
                    removedMax = true;
                    continue; //remove max from the Q
                }
                q.add(Math.max(0, local[j] - 1));
                qi.add(ilocal[j]);
            }

            x1++;
        }

        return out;
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
        int n_1 = 6;
        int x_1 = 5;
        int[] arr_1 = { 1, 2, 2, 3, 4, 5 };
        int[] expected_1 = { 5, 6, 4, 1, 2 };
        int[] output_1 = findPositions(arr_1, x_1);
        check(expected_1, output_1);

        int n_2 = 13;
        int x_2 = 4;
        int[] arr_2 = { 2, 4, 2, 4, 3, 1, 2, 2, 3, 4, 3, 4, 4 };
        int[] expected_2 = { 2, 5, 10, 13 };
        int[] output_2 = findPositions(arr_2, x_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
