package leetcode.topinterviewquestionseasy.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int desired = target - nums[i];
            if (map.containsKey(desired)) {
                return new int[] { map.get(desired), i };
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[2];
    }

    public static int[] twoSumWithMap(int[] nums, int target) {
        if (nums.length == 2) {
            return new int[] { 0, 1 };
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (final int num : nums) {
            map.put(target - num, num);
        }

        int e1Idx = -1;
        int e2Idx = -1;
        int e1 = 0;
        int e2 = 0;
        for (int i = 0; i < nums.length; i++) {
            final Integer c = map.get(nums[i]);
            if (c != null && (e1Idx == -1 || (e1 != c && target / 2 != c))) {
                e1Idx = i;
                e1 = nums[i];
                e2 = c;
                continue;
            }
            if (e1Idx != -1 && e2 == nums[i]) {
                e2Idx = i;
                break;
            }
        }
        return new int[] { e1Idx, e2Idx };
    }

    public static int[] twoSumN2(int[] nums, int target) {
        if (nums.length == 2) {
            return new int[] { 0, 1 };
        }

        int[] sum = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                if (i == i1) {
                    continue;
                }
                if (nums[i] + nums[i1] == target) {
                    return new int[] { i, i1 };
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        //        int[] nums = { 1, 2, 7, 11, 15 };
        //        int target = 9;
        int[] nums = { 3, 2, 4 };
        int target = 6;
        //                int[] nums = { 3, 2, 3 };
        //                int target = 6;
        //        int[] nums = { -1, -2, -3, -4, -5 };
        //        int target = -8;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}
