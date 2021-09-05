package leetcode.strings;

import java.util.HashMap;
import java.util.Map;

class ValidAnagram {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> smap = new HashMap<>();
        Map<Character, Integer> tmap = new HashMap<>();
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            smap.merge(sArray[i], 1, Integer::sum);
            tmap.merge(tArray[i], 1, Integer::sum);
        }

        return smap.equals(tmap);
    }

    public static boolean isAnagramWithArray(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            counter[sArray[i] - 'a']++;
            counter[tArray[i] - 'a']--;
        }
        for (final int j : counter) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagramWithArray("anagram", "nagaram"));
        System.out.println(isAnagramWithArray("rat", "car"));
    }
}
