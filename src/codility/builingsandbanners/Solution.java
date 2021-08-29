package codility.builingsandbanners;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  Map<Integer, Integer> maximumsBefore = new HashMap<>();
  Map<Integer, Integer> maximumsAfter = new HashMap<>();

  public int solution(int[] H) {

    int n = H.length;
    int maxHeight = getMaxHeight(H);
    processMaxHeightsAfter(H);

    int totalMin = maxHeight * n;


    for (int i = 1; i < H.length; i++) {

      int leftMaxHeight = getMaxHeightBefore(i);
      int rightMaxHeight = getMaxHeightAfter(i);

      int bannerLeft = leftMaxHeight * i;
      int bannerRight = rightMaxHeight * (n - i);

      int curMin = bannerLeft + bannerRight;
      if (curMin < totalMin) {
        totalMin = curMin;
      }
    }

    return totalMin;
  }

  private int getMaxHeightBefore(int index) {
    return maximumsBefore.get(index - 1);
  }

  private int getMaxHeightAfter(int index) {
    return maximumsAfter.get(index);
  }

  private int getMaxHeight(int[] h) {
    int max = 0;
    for (int i = 0; i < h.length; i++) {
      if (max < h[i]) {
        max = h[i];
      }
      maximumsBefore.put(i, max);
    }
    return max;
  }

  private void processMaxHeightsAfter(int[] h) {
    int max = 0;
    for (int i = h.length - 1; i >= 0; i--) {
      if (max < h[i]) {
        max = h[i];
      }
      maximumsAfter.put(i, max);
    }
  }

}
