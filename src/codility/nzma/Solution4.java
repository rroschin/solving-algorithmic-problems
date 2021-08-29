package codility.nzma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution4 {

  List<Integer> lengthEachScene(List<Character> inputList) {
    List<Integer> res = new ArrayList<>();

    Map<Character, Integer> repeatables = new HashMap<>();
    for (int i = 0; i < inputList.size(); i++) {
      final Character c = inputList.get(i);
      final Integer cnt = repeatables.get(c);
      if (cnt == null) {
        repeatables.put(c, 1);
      } else {
        repeatables.put(c, cnt + 1);
      }
    }

    int start = 0;
    int end = 0;
    for (int i = 0; i < inputList.size(); i++) {
      final Character curr = inputList.get(i);
      final Integer cnt = repeatables.get(curr);
      if (cnt != null && cnt > 1) { //repeatable
        start = i;
        final Data data = lastIndexOf(curr, inputList, start);
        end = data.lastIndex;
        if (!data.set.isEmpty()) {
          List<Data> otherChars = new ArrayList<>();
          for (Character character : data.set) {
            otherChars.add(lastIndexOf(character, inputList, start));
          }
          for (Data otherChar : otherChars) {
            end = Math.max(end, otherChar.lastIndex);
          }
        }
        res.add(end - start + 1);
        start = end + 1;
        i = start - 1;
      } else {
        res.add(1);
      }
    }

    return res;
  }

  Data lastIndexOf(Character c, List<Character> inputList, int start) {
    Data data = new Data();
    for (int i = start; i < inputList.size(); i++) {
      final Character curr = inputList.get(i);
      if (curr == c) {
        data.lastIndex = i;
      }
    }

    for (int i = start; i < data.lastIndex; i++) {
      data.set.add(inputList.get(i));
    }
    data.set.remove(c);
    return data;
  }

  static class Data {

    int lastIndex = 0;
    Set<Character> set = new HashSet<>();

  }

}
