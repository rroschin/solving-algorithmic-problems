package fb;

// Add any extra import statements you may need here

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ChangeInForeignCurrency {

    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new ChangeInForeignCurrency().run();
    }

    /*

    d = [5, 10, 25, 50, 100, 200]
    t = 94
    1. sort(d)
    2. d1 = d[i] < t -> [5, 10, 25, 50] //50+50>94 -> 50+25<94+25>94+10<94+10
                        [5, 10, 25] //25<t -> 25+25<t -> 25+25+25<t -> 25+25+25+25>t -> 25+25+25+10<t -> 25+25+25+10+10>t -> 25+25+25+10+5<t ->
                        25+25+25+10+5+5>t -> false





    t = 90
    t = 95


    --
    d = [4, 17, 29]
    t = 75
    29<t -> 29+29<t -> 29+29+29>t -> 29+29+17==t -> true

    [6, 91, 100]
    196
    100<t -> 100+100>t -> 100+91<t -> 100+91+91>t -> 100+91+6>t -> 100+

    91<t -> 91+91>t -> 91+6>t -> 6


    */
    boolean canGetExactChange(int targetMoney, int[] denominations) {
        Arrays.sort(denominations);
        List<Integer> d = new ArrayList<>();
        for (int denomination : denominations) {
            if (denomination < targetMoney) {
                d.add(denomination);
            }
        }

        if (d.isEmpty()) {
            return false;
        }
        return exchange(targetMoney, d);
    }

    private boolean exchange(final int targetMoney, final List<Integer> denominations) {
        if (targetMoney < 0)
            return false;
        if (targetMoney == 0)
            return true;
        for (final int denomination : denominations) {
            if (exchange(targetMoney - denomination, denominations)) {
                return true;
            }
        }
        return false;
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
        System.out.print("[\"" + str + "\"]");
    }

    public void run() {
        int target_1 = 94;
        int arr_1[] = { 5, 10, 25, 100, 200 };
        boolean expected_1 = false;
        boolean output_1 = canGetExactChange(target_1, arr_1);
        check(expected_1, output_1);

        int target_2 = 75;
        int arr_2[] = { 4, 17, 29 };
        boolean expected_2 = true;
        boolean output_2 = canGetExactChange(target_2, arr_2);
        check(expected_2, output_2);

        int target_3 = 196;
        int arr_3[] = { 6, 91, 100 };
        boolean expected_3 = true;
        boolean output_3 = canGetExactChange(target_3, arr_3);
        check(expected_3, output_3);

        int target_4 = 96;
        int arr_4[] = { 6, 91, 100 };
        boolean expected_4 = true;
        boolean output_4 = canGetExactChange(target_4, arr_4);
        check(expected_4, output_4);

    }
}
