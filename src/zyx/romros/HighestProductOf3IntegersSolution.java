package zyx.romros;

public class HighestProductOf3IntegersSolution {

  public static void main(String[] args) {
    //    int[] arrayOfInts = {1, 10, -5, 1, -100};
    int[] arrayOfInts = {1, 10, -50, 1, -10, -20};
    System.out.println(getHighestProductOf3Integers(arrayOfInts));
    System.out.println(getHighestProductOf3Integers2(arrayOfInts));
  }

  private static int getHighestProductOf3Integers(int[] arrayOfInts) {
    var high1 = arrayOfInts[0];
    var high2 = arrayOfInts[1];
    var high3 = arrayOfInts[2];
    for (int i = 3; i < arrayOfInts.length; i++) {
      int currHigh = arrayOfInts[i];

      final int currVsHigh1 = Math.max(currHigh, high1);
      if (currVsHigh1 > high1) {
        currHigh = high1;
        high1 = currVsHigh1;
      }

      final int currVsHigh2 = Math.max(currHigh, high2);
      if (currVsHigh2 > high2) {
        currHigh = high2;
        high2 = currVsHigh2;
      }
      final int currVsHigh3 = Math.max(currHigh, high3);
      if (currVsHigh3 > high3) {
        high3 = currVsHigh3;
      }
    }
    System.out.println(high1 + "," + high2 + "," + high3);
    return high1 * high2 * high3;
  }

  private static int getHighestProductOf3Integers2(int[] arrayOfInts) {
    int highestProductOf3 = 0;
    int highestProductOf2 = 0;
    int highest = arrayOfInts[0];
    int lowestProductOf2 = 0;
    int lowest = arrayOfInts[0];
    for (int i = 1; i < arrayOfInts.length; i++) {
      final int current = arrayOfInts[i];
      highestProductOf2 = Math.max(highestProductOf2, current * highest);
      lowestProductOf2 = Math.min(lowestProductOf2, current * lowest);

      highestProductOf3 = Math.max(highestProductOf3, highestProductOf2 * current);
      highestProductOf3 = Math.max(highestProductOf3, lowestProductOf2 * current);
      highest = Math.max(highest, current);
      lowest = Math.min(lowest, current);
    }

    return highestProductOf3;
  }

}
