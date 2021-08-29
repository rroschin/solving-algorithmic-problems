package zyx.romros;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AnyPermutationIsPalindromeSolution {

  public static void main(String[] args) {
    String str = "civic";
    boolean is = isAnyPermutationIsPalindromeSolution(str.toCharArray());
    System.out.println(is);
  }

  private static boolean isAnyPermutationIsPalindromeSolution(char[] charArray) {
    Map<Character, Integer> chars = new HashMap<>();
    for (char c : charArray) {
      chars.merge(c, 1, Integer::sum);
    }

    boolean wasOneOdd = false;
    for (Entry<Character, Integer> el : chars.entrySet()) {
      if (el.getValue() % 2 != 0) {
        if (wasOneOdd) {
          return false;
        } else {
          wasOneOdd = true;
        }
      }
    }

    return true;
  }


}
