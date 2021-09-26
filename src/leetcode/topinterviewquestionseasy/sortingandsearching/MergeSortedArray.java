package leetcode.topinterviewquestionseasy.sortingandsearching;

import java.util.Arrays;

class MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int q = 0;
        int i = 0;
        int j = 0;
        while (i < m || j < n) {
            if (i == m) {
                nums1[q] = nums2[j];
                j++;
            } else if (nums1[i] <= nums2[j]) {
                i++;
            } else {
                int tmp = nums1[i];
                nums1[i] = nums2[j];
                nums2[j] = tmp;
                putMaxInPlace(nums2, j, tmp);
                i++;
            }
            q++;
        }
    }

    private static void putMaxInPlace(final int[] nums2, final int start, final int tmp) {
        for (int i = start + 1; i < nums2.length; i++) {
            if (tmp <= nums2[i]) {
                return;
            } else {
                int temp = nums2[i];
                nums2[i] = tmp;
                nums2[i - 1] = temp;
            }
        }
    }

    public static void mergeWithTempArray(int[] nums1, int m, int[] nums2, int n) {
        int[] nums = new int[m + n];

        int q = 0;
        int i = 0;
        int j = 0;
        while (i < m || j < n) {
            if (i == m) {
                nums[q] = nums2[j];
                j++;
            } else if (j == n) {
                nums[q] = nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                nums[q] = nums1[i];
                i++;
            } else {
                nums[q] = nums2[j];
                j++;
            }
            q++;
        }

        System.arraycopy(nums, 0, nums1, 0, nums.length);
    }

    public static void mergeWithPostSort(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m, j = 0; i < m + n; i++, j++) {
            nums1[i] = nums2[j];
        }
        Arrays.sort(nums1);
    }

    public static void main(String[] args) {
        int[] nums1 = { 0 };
        int m = 0;
        int[] nums2 = { 1 };
        int n = 1;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));

//        int[] nums1 = { 4, 5, 6, 0, 0, 0 };
//        int m = 3;
//        int[] nums2 = { 1, 2, 3 };
//        int n = 3;
//        merge(nums1, m, nums2, n);
//        System.out.println(Arrays.toString(nums1));

        //        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        //        int m = 3;
        //        int[] nums2 = { 2, 5, 6 };
        //        int n = 3;
        //        merge(nums1, m, nums2, n);
        //        System.out.println(Arrays.toString(nums1));
    }
}
