package fb;

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here


class MinimumLengthSubstrings {

  // Add any helper functions you may need here


  int minLengthSubstring(String s, String t) {
    // Write your code here
    if (s.equals(t)) {
      return s.length();
    }
    if (s.length() < t.length()) {
      return -1;
    }

    /*
    s = axdcbefdebce
    t = fdd -> {f: 1, d: 2}
    out = 6 (dcbefd)

    */
    Map<Character, Integer> sMap = new HashMap<>();
    Map<Character, Integer> tMap = new HashMap<>(); //{f: 1, d: 2}
    for (int i = 0; i < t.length(); i++) {
      tMap.merge(t.charAt(i), 1, Integer::sum);
    }

    int minLength = Integer.MAX_VALUE;
    char[] chars = s.toCharArray();

    int start = -1;
    for (int i = 0; i < chars.length; i++) {
      if (start == -1 && tMap.containsKey(chars[i])) { //found start
        start = i; //i = 2, c = d
      }
      if (start != -1) {
        sMap.merge(chars[i], 1, Integer::sum); //{d: 1}
        if (sMap.entrySet().containsAll(tMap.entrySet())) {
          minLength = Math.min(minLength, i - start + 1);
          sMap.clear();
          i = start + 1;
          start = -1;
        }
      }

    }
    return minLength == Integer.MAX_VALUE ? -1 : minLength;
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
  public void run() throws IOException {
    String s_1 = "dcbefebce";
    String t_1 = "fd";
    int expected_1 = 5;
    int output_1 = minLengthSubstring(s_1, t_1);
    check(expected_1, output_1);

    String s_2 = "bfbeadbcbcbfeaaeefcddcccbbbfaaafdbebedddf";
    String t_2 = "cbccfafebccdccebdd";
    int expected_2 = -1;
    int output_2 = minLengthSubstring(s_2, t_2);
    check(expected_2, output_2);

    // Add your own test cases here

  }
  public static void main(String[] args) throws IOException {
    new MinimumLengthSubstrings().run();
  }
}
