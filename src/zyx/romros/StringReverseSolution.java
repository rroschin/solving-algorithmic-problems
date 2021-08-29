package zyx.romros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringReverseSolution {

  public static void main(String[] args) {
    final char[] arrayOfChars = "HelloString".toCharArray();
    reverse(arrayOfChars);
    System.out.println(Arrays.toString(arrayOfChars));

    char[] message = {'c', 'a', 'k', 'e', ' ', 'p', 'o', 'u', 'n', 'd', ' ', 's', 't', 'e', 'a', 'l'};

    reverseWords(message);

    System.out.println(message);
    // prints: "steal pound cake"
  }

  private static void reverseWords(char[] message) {
    if (message.length == 1) {
      return;
    }
    reverse(message); //O(n)
    List<Integer> spacePos = new ArrayList<>();
    for (int i = 0; i < message.length; i++) { //O(n)
      if (message[i] == ' ') {
        spacePos.add(i);
      }
    }

    if (spacePos.isEmpty()) {
      reverse(message); //O(n)
      return;
    }

    int start = 0;
    for (int i = 0; i < spacePos.size(); i++) { //O(m)
      reverse(message, start, spacePos.get(i) -1 ); //O(n)
      start = spacePos.get(i) + 1;
    }
    reverse(message, start, message.length - 1);
  }

  private static void reverse(char[] arrayOfChars, int start, int end) {
    if (arrayOfChars.length == 1 || start == end || start == end + 1) {
      return;
    }
    for (int i = start; i < (start + end + 1) / 2; i++) {
      final char tmp = arrayOfChars[i];
      arrayOfChars[i] = arrayOfChars[(start + end) - i];
      arrayOfChars[(start + end) - i] = tmp;
    }
  }

  private static void reverse(char[] arrayOfChars) {
    if (arrayOfChars.length == 1) {
      return;
    }
    for (int i = 0; i < arrayOfChars.length / 2; i++) {
      final char tmp = arrayOfChars[i];
      arrayOfChars[i] = arrayOfChars[arrayOfChars.length - 1 - i];
      arrayOfChars[arrayOfChars.length - 1 - i] = tmp;
    }
  }

}
