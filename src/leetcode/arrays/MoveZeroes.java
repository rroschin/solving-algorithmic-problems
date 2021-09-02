package leetcode.arrays;

import java.util.Arrays;

class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int lastNonZeroLocation = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroLocation++] = nums[i];
            }
        }
        for (int i = lastNonZeroLocation; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void moveZeroesWithString(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (final int num : nums) {
            if (num != 0) {
                sb.append(num).append(" ");
            }
        }
        final String st = sb.toString();
        if (st.isEmpty()) {
            return;
        }
        Arrays.fill(nums, 0);
        final String[] s = st.split(" ");
        for (int i = 0; i < s.length; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }
    }

    public static void moveZeroesWithGoingBack(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != 0) {
                continue;
            } else if (i != nums.length - 1) {
                if (nums[i] == 0 && nums[i + 1] == 0) {
                    continue;
                }
                nums[i] = nums[i + 1];
                nums[i + 1] = 0;
                i = nums.length - 1;
            }
        }
    }

    public static void moveZeroesN2(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return;
        }

        int[] zeros = new int[nums.length];
        Arrays.fill(zeros, -1);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros[i] = 0;
            } else {
                int zeroIndex = -1;
                for (int j = 0; j < zeros.length; j++) {
                    if (zeros[j] == 0) {
                        zeroIndex = j;
                        break;
                    }
                }
                if (zeroIndex == -1) {
                    continue;
                }
                nums[zeroIndex] = nums[i];
                nums[i] = 0;
                zeros[i] = 0;
                zeros[zeroIndex] = -1;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0, 3, 12 }; //1, 0, 0, 3, 12
        //        int[] nums = { 1, 0, 1 }; //1, 0, 0, 3, 12
        //        int[] nums = { 0, 0 };
        //        int[] nums = { 1, 3, 12, 0, 0 };
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
