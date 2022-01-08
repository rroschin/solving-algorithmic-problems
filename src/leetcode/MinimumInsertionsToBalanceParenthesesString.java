package leetcode;

import java.util.Deque;
import java.util.LinkedList;

class MinimumInsertionsToBalanceParenthesesString {
    public static void main(String[] args) {
        System.out.println(new MinimumInsertionsToBalanceParenthesesString().minInsertions("(()))"));
    }

    public int minInsertions(String s) {

        int minToAdd = 0;
        Deque<Character> stack = new LinkedList<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == '(') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) {
                minToAdd += 1;
            } else {
                stack.pop();
            }
            if (i + 1 == arr.length || arr[i + 1] != ')') {
                minToAdd += 1;
            } else {
                i++;
            }
        }
        if (!stack.isEmpty()) {
            minToAdd += stack.size() * 2;
        }

        return minToAdd;
    }
}
