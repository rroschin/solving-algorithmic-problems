package leetcode.topinterviewquestionseasy.arrays;

import java.util.Arrays;

class PlusOne {
    public static int[] plusOneCheck9(int[] digits) {
        if (digits[digits.length - 1] != 9) {
            digits[digits.length - 1] += 1;
            return digits;
        } else {
            boolean all9s = true;
            for (final int digit : digits) {
                all9s = all9s && digit == 9;
            }
            if (all9s) {
                int[] plusedDigits = new int[digits.length + 1];
                plusedDigits[0] = 1;
                return plusedDigits;
            } else {
                for (int i = digits.length - 1; i >= 0; i--) {
                    if (digits[i] == 9) {
                        digits[i] = 0;
                    } else {
                        digits[i] += 1;
                        return digits;
                    }
                }
            }
        }
        return digits;
    }

    public static void main(String[] args) {
        //        int[] digits = { 1, 2, 3 };
//        int[] digits = { 1, 2, 3, 9 }; // -> 1239 + 1 = 1240
//        int[] digits = { 1, 2, 3, 9, 9, 9 }; // -> 123999 + 1 = 124000
        int[] digits = { 9, 9 }; // -> 99 + 1 = 100


        System.out.println(Arrays.toString(plusOneCheck9(digits)));
    }
}
