package zyx.romros;

import java.util.Arrays;

public class ProductsOfAllIntsExceptAtIndexSolution {

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 0};
    System.out.println(Arrays.toString(getProductsOfAllIntsExceptAtIndex(arr)));
    System.out.println(Arrays.toString(getProductsOfAllIntsExceptAtIndex2(arr)));
    System.out.println(Arrays.toString(getProductsOfAllIntsExceptAtIndex3(arr)));
  }

  private static int[] getProductsOfAllIntsExceptAtIndex(int[] arr) {

    int[] pro = new int[arr.length];
    Arrays.fill(pro, 1);

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if (i != j) {
          pro[i] *= arr[j];
        }
      }
    }
    return pro; //O(n^2)
  }

  private static int[] getProductsOfAllIntsExceptAtIndex2(int[] arr) {
    int[] pro = new int[arr.length];
    int productSoFarBefore = 1;
    for (int i = 0; i < arr.length; i++) {
      pro[i] = productSoFarBefore;
      productSoFarBefore *= arr[i];
    }

    int productSoFarAfter = 1;
    for (int i = arr.length - 1; i >= 0; i--) {
      pro[i] = productSoFarAfter * pro[i];
      productSoFarAfter *= arr[i];
    }

    return pro;
  }

  private static int[] getProductsOfAllIntsExceptAtIndex3(int[] arr) {

    int[] pro = new int[arr.length];
    Arrays.fill(pro, 1);

    int product = 1;
    boolean containsZero = false;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != 0) {
        product *= arr[i];
      } else {
        containsZero = true;
      }
    }

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != 0) {
        if (containsZero) {
          pro[i] = 0;
        } else {
          pro[i] = product / arr[i];
        }
      } else {
        pro[i] = product;
      }
    }
    return pro;
  }

}
