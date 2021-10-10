package fb;

import java.util.PriorityQueue;
// Add any extra import statements you may need here

class MedianStream {

    // Add any helper functions you may need here


  /*
   arr = [5, 15, 1, 3]
   out = [           ]
   i = 0, out[0] = arr[0]
   pq  = [5]
   out = [5,         ]
   i = 1, out[1] = (arr[0] + arr[1]) / 2
   pq  = [5, 15]
   out = [5, 10,     ]

   i = 2
   pq  = [1, 5, 15]
   out[2] = ps.size() == odd then pq[(ps.size() / 2 + 1)] else

   i = 3
   pq  = [1, 3, 5, 15]
   out[3] = ps.size() == even then pq[(ps.size() / 2 + 1)] else (pq[(ps.size() / 2)] + pq[(ps.size() / 2 - 1)]) / 2
  */

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new MedianStream().run();
    }

    int[] findMedian(int[] arr) {
        // Write your code here
        if (arr.length == 0) {
            return new int[0];
        }
        if (arr.length == 1) {
            return new int[] { arr[0] };
        }
        if (arr.length == 2) {
            return new int[] { arr[0], (arr[0] + arr[1]) / 2 };
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(); //natural order 1, 2, 3, ...
        int[] output = new int[arr.length];
        output[0] = arr[0];
        pq.add(arr[0]);
        output[1] = (arr[0] + arr[1]) / 2;
        pq.add(arr[1]);

        for (int i = 2; i < arr.length; i++) {
            int a = arr[i];
            pq.add(a);
            int[] pqArr = pqToArray(pq);
            int s = pq.size();
            if (s % 2 == 0) { //even
                output[i] = (pqArr[s / 2] + pqArr[s / 2 - 1]) / 2;
            } else { //odd
                output[i] = pqArr[s / 2];
            }
        }

        return output;
    }

    private int[] pqToArray(PriorityQueue<Integer> pq0) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(pq0);
        int[] a = new int[pq.size()];
        int i = 0;
        while (i < pq0.size()) {
            a[i] = pq.poll();
            i++;
        }
        return a;
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
        int[] arr_1 = { 5, 15, 1, 3 };
        int[] expected_1 = { 5, 10, 5, 4 };
        int[] output_1 = findMedian(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = { 2, 4, 7, 1, 5, 3 };
        int[] expected_2 = { 2, 3, 4, 3, 4, 3 };
        int[] output_2 = findMedian(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
