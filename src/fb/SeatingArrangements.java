package fb;

import java.util.Arrays;
// Add any extra import statements you may need here


class SeatingArrangements {

  // Add any helper functions you may need here


  /*

  arr = [1, 100, 4, 22, 2, 5, 5, 9]
  99, 96, 18, 20, 3, 0, 4, 8 -> 99
  arr = [1, 2, 4, 5, 5, 22, 100, 9]
  1, 2, 1, 0, 17, 78, 91, 8 -> 91

  arr = [1, 2, 4, 5, 5, 22, 100, 9]
  arr = [1, 2, 4, 5, 5, 9, 22, 100]
  res = 100 - 1 = 99 - not good
  [1, ... 22,    100, 9]
          max-1  max  max - 2

  arr = [5, 10, 6, 8]
  arr = [5, 6, 8, 10]
  arr = [5, 8, 10, 6] -> 4


  arr = [5, 10, 5, 8]
  arr = [5, 5, 8, 10]
  arr = [5, 8, 10, 5] -> 5



  */
  int minOverallAwkwardness(int[] arr) {
    // Write your code here
    if (arr.length < 2) {
      return -1;
    }
    if (arr.length < 3) {
      return Math.abs(arr[0] - arr[1]);
    }

    Arrays.sort(arr);
    return arr[arr.length - 1] - arr[arr.length - 3];
  }












  // These are the tests we use to determine if the solution is correct.
  // You can add your own at the bottom.
  int test_case_number = 1;
  void check(int expected, int output) {
    boolean result = (expected == output);
    char rightTick = '\u2713';
    char wrongTick = '\u2717';
    if (result) {
      System.out.println(rightTick + " Test #" + test_case_number);
    }
    else {
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
    int[] arr_1 = {5, 10, 6, 8};
    int expected_1 = 4;
    int output_1 = minOverallAwkwardness(arr_1);
    check(expected_1, output_1);

    int[] arr_2 = {1, 2, 5, 3, 7};
    int expected_2 = 4;
    int output_2 = minOverallAwkwardness(arr_2);
    check(expected_2, output_2);

    // Add your own test cases here

  }

  public static void main(String[] args) {
    new SeatingArrangements().run();
  }
}
