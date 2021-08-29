package codility.nzma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1 {

  public List<Integer> cellCompete(int[] states, int days) {

    if (states.length == 0) {
      return Collections.emptyList();
    }

    List<Integer> res = new ArrayList<>(states.length);
    for (int state : states) {
      res.add(state);
    }

    int[] tmp = new int[states.length];
    System.arraycopy(states, 0, tmp, 0, states.length);

    int start = 0;
    while (start != days) {
      for (int i = 0; i < tmp.length; i++) {
        final int leftValue = (i == 0 ? 0 : tmp[i - 1]);
        final int rightValue = (i == tmp.length - 1 ? 0 : tmp[i + 1]);

        boolean shouldBeInactive = (leftValue == rightValue);
        res.set(i, shouldBeInactive ? 0 : 1);
      }
      for (int i = 0; i < res.size(); i++) {
        tmp[i] = res.get(i);
      }

      start++;
    }

    return res;
  }

}
