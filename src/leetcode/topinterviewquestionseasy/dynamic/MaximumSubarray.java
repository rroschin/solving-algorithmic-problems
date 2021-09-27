package leetcode.topinterviewquestionseasy.dynamic;

import java.util.ArrayList;
import java.util.List;

class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        List<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);
        int current_largest_sum = dp.get(0);
        for (int i = 1; i < nums.length; i++) {
            dp.add(Math.max(dp.get(i - 1) + nums[i], nums[i]));
            if (dp.get(i) > current_largest_sum) {
                current_largest_sum = dp.get(i);
            }
        }

        return current_largest_sum;
    }

    public static void main(String[] args) {
        int[] nums1 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(maxSubArray(nums1));

        int[] nums2 = { 5, 4, -1, 7, 8 };
        System.out.println(maxSubArray(nums2));

        int[] nums3 = { 1 };
        System.out.println(maxSubArray(nums3));
    }
}
