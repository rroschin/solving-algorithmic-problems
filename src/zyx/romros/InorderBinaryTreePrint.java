package zyx.romros;

import leetcode.TreeNode;

public class InorderBinaryTreePrint {

    public static void main(String[] args) {
        TreeNode root = new ConstructBst().constructRandomTree();
        root.printTree();

        inorderPrint(root);
    }

    private static void inorderPrint(TreeNode node) {
        if (node == null) {
            return;
        }

        inorderPrint(node.left);
        System.out.println(node.val);
        inorderPrint(node.right);
    }

}
