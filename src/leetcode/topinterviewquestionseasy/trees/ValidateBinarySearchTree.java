package leetcode.topinterviewquestionseasy.trees;

/**
 * Definition for a binary tree node.
 */
class ValidateBinarySearchTree {

    private static TreeNode prev = null;

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isValidBST(root.left) == false) {
            return false;
        }
        if (prev != null && prev.val >= root.val) {
            return false;
        }
        prev = root;
        return isValidBST(root.right);
    }

    public static boolean isValidBSTWithMinMax(TreeNode root) {
        if (root == null) {
            return true;
        }

        return validate(root, null, null);
    }

    public static boolean validate(TreeNode node, Integer max, Integer min) {
        if (node == null) {
            return true;
        }
        boolean validLeft = true;
        boolean validRight = true;
        if (max != null && node.val >= max) {
            return false;
        }
        if (min != null && node.val <= min) {
            return false;
        }

        if (node.left != null) {
            validLeft = validate(node.left, node.val, min);
        }
        if (node.right != null) {
            validRight = validate(node.right, max, node.val);
        }
        return validLeft && validRight;
    }

    public static boolean isValidBST_Incomplete(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftValid = root.left == null || root.val > root.left.val;
        boolean rightValid = root.right == null || root.val < root.right.val;
        boolean isLevelValid = leftValid && rightValid;
        if (!isLevelValid) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(isValidBST(root1));

        TreeNode root2 = new TreeNode(5,
                                      new TreeNode(1), new TreeNode(4,
                                                                    new TreeNode(3), new TreeNode(6)));
        System.out.println(isValidBST(root2));

        TreeNode root3 = new TreeNode(5,
                                      new TreeNode(4), new TreeNode(6,
                                                                    new TreeNode(3), new TreeNode(7)));
        System.out.println(isValidBST(root3));
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
    }
}
