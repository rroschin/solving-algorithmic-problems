package leetcode.arrays;

import java.util.Arrays;

class SingleNumber {

    public static int singleNumberWithSorting(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        Arrays.sort(nums);
        if (nums[0] != nums[1]) { //1 2 2 4 4
            return nums[0];
        }
        if (nums[nums.length - 1] != nums[nums.length - 2]) { //2 2 4 4 5
            return nums[nums.length - 1];
        }

        //2 2 1 1 3 4 4 6 6

        for (int i = 0; i < nums.length; i+=2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    public static int singleNumberWithExclusiveOr(int[] nums) {
        int answer = 0;
        for (final int num : nums) {
            answer = answer ^ num;
        }
        return answer;
    }

    public static int singleNumberWithMap(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] map = new int[30000 * 2];
        for (final int num : nums) {
            if (num < 0) {
                int el = -1 * num + 30000;
                map[el] += 1;
            } else {
                map[num] += 1;
            }
        }
        for (int i = 0; i < map.length; i++) {
            int el = map[i];
            if (el == 1) {
                if (i <= 30000) {
                    return i;
                } else {
                    return (i - 30000) * -1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 4, -1, 2, -1, 2 };
        System.out.println(singleNumberWithSorting(nums));
    }
}
