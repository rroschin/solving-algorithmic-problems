package leetcode.trees;

/**
 * Definition for a binary tree node.
 */
class SortedArray2BinarySearchTree {

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode head = helper(nums, 0, nums.length - 1);
        return head;
    }

    public static TreeNode helper(int[] num, int low, int high) {
        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper(num, low, mid - 1);
        node.right = helper(num, mid + 1, high);
        return node;
    }

    public static TreeNode sortedArrayToBSTWhileLoop(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        if (nums.length == 2) {
            return new TreeNode(nums[0], null, new TreeNode(nums[1]));
        }

        final int midIdx = nums.length / 2;
        int rootVal = nums[midIdx]; //TODO works for odd length, add support for even length
        TreeNode root = new TreeNode(rootVal);

        TreeNode left = root;
        TreeNode right = root;
        int i = midIdx - 1;
        int j = midIdx + 1;
        while (i >= 0 && j < nums.length) {
            left.left = new TreeNode(nums[i]);
            right.right = new TreeNode(nums[j]);

            left = left.left;
            right = right.right;

            i--;
            j++;
        }
        if (i == 0) {
            left.left = new TreeNode(nums[i]);
        }
        if (j == nums.length - 1) {
            right.right = new TreeNode(nums[j]);
        }

        return root;
    }

    public static void main(String[] args) {
        int[] nums1 = { -10, -3, 0, 5, 9 };
        System.out.println(sortedArrayToBST(nums1));

        int[] nums2 = { 1, 3 };
        System.out.println(sortedArrayToBST(nums2));

        int[] nums3 = { -10, -3, 0, 5, 9, 12 };
        System.out.println(sortedArrayToBST(nums3));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "val=" + val;
        }
    }
}
