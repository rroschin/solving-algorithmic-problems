package leetcode.topinterviewquestionseasy.strings;

import java.util.Arrays;

class ReverseString {
    public static void reverseString(char[] s) {
        if (s.length == 0 || s.length == 1) {
            return;
        }
        for (int i = 0; i < s.length / 2; i++) {
            int j = s.length - 1 - i;
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }

    public static void main(String[] args) {
        char[] s = { 'h', 'e', 'l', 'l', 'o' };
        reverseString(s);
        System.out.println(Arrays.toString(s));
    }
}
