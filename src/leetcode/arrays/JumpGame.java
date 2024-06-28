package leetcode.arrays;

public class JumpGame {

    public static void main(String[] args) {
        System.out.println(new JumpGame().canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(new JumpGame().canJump(new int[]{3, 2, 1, 0, 4}));
    }

    public boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int i = 0;
        int el;
        while (i < nums.length) {
            el = nums[i];
            if (el == 0) {
                return false;
            }

            int maxPossibleJump = -1;
            int maxPossibleJumpIdx = -1;
            for (int j = i + 1; j <= Math.min(nums.length - 1, i + el); j++) {
                int hops = j - i;
                if (hops + nums[j] > maxPossibleJump) {
                    maxPossibleJump = hops + nums[j];
                    maxPossibleJumpIdx = j;
                }
            }
            if (i + maxPossibleJump >= nums.length - 1) {
                return true;
            }
            i = maxPossibleJumpIdx;
        }
        return false;
    }
}
