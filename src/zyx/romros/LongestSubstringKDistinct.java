package zyx.romros;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class LongestSubstringKDistinct {
    public static int findLength(String str, int k) {
        if (str == null || str.length() == 0)
            throw new IllegalArgumentException();

        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        // in the following loop we'll try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);
            // shrink the sliding window, until we are left with 'k' distinct characters in the frequency map
            while (charFrequencyMap.size() > k) {
                char leftChar = str.charAt(windowStart);
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                if (charFrequencyMap.get(leftChar) == 0) {
                    charFrequencyMap.remove(leftChar);
                }
                windowStart++; // shrink the window
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // remember the maximum length so far
        }

        return maxLength;
    }

    public static int findLength1(String str, int k) {
        if (str.length() < k) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        char[] chars = str.toCharArray();

        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        while (start <= end) {
            while (end < chars.length && set.size() <= k) {
                set.add(chars[end]);
                end++;
            }
            end--;
            max = Math.max(max, end - start);
            set.remove(chars[start]);
            start++;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(findLength("araaci", 2));
    }
}
