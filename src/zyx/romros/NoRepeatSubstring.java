package zyx.romros;

import java.util.*;

class NoRepeatSubstring {
  public static void main(String[] args) {
    System.out.println(findLength("aabccbb")); //3
    System.out.println(findLength("abbbb")); //2
    System.out.println(findLength("abccde")); //3
  }

  public static int findLength(String str) {
    if (str.length() <= 1) {
      return str.length();
    }

    int max = Integer.MIN_VALUE;
    Set<Character> set = new HashSet<>();
    int start = 0;
    for (int end = 0; end < str.length(); end++) {
      while (end < str.length() && !set.contains(str.charAt(end))) {
        set.add(str.charAt(end));
        end++;
      }
      max = Math.max(max, set.size());
      set.remove(str.charAt(start));
      start++;
      end--;
    }
    max = Math.max(max, set.size());

    return max;
  }
}
