package leetcode.topinterviewquestionseasy.strings;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacterInString {

    public static int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            final char c = chars[i];
            if (map.containsKey(c)) {
                map.put(c, -1);
            } else {
                map.put(c, i);
            }
        }

        int minIdx = Integer.MAX_VALUE;
        for (final Map.Entry<Character, Integer> ci : map.entrySet()) {
            if (ci.getValue() != -1 && minIdx > ci.getValue()) {
                minIdx = ci.getValue();
            }
        }
        if (minIdx == Integer.MAX_VALUE) {
            return -1;
        }
        return minIdx;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
        System.out.println(firstUniqChar("loveleetcode"));
        System.out.println(firstUniqChar("aabb"));
    }
}
