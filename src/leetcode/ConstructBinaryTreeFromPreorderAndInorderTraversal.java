package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 */
class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };
        new ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTree(preorder, inorder);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        Map<Integer, Integer> inorderIndexes = createInorderIndexes(inorder);
        int[] preorderIndex = { 0 };
        TreeNode head = arrayToTree(preorder, preorderIndex, inorderIndexes, 0, preorder.length - 1);
        return head;
    }

    TreeNode arrayToTree(int[] preorder, int[] preorderIndex, Map<Integer, Integer> inorderIndexes, int left, int right) {
        if (left > right ) {
            return null;
        }

        int rootInorderIndex = inorderIndexes.get(preorder[preorderIndex[0]]);
        TreeNode root = new TreeNode(preorder[preorderIndex[0]]);
        preorderIndex[0]++;
        root.left = arrayToTree(preorder, preorderIndex, inorderIndexes, left, rootInorderIndex - 1);
        root.right = arrayToTree(preorder, preorderIndex, inorderIndexes, rootInorderIndex + 1, right);

        return root;
    }

    Map<Integer, Integer> createInorderIndexes(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return map;
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
