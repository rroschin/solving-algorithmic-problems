package leetcode.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String lcp = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(lcp)) {
                lcp = lcp.substring(0, lcp.length() - 1);
                i--;
            }
        }
        return lcp;
    }

    public static String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        int c = 0;
        String first = strs[0];
        String last = strs[strs.length - 1];

        while (c < first.length() && first.charAt(c) == last.charAt(c))
            c++;

        return c > 0 ? first.substring(0, c) : "";
    }

    public static String longestCommonPrefixTwoLoops(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        List<Character> prefix = new ArrayList<>();
        int j = 0;
        if (strs[0].length() == 0) {
            return "";
        }
        char toCheck = strs[0].charAt(j);
        boolean done = false;
        while (!done) {
            for (final String str : strs) {
                if (str.length() - 1 < j || str.charAt(j) != toCheck) {
                    done = true;
                    break;
                }
            }
            if (!done) {
                prefix.add(toCheck);
                j++;
                if (strs[0].length() == j) {
                    break;
                }
                toCheck = strs[0].charAt(j);
            }
        }

        final char[] chars = new char[prefix.size()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = prefix.get(i);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] {
                "flower", "flower", "flower", "flower"
        }));
        System.out.println(longestCommonPrefix(new String[] {
                "ab", "", ""
        }));
        System.out.println(longestCommonPrefix(new String[] {
                "flower", "flow", "flight"
        }));
        System.out.println(longestCommonPrefix(new String[] {
                "dog", "racecar", "car"
        }));
    }
}
