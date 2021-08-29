package zyx.romros;

public class AppleStocksSolution {

  public static void main(String[] args) {
    int[] stockPrices = new int[]{10, 7, 5, 8, 11, 9};

    int profit = getMaxProfit(stockPrices);
    // Returns 6 (buying for $5 and selling for $11)
    System.out.println(profit);
  }

  private static int getMaxProfit(int[] stockPrices) {
    int iterations1 = 0;
    boolean isOn2Ok = false;
    int best = 0;
    for (int i = 0; i < stockPrices.length; i++) {
      for (int j = i; j < stockPrices.length; j++) {
        iterations1++;
        final int isBest = stockPrices[j] - stockPrices[i];
        if (isBest > best) {
          best = isBest;
        }
      }
    }
    System.out.println("iterations1 = " + iterations1);
    if (isOn2Ok && best > 0) {
      return best;
    }

    int iterations2 = 0;
    boolean isWhileOk = false;

    int currMax = 0;
    int buy = 0;
    int sell = 1;
    while (buy < sell && sell < stockPrices.length) {
      final int isMax = stockPrices[sell] - stockPrices[buy];
      if (currMax < isMax) {
        currMax = isMax;
      }
      sell++;
      if (sell == stockPrices.length) {
        buy++;
        sell = buy + 1;
      }

      iterations2++;
    }
    System.out.println("iterations2 = " + iterations2);
    if (isWhileOk && currMax > 0) {
      return currMax;
    }

    int iterations3 = 0;

    int bestProfit = 0;
    int minPrice = stockPrices[0];
    for (int i = 1; i < stockPrices.length; i++) {
      iterations3++;

      bestProfit = Math.max(bestProfit, stockPrices[i] - minPrice);
      minPrice = Math.min(minPrice, stockPrices[i]);
    }
    System.out.println("iterations3 = " + iterations3);
    return bestProfit;
  }

}
