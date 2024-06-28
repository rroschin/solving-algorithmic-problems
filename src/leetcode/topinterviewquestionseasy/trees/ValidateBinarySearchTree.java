package leetcode.topinterviewquestionseasy.trees;

import leetcode.TreeNode;

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

    public static boolean isBst(TreeNode root) { //5
        return checkNode(root, null, null);
    }

    static boolean checkNode(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if (max != null && node.val > max) {
            return false;
        }
        if (min != null && node.val < min) {
            return false;
        }


        if (!checkNode(node.left, min, node.val)) {
            return false;
        }

        if (!checkNode(node.right, node.val, max)) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(4), new TreeNode(6, new TreeNode(3), new TreeNode(7)));
        System.out.println(isValidBSTWithMinMax(root));

        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(isValidBST(root1) + " vs " + isBst(root1));

        TreeNode root2 = new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        System.out.println(isValidBST(root2) + " vs " + isBst(root2));

        TreeNode root3 = new TreeNode(5, new TreeNode(4), new TreeNode(6, new TreeNode(3), new TreeNode(7)));
        System.out.println(isValidBST(root3) + " vs " + isBst(root3));

        TreeNode root4 = new TreeNode(5, new TreeNode(3, new TreeNode(6), new TreeNode(8)), new TreeNode(7, new TreeNode(6), new TreeNode(8)));
        System.out.println(isValidBST(root4) + " vs " + isBst(root4));

        TreeNode root5 = new TreeNode(2, new TreeNode(3), new TreeNode(3));
        System.out.println(isValidBST(root5) + " vs " + isBst(root5));
    }

}
