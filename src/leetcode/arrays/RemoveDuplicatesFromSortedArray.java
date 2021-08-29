package leetcode.arrays;

class RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static int removeDuplicates(int[] nums) {

        int last = nums[nums.length - 1];
        int k = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == last) {
                k++;
                break;
            }
            if (nums[i - 1] == nums[i]) { //should remove nums[i] and shift the array
                shift(nums, i);
                i--;
            } else {
                k++;
            }
        }
        return k;
    }

    private static void shift(int[] nums, int from) {
        for (int i = from; i < nums.length; i++) {
            if (i == nums.length - 1) {
                nums[i] = 0;
            } else {
                nums[i] = nums[i + 1];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }; // Input array
        int[] expectedNums = { 0, 1, 2, 3, 4 }; // The expected answer with correct length

        int k = removeDuplicates2(nums); // Calls your implementation

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }

}
