package fb;

// Add any extra import statements you may need here

class RevenueMilestones {

    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new RevenueMilestones().run();
    }

    int[] getMilestoneDays(int[] revenues, int[] milestones) {
        // Write your code here
        //TODO add edge cases

        int[] res = new int[milestones.length];
        int[] r1 = new int[revenues.length];
        r1[0] = revenues[0];

        for (int i = 1; i < revenues.length; i++) {
            r1[i] = r1[i - 1] + revenues[i];
        }

        for (int j = 0; j < milestones.length; j++) { //O(k)
            res[j] = findClosestIndex(milestones[j], r1) + 1;
      /*
      for (int i = 0; i < r1.length; i++) { //O(n) -> O(logn)
        if (r1[i] >= milestones[j]) {
          res[j] = i + 1; //1-based index
          break;
        }
      }
      if (res[j] == 0) {
        res[j] = -1;
      }
      */
        }

        return res; //O(k*logn)
    }

    private int findClosestIndex(int target, int[] arr) { //arr is sorted asc

        int[] res = new int[1];
        res[0] = -2;
        find(target, arr, 0, arr.length, res);
        return res[0];
    }

    private void find(int target, int[] arr, int start, int end, int[] res) {
        if (start > end) {
            return;
        }
        int mid = (end - start) / 2 + start;

        if (arr[mid] >= target) {
            res[0] = mid;
            find(target, arr, start, mid - 1, res);
        } else {
            find(target, arr, mid + 1, end, res);
        }
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
        int revenues_1[] = { 100, 200, 300, 400, 500 };
        int milestones_1[] = { 300, 800, 1000, 1400 };
        int expected_1[] = { 2, 4, 4, 5 };
        int[] output_1 = getMilestoneDays(revenues_1, milestones_1);
        check(expected_1, output_1);

        int revenues_2[] = { 700, 800, 600, 400, 600, 700 };
        int milestones_2[] = { 3100, 2200, 800, 2100, 1000 };
        int expected_2[] = { 5, 4, 2, 3, 2 };
        int[] output_2 = getMilestoneDays(revenues_2, milestones_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
