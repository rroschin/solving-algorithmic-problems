package zyx.romros;

import java.util.Arrays;

public class MergeSortedArraysSolution {

  public static void main(String[] args) {
    int[] myArray = new int[]{3, 4, 6, 10, 11, 15, 20, 21, 22};
    int[] alicesArray = new int[]{1, 5, 8, 12, 14, 19};

    System.out.println(Arrays.toString(mergeArrays(myArray, alicesArray)));

    System.out.println(Arrays.toString(mergeArraysReview(myArray, alicesArray)));

    System.out.println(Arrays.toString(mergeArrays3(myArray, alicesArray)));
  }

  private static int[] mergeArrays(int[] myArray, int[] alicesArray) {
    final int[] res = new int[myArray.length + alicesArray.length];
    int resPos = 0;

    int pos1 = 0;
    int pos2 = 0;
    while (pos1 != myArray.length || pos2 != alicesArray.length) {
      if (pos1 == myArray.length) {
        res[resPos] = alicesArray[pos2];
        resPos++;
        pos2++;
      } else if (pos2 == alicesArray.length || myArray[pos1] < alicesArray[pos2]) {
        res[resPos] = myArray[pos1];
        resPos++;
        pos1++;
      } else {
        res[resPos] = alicesArray[pos2];
        resPos++;
        pos2++;
      }
    }

    return res;
  }

  private static int[] mergeArraysReview(int[] arrayOne, int[] arrayTwo) {
    int idxOne = 0;
    int idxTwo = 0;

    int[] merged = new int[arrayOne.length + arrayTwo.length];
    int idxMerged = 0;
    while (idxOne < arrayOne.length || idxTwo < arrayTwo.length) {
      boolean endedArrayOne = !(idxOne < arrayOne.length);
      boolean endedArrayTwo = !(idxTwo < arrayTwo.length);
      if (!endedArrayOne && !endedArrayTwo) {
        if (arrayOne[idxOne] < arrayTwo[idxTwo]) {
          merged[idxMerged] = arrayOne[idxOne];
          idxMerged++;
          idxOne++;
        } else {
          merged[idxMerged] = arrayTwo[idxTwo];
          idxMerged++;
          idxTwo++;
        }
      } else if (!endedArrayOne) {
        merged[idxMerged] = arrayOne[idxOne];
        idxMerged++;
        idxOne++;
      } else if (!endedArrayTwo) {
        merged[idxMerged] = arrayTwo[idxTwo];
        idxMerged++;
        idxTwo++;
      }
    }
    return merged;
  }

  private static int[] mergeArrays3(int[] arr1, int[] arr2) {
    int[] res = new int[arr1.length + arr2.length];
    int iRes = 0;

    int iArr1 = 0;
    int iArr2 = 0;

    while (iRes < res.length) {
      boolean hasItemsInArr1 = iArr1 < arr1.length;
      boolean hasItemsInArr2 = iArr2 < arr2.length;

      if (hasItemsInArr1 && hasItemsInArr2 && arr1[iArr1] < arr2[iArr2]) {
        res[iRes] = arr1[iArr1];
        iArr1++;
      } else if (hasItemsInArr1 && hasItemsInArr2 && arr1[iArr1] >= arr2[iArr2]) {
        res[iRes] = arr2[iArr2];
        iArr2++;
      } else if (hasItemsInArr1 && !hasItemsInArr2) {
        res[iRes] = arr1[iArr1];
        iArr1++;
      } else if (!hasItemsInArr1 && hasItemsInArr2) {
        res[iRes] = arr2[iArr2];
        iArr2++;
      }
      iRes++;
    }

    return res;
  }

}
