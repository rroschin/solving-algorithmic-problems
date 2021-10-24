package zyx.romros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MirrorBT {

    public static void main(String[] args) {
        final BinaryTreeNode root = new BinaryTreeNode(20,
                                                       new BinaryTreeNode(50, new BinaryTreeNode(75), new BinaryTreeNode(25)),
                                                       new BinaryTreeNode(200, null, new BinaryTreeNode(300)));
        new MirrorBT().mirror_binary_tree(root);

        BTreePrinter.printNode(root);
    }

    public void mirror_binary_tree(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        BinaryTreeNode left = root.left;
        BinaryTreeNode right = root.right;

        mirror_binary_tree(root.left);
        mirror_binary_tree(root.right);

        root.left = right;
        root.right = left;
    }

    static class BinaryTreeNode {
        int val;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(final int val) {
            this.val = val;
        }

        public BinaryTreeNode(final int val, final BinaryTreeNode left, final BinaryTreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /* SO copy-paste */
    static class BTreePrinter {

        public static void printNode(BinaryTreeNode root) {
            int maxLevel = BTreePrinter.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static void printNodeInternal(List<BinaryTreeNode> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<BinaryTreeNode> newNodes = new ArrayList<>();
            for (BinaryTreeNode BinaryTreeNode : nodes) {
                if (BinaryTreeNode != null) {
                    System.out.print(BinaryTreeNode.val);
                    newNodes.add(BinaryTreeNode.left);
                    newNodes.add(BinaryTreeNode.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println();

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println();
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static int maxLevel(BinaryTreeNode BinaryTreeNode) {
            if (BinaryTreeNode == null)
                return 0;

            return Math.max(BTreePrinter.maxLevel(BinaryTreeNode.left), BTreePrinter.maxLevel(BinaryTreeNode.right)) + 1;
        }

        private static boolean isAllElementsNull(List<?> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }

            return true;
        }

    }
}
