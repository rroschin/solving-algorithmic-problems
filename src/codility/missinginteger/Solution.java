package codility.missinginteger;

import java.util.HashSet;
import java.util.Set;

public class Solution {

  public static void main(String[] args) {
    
  }

  public int solution(int[] A) {
    // write your code in Java SE 8
    Set<Integer> set = new HashSet<>();
    for (int i : A) {
      set.add(i);
    }
    for (int i = 1; i < 1_000_000; i++) {
      if (!set.contains(i)) {
        return i;
      }
    }
    return 1;
  }

}
