package leetcode;

import java.util.HashMap;
import java.util.Map;

class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring().minWindow("ab", "a")); //a
        System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC")); //"BANC"
    }

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        if (s.equals(t)) {
            return s;
        }

        Map<Character, Integer> mapT = new HashMap<>(); //{A:1, B:1, C:1}
        for (char c : t.toCharArray()) {
            mapT.merge(c, 1, Integer::sum);
        }

        String min = "";

        Map<Character, Integer> mapS = new HashMap<>();
        int start = 0;
        for (int end = 0; end <= s.length(); end++) {

            while (end < s.length() && !isIncluded(mapS, mapT)) {
                char c = s.charAt(end);
                mapS.merge(c, 1, Integer::sum);
                end++;
            }
            if (isIncluded(mapS, mapT)) {
                String res = s.substring(start, end);
                if ("".equals(min) || min.length() > res.length()) {
                    min = res;
                }
                Integer charCount = mapS.get(s.charAt(start));
                if (charCount == 1) {
                    mapS.remove(s.charAt(start));
                } else {
                    mapS.put(s.charAt(start), charCount - 1);
                }
                start++;
                end--;
            }
        }

        return min;
    }

    boolean isIncluded(Map<Character, Integer> mapS, Map<Character, Integer> mapT) {
        for (Character ct : mapT.keySet()) {
            Integer is = mapS.get(ct);
            if (is == null) {
                return false;
            }
            if (mapT.get(ct) > is) {
                return false;
            }
        }
        return true;
    }
}
