package zyx.romros;

class IdenticalTrees {

    public static void main(String[] args) {
        System.out.println(new IdenticalTrees().areIdentical(new BinaryTreeNode(100,
                                                                                new BinaryTreeNode(50,
                                                                                                   new BinaryTreeNode(25), null),
                                                                                new BinaryTreeNode(200,
                                                                                                   new BinaryTreeNode(125),
                                                                                                   new BinaryTreeNode(350))
                                                             ),
                                                             new BinaryTreeNode(100,
                                                                                new BinaryTreeNode(50,
                                                                                                   null, new BinaryTreeNode(25)),
                                                                                new BinaryTreeNode(200,
                                                                                                   new BinaryTreeNode(125),
                                                                                                   new BinaryTreeNode(350))
                                                             )));

        System.out.println(new IdenticalTrees().areIdentical(new BinaryTreeNode(100,
                                                                                new BinaryTreeNode(50,
                                                                                                   new BinaryTreeNode(25), null),
                                                                                new BinaryTreeNode(200,
                                                                                                   new BinaryTreeNode(125),
                                                                                                   new BinaryTreeNode(350))
                                                             ),
                                                             new BinaryTreeNode(100,
                                                                                new BinaryTreeNode(50,
                                                                                                   new BinaryTreeNode(25), null),
                                                                                new BinaryTreeNode(200,
                                                                                                   new BinaryTreeNode(125),
                                                                                                   new BinaryTreeNode(350))
                                                             )));
    }

    public boolean areIdentical(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 != null && root2 != null && root1.val != root2.val) {
            return false;
        }
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (!areIdentical(root1.left, root2.left)) {
            return false;
        }
        if (!areIdentical(root1.right, root2.right)) {
            return false;
        }
        return true;
    }

    static class BinaryTreeNode {
        int val;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(final int val) {
            this(val, null, null);
        }

        public BinaryTreeNode(final int val, final BinaryTreeNode left, final BinaryTreeNode right) {
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
