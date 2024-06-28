package fb;

import leetcode.TreeNode;

public class BstInsert {
    public static void main(String[] args) {
        TreeNode root =
                new TreeNode(4,
                        new TreeNode(2,
                                new TreeNode(1, null, null),
                                new TreeNode(3, null, null)),
                        new TreeNode(7));


        new BstInsert().insert(root, 5);
        root.printTree();

        new BstInsert().insert(root, 6);
        root.printTree();

        new BstInsert().insert(root, 0);
        root.printTree();
    }

    private void insert(TreeNode node, int el) {
        if (node == null) {
            return;
        }

        if (el < node.val) {
            if (node.left == null) {
                node.left = new TreeNode(el);
            } else {
                insert(node.left, el);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(el);
            } else {
                insert(node.right, el);
            }
        }
    }

}
