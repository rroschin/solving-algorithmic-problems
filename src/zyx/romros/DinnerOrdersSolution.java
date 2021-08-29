package zyx.romros;

public class DinnerOrdersSolution {

  public static void main(String[] args) {
    int[] servedOrders = {1, 2, 3, 4, 5, 6};

    boolean checked = isFirstInFirstOut(servedOrders);
    System.out.println(checked);
  }

  private static boolean isFirstInFirstOut(int[] servedOrders) {
    for (int i = 1; i < servedOrders.length; i++) {
      if (servedOrders[i] < servedOrders[i-1]) {
        return false;
      }
    }
    return true;
  }

}
