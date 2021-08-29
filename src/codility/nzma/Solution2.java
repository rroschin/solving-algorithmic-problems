package codility.nzma;

public class Solution2 {

  public int generalizedGCD(int num, int[] arr) {

    if (arr.length == 0) {
      return 1;
    }
    if (arr.length == 1) {
      return arr[0];
    }

    int min = Integer.MAX_VALUE;
    for (int value : arr) {
      min = Math.min(min, value);
    }

    boolean isMinCandidate = true;
    for (int value : arr) {
      if (value % min != 0) {
        isMinCandidate = false;
        break;
      }
    }
    if (isMinCandidate) {
      return min;
    }

    min--;
    while (min != 1) {
      isMinCandidate = true;
      for (int value : arr) {
        if (value % min != 0) {
          isMinCandidate = false;
          break;
        }
      }
      if (isMinCandidate) {
        return min;
      }
      min--;
    }

    return min;
  }

}
