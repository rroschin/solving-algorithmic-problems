package fb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// Add any extra import statements you may need here

class PassingYearbooks {

    // Add any helper functions you may need here


  /*
  arr = [1, 2]
  1:
  - signature
  - 1-1 = 0, 2-1 = 1

  arr = [s4,   s2,   s1,   s3]
  out = [b0s4, b0s2, b0s1, b0s3]
  1:
  - in
  out = [b0s4, b0s2, b0s1, b0s3]
  - sign
  out = [b1s4, b1s2,  b1s1, b1s3]
  arr = [s4,   s2,    s1,   s3]
  - move
  out = [b1s1, b1s2,  b1s3, b1s4]
  - lock
  out = [b1s1, b1s2*, b1s3, b1s4]
  arr = [s4,   s2,    s1,   s3]

  2:
  - in
  out = [b1s1, b1s2*, b1s3, b1s4]
  - sign
  out = [b2s1, b1s2*, b2s3, b2s4]
  arr = [s4,   s2,    s1,   s3]
  - move
  out = [b2s3, b1s2*, b2s4, b2s1]
  - lock
  out = [b2s3, b1s2*, b2s4, b2s1]
  arr = [s4,   s2,    s1,   s3]

  3:
  - in
  out = [b2s3, b1s2*, b2s4, b2s1]
  - sign
  out = [b3s3, b1s2*, b3s4, b3s1]
  arr = [s4,   s2,    s1,   s3]
  - move
  out = [b3s4, b1s2*, b3s1, b3s3]
  - lock
  out = [b3s4*, b1s2*, b3s1*, b3s3*]
  arr = [s4,    s2,    s1,    s3]

  4:
  - in
  ! ALL LOCKED -> return
  out = [3, 1, 3, 3]

  */

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) throws IOException {
        new PassingYearbooks().run();
    }

    int[] findSignatureCounts(int[] arr) {
        // Write your code here
        List<Yearbook> list = new ArrayList<>();
        for (int s : arr) {
            Yearbook yb = new Yearbook(s);
            list.add(yb);
        }

        int numOfLocked = 0;
        boolean firstRun = true;
        while (numOfLocked < arr.length) {
            List<Yearbook> tmpList = new ArrayList<>(list);
            for (int i = 0; i < arr.length; i++) {
                Yearbook yb = list.get(i);
                //in
                if (yb.isLocked) {
                    continue;
                }
                //check
                if (!firstRun && yb.studentId == arr[i]) {
                    yb.isLocked = true;
                    numOfLocked++;
                    continue;
                }
                //sign
                yb.numOfSignatures += 1;

                //pass
                tmpList.set(arr[i] - 1, yb);
            }
            list = tmpList;
            firstRun = false;
        }

        int[] output = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            output[i] = list.get(i).numOfSignatures;
        }

        return output;
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
        int[] arr_1 = { 2, 1 };
        int[] expected_1 = { 2, 2 };
        int[] output_1 = findSignatureCounts(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = { 1, 2 };
        int[] expected_2 = { 1, 1 };
        int[] output_2 = findSignatureCounts(arr_2);
        check(expected_2, output_2);

        int[] arr_3 = { 4, 2, 1, 3 };
        int[] expected_3 = { 3, 1, 3, 3 };
        int[] output_3 = findSignatureCounts(arr_3);
        check(expected_3, output_3);

    }

    class Yearbook {
        int studentId;
        int numOfSignatures = 0;
        boolean isLocked = false;

        public Yearbook(int studentId) {
            this.studentId = studentId;
        }

        @Override
        public String toString() {
            return "Yearbook{" +
                   "studentId=" + studentId +
                   ", numOfSignatures=" + numOfSignatures +
                   ", isLocked=" + isLocked +
                   '}';
        }
    }
}
