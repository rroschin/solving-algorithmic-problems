package leetcode.topinterviewquestionseasy.dynamic;

class HouseRobber {
    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] max = new int[nums.length];
        max[0] = nums[0];
        max[1] = Math.max(max[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            max[i] = Math.max(nums[i] + max[i - 2], max[i - 1]);
        }
        return Math.max(max[nums.length - 1], max[nums.length - 2]);
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] { 1, 2, 3, 1 }));
        System.out.println(rob(new int[] { 2, 7, 9, 3, 1 }));
        System.out.println(rob(new int[] { 8, 7, 8, 10, 1 }));
    }
}
