package zyx.romros;

import java.util.Arrays;

public class BinarySearchSolution {

  public static void main(final String[] args) {
  }


  private static int bsearch(final int[] a, final int o, final int i1, final int i2) {
    if (a[0] > o) {
      return -1;
    }
    if (a[a.length - 1] < o) {
      return -1;
    }
    if (a[0] == o) {
      return 0;
    }
    if (a[a.length - 1] == o) {
      return a.length - 1;
    }
    final int m = (i2 + i1) / 2;
    if (a[m] == o) {
      return m;
    }
    if (i1 == i2) {
      return -1;
    }
    if (a[m] < o) {
      return bsearch(a, o, m, i2);
    } else {
      return bsearch(a, o, i1, m);
    }
  }

  private static int[] mergeSort(final int[] arrayToSort) {

    // BASE CASE: arrays with fewer than 2 elements are sorted
    if (arrayToSort.length < 2) {
      return arrayToSort;
    }

    // STEP 1: divide the array in half
    // we use integer division, so we'll never get a "half index"
    final int midIndex = arrayToSort.length / 2;

    final int[] left = Arrays.copyOfRange(arrayToSort, 0, midIndex);
    final int[] right = Arrays.copyOfRange(arrayToSort, midIndex, arrayToSort.length);

    // STEP 2: sort each half
    final int[] sortedLeft = mergeSort(left);
    final int[] sortedRight = mergeSort(right);

    // STEP 3: merge the sorted halves
    final int[] sortedArray = new int[arrayToSort.length];

    int currentLeftIndex = 0;
    int currentRightIndex = 0;

    for (int currentSortedIndex = 0; currentSortedIndex < arrayToSort.length; currentSortedIndex++) {

      // sortedLeft's first element comes next
      // if it's less than sortedRight's first
      // element or if sortedRight is exhausted
      if (currentLeftIndex < sortedLeft.length && (currentRightIndex >= sortedRight.length
                                                   || sortedLeft[currentLeftIndex] < sortedRight[currentRightIndex])) {
        sortedArray[currentSortedIndex] = sortedLeft[currentLeftIndex];
        currentLeftIndex++;
      } else {
        sortedArray[currentSortedIndex] = sortedRight[currentRightIndex];
        currentRightIndex++;
      }
    }

    return sortedArray;
  }

}
