package leetcode.topinterviewquestionseasy.sortingandsearching;

import java.util.Arrays;

class MergeSortedArray {

    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (k >= 0) {
            if (i >= 0 && j >= 0) {
                if (nums1[i] >= nums2[j])
                    nums1[k--] = nums1[i--];
                else
                    nums1[k--] = nums2[j--];
            } else if (i >= 0)
                nums1[k--] = nums1[i--];
            else if (j >= 0)
                nums1[k--] = nums2[j--];
        }
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
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
        int[] nums1 = { 1,2,7,8,0,0,0 };
        merge(nums1, 4, new int[] { 2,5,6 }, 3);
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

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2.length == 0) {
            return;
        }

        int p1 = m - 1; //3
        int p2 = n - 1; //2
        int i = nums1.length - 1; //6

        while (i >= 0) {
            if (p1 < 0) {
                nums1[i] = nums2[p2];
                p2--;
            } else if (p2 < 0) {
                nums1[i] = nums1[p1];
                p1--;
            } else if (nums1[p1] >= nums2[p2]) { //8 & 6, 7 & 6, 2 & 6
                nums1[i] = nums1[p1]; //[7,8]
                p1--; //2, 1
            } else {
                nums1[i] = nums2[p2];
                p2--;
            }
            i--;
        }
    }
}
