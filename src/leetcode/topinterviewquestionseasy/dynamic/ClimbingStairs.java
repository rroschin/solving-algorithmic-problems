package leetcode.topinterviewquestionseasy.dynamic;

class ClimbingStairs {

    public static int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        return fibLoop(n - 1);
    }

    private static int fibLoop(final int n) {
        if (n == 0) {
            return 1;
        }
        int prev2 = 1;
        int prev1 = 1;
        int i = 1;
        int fib = prev1 + prev2;
        while (i <= n) {
            fib = prev1 + prev2;
            prev2 = prev1;
            prev1 = fib;
            i++;
        }
        return fib;
    }

    private static int fibRec(final int n) {
        if (n <= 0) {
            return 1;
        }
        return fibRec(n - 1) + fibRec(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("2 -> " + climbStairs(2));
        System.out.println("3 -> " + climbStairs(3));
        System.out.println("5 -> " + climbStairs(5));
        System.out.println("6 -> " + climbStairs(6));
        System.out.println("45 -> " + climbStairs(45));
    }
}
