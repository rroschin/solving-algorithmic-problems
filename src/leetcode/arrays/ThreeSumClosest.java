package leetcode.arrays;

public class ThreeSumClosest {

    public static void main(String[] args) {

        System.out.println(new ThreeSumClosest().threeSumClosest(new int[]{4, 0, 5, -5, 3, 3, 0, -4, -5}, -2)); //-2
    }

    public int threeSumClosest(int[] nums, int target) {

        if (nums.length < 3) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < nums.length; i++ ) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int diff = target - sum;

                    if (Math.abs(diff) < min) {
                        min = Math.abs(diff);
                        result = sum;
                    }
                }
            }
        }

        return result;
    }
}
