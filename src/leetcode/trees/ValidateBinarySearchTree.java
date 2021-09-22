package leetcode.trees;

/**
 * Definition for a binary tree node.
 */
class ValidateBinarySearchTree {

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return validate(root, null, null);
    }

    public static boolean validate(TreeNode node, Integer max, Integer min) {
        if (node == null) {
            return true;
        } else if (node.left != null) {
            return validate(node.left, node.val, null);
        } else if (node.right != null) {
            return validate(node.right, null, node.val);
        } else {
            if (max != null) {
                return node.val < max;
            }
            if (min != null) {
                return node.val > min;
            }
            return true;
        }
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
