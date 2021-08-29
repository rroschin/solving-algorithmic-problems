package codility.gr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Solution5 {

  public static void main(String[] args) {
    System.out.println(isAnagramMap("Steer", Set.of("Trees")));
    System.out.println(isAnagramMap("Steer", Set.of("Street")));
    System.out.println(isAnagramMap("Steer", Set.of("Trees", "Street", "Ardvark")));
    System.out.println(isAnagramMap("Stress", Set.of("Trees", "Street", "Ardvark")));

    System.out.println();

    System.out.println(isAnagramArray("Steer", Set.of("Trees")));
    System.out.println(isAnagramArray("Steer", Set.of("Street")));
    System.out.println(isAnagramArray("Steer", Set.of("Trees", "Street", "Ardvark")));
    System.out.println(isAnagramArray("Stress", Set.of("Trees", "Street", "Ardvark")));

    System.out.println();

    System.out.println(isAnagramSum("Steer", Set.of("Trees")));
    System.out.println(isAnagramSum("Steer", Set.of("Street")));
    System.out.println(isAnagramSum("Steer", Set.of("Trees", "Street", "Ardvark")));
    System.out.println(isAnagramSum("Stress", Set.of("Trees", "Street", "Ardvark")));
  }

  public static boolean isAnagramSum(final String candidate, final Set<String> dictionary) {
    final char[] letters = candidate.toLowerCase().toCharArray();
    int sum1 = 0;
    for (int i = 0; i < letters.length; i++) {
      int pos = letters[i];
      sum1 += pos;
    }

    for (String wordStr : dictionary) {
      final char[] word = wordStr.toLowerCase().toCharArray();
      int sum2 = 0;
      for (int i = 0; i < word.length; i++) {
        int pos = word[i];
        sum2 += pos;
      }
      if (sum1 == sum2) {
        return true;
      }
    }

    return false;
  }

  public static boolean isAnagramArray(final String candidate, final Set<String> dictionary) {
    final char[] letters = candidate.toLowerCase().toCharArray();
    int[] map = new int[256];
    for (int i = 0; i < letters.length; i++) {
      int pos = letters[i];
      map[pos]++;
    }

    for (String wordStr : dictionary) {
      int[] copy = Arrays.copyOf(map, map.length);
      final char[] word = wordStr.toLowerCase().toCharArray();
      for (int i = 0; i < word.length; i++) {
        int pos = word[i];
        copy[pos]--;
      }
      int sum = 0;
      for (int i = 0; i < copy.length; i++) {
        sum += copy[i];
      }
      if (sum == 0) {
        return true;
      }
    }
    return false;
  }

  public static boolean isAnagramMap(final String candidate, final Set<String> dictionary) {

    //if (candidate == null) { false

    Map<String, Integer> map = new HashMap<>();
    // s - 1
    // t - 1
    // e - 2
    // r - 1
    for (char c : candidate.toLowerCase().toCharArray()) { //O(n)
      final String letter = String.valueOf(c); //time
      final Integer cnt = map.get(letter);
      if (Objects.nonNull(cnt)) {
        map.put(letter, cnt + 1);
      } else {
        map.put(letter, 1);
      }
    }

    for (String s : dictionary) { //O(m)
      Map<String, Integer> map2 = new HashMap<>();
      for (char c : s.toLowerCase().toCharArray()) { //O(k)
        final String letter = String.valueOf(c);
        if (map2.containsKey(letter)) {
          final Integer cnt = map2.get(letter);
          map2.put(letter, cnt + 1);
        } else {
          map2.put(letter, 1);
        }
      }
      if (map2.equals(map)) { //O(J)
        return true;
      }
    }
    return false;
  }

  //O(m*k)
}
