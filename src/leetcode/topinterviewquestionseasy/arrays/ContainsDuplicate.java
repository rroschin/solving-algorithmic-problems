package leetcode.topinterviewquestionseasy.arrays;

class ContainsDuplicate {

    public static boolean containsDuplicateWithMaxIntArray(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return false;
        }
        int[] map = new int[1000000000];
        for (int el : nums) {
            if (map[el] > 0) {
                return true;
            }
            map[el] += 1;
        }
        return false;
    }

    public static boolean containsDuplicateWithTwoLoops(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (i == j) {
                    continue;
                }
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsDuplicateWithSort(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return false;
        }
        java.util.Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsDuplicateWithSet(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return false;
        }
        java.util.Set<Integer> unique = new java.util.HashSet<>();
        for (final int num : nums) {
            if (unique.contains(num)) {
                return true;
            }
            unique.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,3,3,4,3,2,4,2};
        System.out.println(containsDuplicateWithMaxIntArray(nums));
    }
}
