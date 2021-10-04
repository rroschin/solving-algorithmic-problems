package leetcode;

import java.util.Deque;
import java.util.LinkedList;

class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = { "2", "1", "+", "3", "*" };
        System.out.println(new EvaluateReversePolishNotation().evalRPN(tokens));
    }

    public int evalRPN(String[] tokens) {
        int result = 0;
        Deque<String> d = new LinkedList<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            boolean isOperator = isOperator(token);
            if (!isOperator) {
                d.push(token);
            } else {
                String num2 = d.pop();
                String num1 = d.pop();
                int calculated = calculate(num1, num2, token);
                d.push(Integer.toString(calculated));
            }

        }

        //[+,3,9,6,10]
        return result;
    }

    private int calculate(String num1, String num2, String operator) {
        int i_num1 = Integer.parseInt(num1);
        int i_num2 = Integer.parseInt(num2);

        if ("+".equals(operator)) {
            return i_num1 + i_num2;
        } else if ("-".equals(operator)) {
            return i_num1 - i_num2;
        } else if ("*".equals(operator)) {
            return i_num1 * i_num2;
        } else if ("/".equals(operator)) {
            return i_num1 / i_num2;
        } else {
            throw new RuntimeException();
        }
    }

    private boolean isOperator(String operator) {
        if ("+".equals(operator)) {
            return true;
        } else if ("-".equals(operator)) {
            return true;
        } else if ("*".equals(operator)) {
            return true;
        } else if ("/".equals(operator)) {
            return true;
        } else {
            return false;
        }
    }
}
