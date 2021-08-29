package codility.gr;

import java.util.HashSet;
import java.util.Set;

public class Solution4 {

  public static void main(String[] args) {
    int[] A = {1, 2, 3, 4, 5, 6, 1, 7, 0};
    int K = 4;
    boolean has = hasDublicates(A, K);
    System.out.println(has);
  }

  private static boolean hasDublicates(int[] A, int K) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < A.length; i++) {
      final int curr = A[i];
      if (set.contains(curr)) {
        return true;
      }
      if (set.size() == 4) {
        set.remove(A[i - K]);
      }
      set.add(curr);
    }
    return false;
  }

}
