package fb;

import java.util.Deque;
import java.util.LinkedList;
// Add any extra import statements you may need here

class BalanceBrackets {
    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new BalanceBrackets().run();
    }

    boolean isBalanced(String s) {
        // Write your code here
        if (s.isEmpty()) {
            return true;
        }
        if (s.length() % 2 != 0) {
            return false;
        }

    /*
    open vs close
    1:
    {[()]}
    -> { - open  -> continue //{
    -> [ - open  -> continue //[ {
    -> ( - open  -> continue //( [ {
    -> ) - close -> find 'open' of same type:
    pop() -> ( == open == round -> good -> continue
    //[ {
    -> ] - close -> find 'open' of same type:
    pop() -> [ == open == square -> good -> continue

    2:
    {}()

    3:
    )

    4:
    {(})
    -> { - open - push() //{
    -> ( - open - push() //( {
    -> } - close - pop()
    ( == open != type -> fail!
    */

        char[] chars = s.toCharArray();
        Deque<Character> d = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '{' || chars[i] == '[') { //open
                d.push(chars[i]);
            } else {
                int openType = typeOf(d.pop());
                int closeType = typeOf(chars[i]);
                if (openType != closeType) {
                    return false;
                }
            }
        }

        return true;
    }

    private int typeOf(char b) {
        if (b == '(' || b == ')') {
            return 0;
        } else if (b == '{' || b == '}') {
            return 1;
        } else if (b == '[' || b == ']') {
            return 2;
        }
        return -1;
    }

    void check(boolean expected, boolean output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            System.out.print(expected);
            System.out.print(" Your output: ");
            System.out.print(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printString(String str) {
        System.out.print("[" + str + "]");
    }

    public void run() {
        String s_1 = "{[(])}";
        boolean expected_1 = false;
        boolean output_1 = isBalanced(s_1);
        check(expected_1, output_1);

        String s_2 = "{{[[(())]]}}";
        boolean expected_2 = true;
        boolean output_2 = isBalanced(s_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
