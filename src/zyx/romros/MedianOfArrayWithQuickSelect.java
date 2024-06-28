package zyx.romros;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MedianOfArrayWithQuickSelect {

    public static void main(String[] args) {
        System.out.println(new MedianOfArrayWithQuickSelect().findMedian1(new int[]{3, 6, 11, -5, 2, 10, 8}));
        System.out.println(new MedianOfArrayWithQuickSelect().findMedian(new int[]{3, 6, 11, -5, 2, 10, 8}));
        System.out.println(new MedianOfArrayWithQuickSelect().findMedian2(new int[]{3, 6, 11, -5, 2, 10, 8}));
        System.out.println(new MedianOfArrayWithQuickSelect().findMedian1(new int[]{3, 6, 11, -5, 2, 10, 8, -14}));
        System.out.println(new MedianOfArrayWithQuickSelect().findMedian(new int[]{3, 6, 11, -5, 2, 10, 8, -14}));
        System.out.println(new MedianOfArrayWithQuickSelect().findMedian2(new int[]{3, 6, 11, -5, 2, 10, 8, -14}));

    }

    private int findMedian2(int[] arr) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int el : arr) {
            heap.add(el);
        }

        int end = arr.length % 2 == 0 ? ((arr.length / 2) - 1) : arr.length / 2;
        for (int i = 0; i < end; i++) {
            heap.poll();
        }
        int med = heap.poll();
        if (arr.length % 2 == 0) {
            return (med + heap.poll()) / 2;
        } else {
            return med;
        }
    }

    private int findMedian1(int[] arr) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        if (arr.length % 2 == 0) {
            int mid = arr.length / 2;
            return (arr[mid - 1] + arr[mid]) / 2;
        } else {
            return arr[arr.length / 2];
        }
    }

    private int findMedian(int[] arr) {
        if (arr.length % 2 == 0) {
            int k2 = arr.length / 2;
            int k1 = k2 - 1;

            int med1 = quickSelect(arr, 0, arr.length - 1, k1);
            int med2 = quickSelect(arr, 0, arr.length - 1, k2);
            return (med2 + med1) / 2;
        } else {
            int k1 = arr.length / 2;

            int med1 = quickSelect(arr, 0, arr.length - 1, k1);
            return med1;
        }
    }

    int quickSelect(int[] arr, int lo, int hi, int targetIdx) {
        int pivotIdx = partition(arr, lo, hi);

        if (pivotIdx == targetIdx) {
            return arr[pivotIdx];
        } else if (pivotIdx < targetIdx) {
            return quickSelect(arr, pivotIdx + 1, hi, targetIdx);
        } else {
            return quickSelect(arr, lo, pivotIdx - 1, targetIdx);
        }
    }

    int partition(int[] arr, int leftIdx, int rightIdx) {
        int pivotPos = rightIdx;
        int pivotVal = arr[pivotPos];
        rightIdx--;

        while (true) {
            while (leftIdx < arr.length && arr[leftIdx] < pivotVal) {
                leftIdx++;
            }
            while (rightIdx >= 0 && arr[rightIdx] > pivotVal) {
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

    void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
