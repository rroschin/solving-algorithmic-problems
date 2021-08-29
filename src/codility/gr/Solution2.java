package codility.gr;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Solution2 {

  public int solution(int N) {
    final char[] chars = Integer.toString(N).toCharArray();

    List<String> list = new ArrayList<>(chars.length);
    for (int i = 0; i < chars.length; i++) {
      list.add(String.valueOf(chars[i]));
    }

    TreeSet<Integer> numbers = new TreeSet<>();
    String strToInsert = "5";
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == '-') {
        continue;
      }
      List<String> copylist = new ArrayList<>(list);
      copylist.add(i, strToInsert);
      final int newInt = Integer.parseInt(copylist.stream().collect(Collectors.joining()));
      numbers.add(newInt);
    }
    list.add(strToInsert);
    final int newInt = Integer.parseInt(list.stream().collect(Collectors.joining()));
    numbers.add(newInt);

    return numbers.last();
  }

  public int solution2(int N) {
    int intToInsert = 5;
    char charToInsert = '5';

    final char[] chars = Integer.toString(N).toCharArray();
    boolean isNegative = chars[0] == '-';

    if (!isNegative && chars.length == 1) {
      final int intAtPos = Integer.parseInt(String.valueOf(chars[0]));
      if (intAtPos > intToInsert) {
        return Integer.parseInt(String.valueOf(chars[0]) + charToInsert);
      }
    }
    if (isNegative && chars.length == 2) {
      final int intAtPos = Integer.parseInt(String.valueOf(chars[1]));
      if (intAtPos < intToInsert) {
        return -Integer.parseInt(String.valueOf(chars[1]) + charToInsert);
      }
    }

    char[] result = new char[chars.length + 1];
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == '-') {
        result[i] = chars[i];
        continue;
      }

      final int intAtPos = Integer.parseInt(String.valueOf(chars[i]));
      if (isNegative) {
        if (intAtPos >= intToInsert) {
          result[i] = charToInsert;
          copyTheRest(chars, i, result);
          break;
        } else {
          result[i] = chars[i];
        }

      } else {
        if (intAtPos <= intToInsert) {
          result[i] = charToInsert;
          copyTheRest(chars, i, result);
          break;
        } else {
          result[i] = chars[i];
        }
      }
    }

    return Integer.parseInt(new String(result));
  }

  private char[] copyTheRest(char[] chars, int i, char[] result) {
    for (int j = i; j < chars.length; j++) {
      result[j + 1] = chars[j];
    }
    return result;
  }

}
