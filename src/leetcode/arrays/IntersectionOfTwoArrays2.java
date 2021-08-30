package leetcode.arrays;

import java.util.Arrays;

class IntersectionOfTwoArrays2 {

    public static int[] intersectSorted(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[] {};
        }
        int lnums1 = nums1.length;
        int lnums2 = nums2.length;
        int[] intersection = new int[Math.min(lnums1, lnums2)];

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // { 1, 4, 5, 9 };
        // { 4, 4, 7, 8, 9, 9 };

        int f = 0;
        int i1 = 0;
        int i2 = 0;
        boolean done = false;
        while (!done) {
            if (nums1[i1] == nums2[i2]) {
                intersection[f] = nums1[i1];
                f++;
                i1++;
                i2++;
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else if (nums1[i1] > nums2[i2]) {
                i2++;
            }

            done = (i1 == (lnums1) || i2 == (lnums2));
        }

        return Arrays.copyOfRange(intersection, 0, f);
    }

    public static int[] intersectTwoLoops(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[] {};
        }
        int lnums1 = nums1.length;
        int lnums2 = nums2.length;
        int[] intersection = new int[Math.min(lnums1, lnums2)];

        int[] used = new int[nums2.length];
        int f = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j] && used[j] != 1) {
                    intersection[f] = nums1[i];
                    f++;
                    used[j] += 1;
                    break;
                }
            }
        }

        int[] finalIntersection = new int[f];
        System.arraycopy(intersection, 0, finalIntersection, 0, f);
        return finalIntersection;
    }

    public static void main(String[] args) {
//        int[] nums1 = { 4, 9, 5 };
//        int[] nums2 = { 9, 4, 9, 8, 4 };
//                int[] nums1 = { 1,2,2,1 };
//                int[] nums2 = { 2 };
                int[] nums1 = { 2, 1 };
                int[] nums2 = { 1, 2 };
        System.out.println(Arrays.toString(intersectSorted(nums1, nums2)));
    }
}
