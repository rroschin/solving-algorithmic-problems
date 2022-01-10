package google;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class CoinChange {
    Map<Integer, Integer> amountDict = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[] { 1, 2, 5 }, 11));
        System.out.println(new CoinChange().coinChange(new int[] { 186, 419, 83, 408 }, 6249));
        System.out.println(new CoinChange().coinChange(new int[] { 2 }, 3));
        System.out.println(new CoinChange().coinChange(new int[] { 1 }, 0));
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins)
            for (int i = coin; i <= amount; i++)
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange1(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (amountDict.containsKey(amount))
            return amountDict.get(amount);
        int n = amount + 1;
        for (int coin : coins) {
            int curr = 0;
            if (amount >= coin) {
                int next = coinChange(coins, amount - coin);
                if (next >= 0)
                    curr = 1 + next;
            }
            if (curr > 0)
                n = Math.min(n, curr);
        }
        int finalCount = (n == amount + 1) ? -1 : n;
        amountDict.put(amount, finalCount);
        return finalCount;
    }
}
