package amzn;

import leetcode.TreeNode;

public class NetworkSignalPropagation {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(5,
                new TreeNode(3),
                new TreeNode(7));
        root1.printTree();
        System.out.println(new NetworkSignalPropagation().calculateNumberOfSignals(root1));

        System.out.println("----");
        TreeNode root2 = new TreeNode(5,
                new TreeNode(3),
                new TreeNode(7,
                        new TreeNode(6),
                        new TreeNode(11)
                ));
        root2.printTree();
        System.out.println(new NetworkSignalPropagation().calculateNumberOfSignals(root2));

        System.out.println("----");
        TreeNode root3 = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2), null),
                new TreeNode(7,
                        new TreeNode(6),
                        new TreeNode(11)
                ));
        root3.printTree();
        System.out.println(new NetworkSignalPropagation().calculateNumberOfSignals(root3));

        System.out.println("----");
        TreeNode root4 = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2,
                                new TreeNode(1),
                                null
                        ), null),
                new TreeNode(7,
                        new TreeNode(6),
                        new TreeNode(11,
                                new TreeNode(10),
                                new TreeNode(12))
                ));
        root4.printTree();
        System.out.println(new NetworkSignalPropagation().calculateNumberOfSignals(root4));
    }

    private int calculateNumberOfSignals(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        return walk(root);
    }

    private int walk(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        int left = 0;
        if (node.left != null) {
            left = walk(node.left) + 1;
        }
        int right = 0;
        if (node.right != null) {
            right = walk(node.right) + 1;
        }

        int value;
        if (left == right) {
            value = left == 0 ? 0 : left + 1;
        } else {
            value = Math.max(left, right);
        }

        return value;
    }
}
