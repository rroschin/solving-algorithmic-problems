package leetcode.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class MajorityElement {

    public static void main(String[] args) {
        System.out.println(new MajorityElement().majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        //opt 1 - sorted array
        int option1 = majorityElementWithSorting(nums); //O(n log_n)

        //opt 2 - store in map
        int option2 = majorityElementWithMap(nums); //O(n) time, O(n) space

        //opt 3
        int option3 = majorityElementWithMoore(nums); //O(n) time, O(1) space
        return option3;
    }

    int majorityElementWithSorting(int[] nums) {
        Arrays.sort(nums);
        int k = nums.length / 2;

        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                max++;
            } else {
                max = 1;
            }
            if (max > k) {
                return nums[i];
            }
        }
        return -1;
    }

    int majorityElementWithMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        int k = nums.length / 2;
        int res = -1;
        int max = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                res = entry.getKey();
            }
            if (max > k) {
                return res;
            }
        }
        return -1;
    }

    int majorityElementWithMoore(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (candidate == num) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
