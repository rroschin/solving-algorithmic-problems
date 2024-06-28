package leetcode.arrays;

public class JumpGame2 {

    public static void main(String[] args) {
        System.out.println(new JumpGame2().jump(new int[]{7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3}));
        System.out.println(new JumpGame2().jump(new int[]{3, 4, 3, 2, 5, 4, 3}));
        System.out.println(new JumpGame2().jump(new int[]{2, 2, 0, 4, 3}));
        System.out.println(new JumpGame2().jump(new int[]{1, 2}));
        System.out.println(new JumpGame2().jump(new int[]{1, 3, 2}));
        System.out.println(new JumpGame2().jump(new int[]{1, 2, 3}));
        System.out.println(new JumpGame2().jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(new JumpGame2().jump(new int[]{2, 3, 1, 0, 4}));
    }

    public int jump(int[] nums) {
        if (nums.length == 0 || nums[0] == 0) {
            return 0;
        }
        if (nums[0] >= nums.length - 1) {
            return 1;
        }

        int jumps = 0;

        int total = 0;
        int i = 0;

        while (total < nums.length - 1) {
            int iVal = nums[i];

            int bestJump = 0;
            int bestJumpIdx = 0;
            for (int j = i + 1; j <= i + iVal; j++) {
                if (j == nums.length - 1) {
                    return ++jumps;
                }
                if (bestJump <= j - i + nums[j]) {
                    bestJump = j - i + nums[j];
                    bestJumpIdx = j;
                }
            }

            jumps++;
            total += bestJumpIdx - i;
            i = bestJumpIdx;
        }

        return jumps;
    }
}
