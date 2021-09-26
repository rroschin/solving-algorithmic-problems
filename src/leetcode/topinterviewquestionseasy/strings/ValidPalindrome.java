package leetcode.topinterviewquestionseasy.strings;

class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        int asciiRangeOneStart = 48;
        int asciiRangeOneEnd = 57;
        int asciiRangeTwoStart = 97;
        int asciiRangeTwoEnd = 122;

        if (s.length() == 0 || s.length() == 1) {
            return true;
        }

        final char[] chars = s.toLowerCase().toCharArray();

        int[] forward = new int[s.length()];
        int forwardIdx = 0;
        for (int sym : chars) {
            if ((asciiRangeOneStart <= sym && sym <= asciiRangeOneEnd) || (asciiRangeTwoStart <= sym && sym <= asciiRangeTwoEnd)) { //valid
                forward[forwardIdx] = sym;
                forwardIdx++;
            }
        }

        for (int i = 0; i < forwardIdx / 2; i++) {
            if (forward[i] != forward[forwardIdx - 1 - i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }
}
