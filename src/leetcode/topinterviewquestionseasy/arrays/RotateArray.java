package leetcode.topinterviewquestionseasy.arrays;

import java.util.Arrays;

class RotateArray {
    public static void rotate1(int[] nums, int k) {
        if (nums.length == 1) {
            return;
        }
        if (k == 0) {
            return;
        }
        while (k != 0) {
            int last = nums[nums.length - 1];
            System.arraycopy(nums, 0, nums, 1, nums.length - 1);
            nums[0] = last;

            k--;
        }

    }

    public static void rotate2(int[] nums, int k) {
        if (nums.length == 1) {
            return;
        }
        if (k == 0) {
            return;
        }
        while (k > nums.length) {
            k -= nums.length;
        }
        int[] copy = new int[k];
        System.arraycopy(nums, nums.length - k, copy, 0, k);
        System.arraycopy(nums, 0, nums, k, nums.length - k);
        System.arraycopy(copy, 0, nums, 0, copy.length);

        /*
        [1, 2, 3, 4, 5, 6, 7]; k = 3
        [5, 6, 7].length = k
        copy[0] = nums[4] => nums.length (7) - k (3) -> 4
        copy[1] = nums[5] => nums.length (7) - k (3) -> 5
        copy[2] = nums[6] => nums.length (7) - k (3) -> 6
         */
    }

    public static void rotate3(int[] nums, int k) {
        if (nums.length == 1) {
            return;
        }
        if (k == 0) {
            return;
        }
        while (k > nums.length) {
            k -= nums.length;
        }

        for (int i = 0; i < nums.length / 2; i++) {
            int tmp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = tmp;
        }

        for (int i = 0; i < k / 2; i++) {
            int tmp = nums[i];
            nums[i] = nums[k - 1 - i];
            nums[k - 1 - i] = tmp;
        }

        for (int i = 0; i < (nums.length - k) / 2; i++) {
            int j = i + k;
            int tmp = nums[j];
            nums[j] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        int k = 3;
//        int[] nums = { 1, 2 };
//        int k = 5;

        rotate3(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
