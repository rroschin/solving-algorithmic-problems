package zyx.romros;

import java.util.Arrays;

public class QuickSelect {

    public static void main(String[] args) {

        int[] arr1 = {4, 12, 6, 1, 0, 44, 21, 8};

        System.out.print(new QuickSelect().selectLowest(0, Arrays.copyOf(arr1, arr1.length)) + " ");
        System.out.print(new QuickSelect().selectLowest(1, Arrays.copyOf(arr1, arr1.length)) + " ");
        System.out.print(new QuickSelect().selectLowest(3, Arrays.copyOf(arr1, arr1.length)) + " ");
        System.out.print(new QuickSelect().selectLowest(7, Arrays.copyOf(arr1, arr1.length)) + " ");

        System.out.println();
        Arrays.sort(arr1);

        System.out.print(arr1[0] + " ");
        System.out.print(arr1[1] + " ");
        System.out.print(arr1[3] + " ");
        System.out.print(arr1[7] + " ");

        System.out.println();
        System.out.println(Arrays.toString(arr1));

        System.out.println("----");

        int[] arr2 = {4, 12, 6, 1, 0, 44, 21, 8};

        System.out.print(new QuickSelect().selectHighest(0, Arrays.copyOf(arr2, arr2.length)) + " ");
        System.out.print(new QuickSelect().selectHighest(1, Arrays.copyOf(arr2, arr2.length)) + " ");
        System.out.print(new QuickSelect().selectHighest(3, Arrays.copyOf(arr2, arr2.length)) + " ");
        System.out.print(new QuickSelect().selectHighest(7, Arrays.copyOf(arr2, arr2.length)) + " ");

        System.out.println();
        Arrays.sort(arr2);

        System.out.print(arr2[arr2.length - 1 - 0] + " ");
        System.out.print(arr2[arr2.length - 1 - 1] + " ");
        System.out.print(arr2[arr2.length - 1 - 3] + " ");
        System.out.print(arr2[arr2.length - 1 - 7] + " ");

        System.out.println();
        System.out.println(Arrays.toString(arr2));
    }

    private int selectLowest(int lowestPos, int[] arr) {
        return quickSelectLowest(lowestPos, 0, arr.length - 1, arr);
    }

    private int quickSelectLowest(int lowestPos, int leftIdx, int rightIdx, int[] arr) {
        if (rightIdx - leftIdx <= 0) {
            return arr[leftIdx];
        }

        int pivotPos = partition(leftIdx, rightIdx, arr);
        if (pivotPos == lowestPos) {
            return arr[pivotPos];
        } else if (pivotPos > lowestPos) {
            return quickSelectLowest(lowestPos, leftIdx, pivotPos - 1, arr);
        } else if (pivotPos < lowestPos) {
            return quickSelectLowest(lowestPos, pivotPos + 1, rightIdx, arr);
        }

        return arr[pivotPos];
    }

    private int selectHighest(int highestPos, int[] arr) {
        return quickSelectHighest(highestPos, 0, arr.length - 1, arr);
    }

    private int quickSelectHighest(int highestPos, int leftIdx, int rightIdx, int[] arr) {
        if (rightIdx - leftIdx <= 0) {
            return arr[leftIdx];
        }

        int pivotPos = partition(leftIdx, rightIdx, arr);
        int highestInvertedPos = arr.length - 1 - highestPos;
        if (pivotPos == highestInvertedPos) {
            return arr[pivotPos];
        } else if (pivotPos < highestInvertedPos) {
            return quickSelectHighest(highestPos, pivotPos + 1, rightIdx, arr);
        } else if (pivotPos > highestInvertedPos) {
            return quickSelectHighest(highestPos, leftIdx, pivotPos - 1, arr);
        }

        return arr[pivotPos];
    }

    private int partition2(int leftIdx, int rightIdx, int[] arr) {
        int pivot = arr[rightIdx];
        int idx = leftIdx - 1;

        for (int i = leftIdx; i < rightIdx; i++) {
            if (arr[i] <= pivot) {
                idx++;
                swap(idx, i, arr);
            }
        }
        idx++;
        swap(idx, rightIdx, arr);
        return idx;
    }

    private int partition(int leftIdx, int rightIdx, int[] arr) {
        int pivotPos = rightIdx;
        int pivotVal = arr[pivotPos];
        rightIdx--;

        while (true) {
            while (arr[leftIdx] < pivotVal) {
                leftIdx++;
            }
            while (arr[rightIdx] > pivotVal) {
                rightIdx--;
            }

            if (leftIdx >= rightIdx) {
                break;
            } else {
                swap(leftIdx, rightIdx, arr);
            }
        }
        swap(pivotPos, leftIdx, arr);

        return leftIdx;
    }

    private void swap(int leftIdx, int rightIdx, int[] arr) {
        int tmp = arr[leftIdx];
        arr[leftIdx] = arr[rightIdx];
        arr[rightIdx] = tmp;
    }
}
