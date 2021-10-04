package leetcode.topinterviewquestionseasy.dynamic;

class BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1) {
            return 0;
        }

        int profit = 0;
        int[] mins = new int[prices.length];
        mins[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            mins[i] = Math.min(mins[i - 1], prices[i]);
            profit = Math.max(profit, prices[i] - mins[i]);
        }
        return Math.max(profit, 0);
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
        System.out.println(maxProfit(new int[] { 7, 6, 4, 3, 1 }));
    }
}
