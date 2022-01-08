package leetcode;

class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babad"));
    }

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int left = i;
            int right = i;

            while (left >= 0 && s.charAt(left) == c) {
                left--;
            }
            while (right < s.length() && s.charAt(right) == c) {
                right++;
            }
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left--;
                right++;
            }
            left += 1;
            if (end - start < right - left) {
                start = left;
                end = right;
            }
        }
        return s.substring(start, end);

    }

}
