package zyx.romros;

import java.util.ArrayList;
import java.util.List;

class PalindromeSubStrings {

    public static void main(String[] args) {
        System.out.println(new PalindromeSubStrings().findAllPalindromeSubstrings("aabbbaa")); //7
    }

    public int findAllPalindromeSubstrings(String input) {
        List<String> palindroms = new ArrayList<>();
        int count = 0;

        char[] chars = input.toCharArray();

        String curr = "";
        for (int i = 0; i < chars.length; i++) {
            curr += chars[i];
            for (int j = i + 1; j < chars.length; j++) {
                curr += chars[j];
                if (isPalindrome(curr.toCharArray())) {
                    palindroms.add(curr);
                    count++;
                }
            }
            curr = "";
        }

        return count;
    }

    boolean isPalindrome(char[] chars) {
        if (chars.length < 2) {
            return false;
        }

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }

        return true;
    }

}
