package fb;

// Add any extra import statements you may need here

class OneBillionUsers {

    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new OneBillionUsers().run();
    }

    /*

    users = 0;
    arr = [1, 2, 3]
    t = 1;
    1 ^1 + 2^1 + 3^1 = 1+2+3 = 6 -> 6 < 1_000_000_000 -> t++

    t = 2
    1 ^2 + 2^2 + 3^2 = 1+4+9 = 14 -> 6+14 < 1_000_000_000 -> t++
    ..


    */
    int getBillionUsersDay(float[] growthRates) {
        // Write your code here
        int t = 0;
        double users = 0;
        int maxUsers = 1_000_000_000;
        while (users < maxUsers) {
            t++;
            for (final float growthRate : growthRates) {
                double val = Math.pow(growthRate, t);
                users += val;
            }
        }

        return t;
    }

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {
        float[] test_1 = { 1.1f, 1.2f, 1.3f };
        int expected_1 = 79;
        int output_1 = getBillionUsersDay(test_1);
        check(expected_1, output_1);

        float[] test_2 = { 1.01f, 1.02f };
        int expected_2 = 1047;
        int output_2 = getBillionUsersDay(test_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
