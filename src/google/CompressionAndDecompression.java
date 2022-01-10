package google;

import java.util.ArrayDeque;
import java.util.Deque;

public class CompressionAndDecompression {

    public static void main(String[] args) {
        System.out.println(new CompressionAndDecompression().decomp("x2[3[a]b]")); //xaaabaaab
        System.out.println(new CompressionAndDecompression().decomp("x3[abc]4[ab]c")); //xabcabcabcababababc
        System.out.println(
            new CompressionAndDecompression().decomp("2[3[2[e]a]4[c]b]3[x]")); //eeaeeaeeaccccbeeaeeaeeaccccbxxx
    }

    String decomp(String comp) {
        StringBuilder res = new StringBuilder();
        StringBuilder digitStr = new StringBuilder();
        int digitInt = 0;

        StringBuilder substring = new StringBuilder();
        char[] charArray = comp.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (Character.isDigit(c)) {
                if (digitInt == 0) {
                    digitStr.append(c);
                } else {
                    final int closingBracket = findClosingBracket(comp, i);
                    substring.append(decomp(comp.substring(i, closingBracket + 1)));
                    i = closingBracket;
                }
            } else if (c == '[') {
                digitInt = Integer.parseInt(digitStr.toString());
                digitStr = new StringBuilder();
            } else if (c == ']') {
                res.append(repeat(digitInt, substring.toString()));
                substring = new StringBuilder();
                digitInt = 0;
            } else { //letter
                if (digitInt == 0) {
                    res.append(c);
                } else {
                    substring.append(c);
                }
            }
        }
        return res.toString();
    }

    private int findClosingBracket(final String comp, final int start) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] charArray = comp.toCharArray();
        for (int i = start; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '[') {
                stack.push(c);
            } else if (c == ']') {
                stack.pop();
                if (stack.isEmpty()) {
                    return i;
                }
            }
        }
        return comp.length();
    }

    private String repeat(final int digitInt, final String str) {
        return str.repeat(Math.max(0, digitInt));
    }
}
