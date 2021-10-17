package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MaximumLengthOfConcatenatedStringWithUniqueCharacters {

    public static void main(String[] args) {
        System.out.println(new MaximumLengthOfConcatenatedStringWithUniqueCharacters().maxLength(List.of("un", "iq", "ue")));
    }

    public int maxLength(List<String> arr) {

        int[] max = new int[1];
        combine(arr, 0, "", max);
        return max[0];
    }

    private void combine(List<String> arr, int index, String s, int[] max) {
        if (index == arr.size()) {
            if (allUnique(s) && s.length() > max[0]) {
                max[0] = s.length();
            }
            return;
        }

        combine(arr, index + 1, s, max);
        combine(arr, index + 1, s + arr.get(index), max);
    }

    private boolean allUnique(String s) {
        Set<Character> unique = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (unique.contains(c)) {
                return false;
            } else {
                unique.add(c);
            }
        }
        return true;
    }
}
