package leetcode.arrays;

public class SearchInsertPosition {

    public static void main(String[] args) {
        System.out.println(new SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 0)); //0
        System.out.println(new SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 2)); //1
    }
    public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}
