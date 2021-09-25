package leetcode.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BinaryTreeLevelOrderTraversal {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        level(root, res, 0);
        return res;
    }

    static void level(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }

        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);

        level(root.left, res, level + 1);
        level(root.right, res, level + 1);
    }

    public static List<List<Integer>> levelOrder1(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        if (root.left == null && root.right == null) {
            return List.of(List.of(root.val));
        }

        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        go1(q, res, 0);
        return res;
    }

    private static void go1(final Queue<TreeNode> q, final List<List<Integer>> res, final int level) {
        if (q.isEmpty()) {
            return;
        }

        final Queue<TreeNode> localQ = new LinkedList<>();
        while (!q.isEmpty()) {
            final TreeNode node = q.poll();

            if (res.size() < level + 1) {
                res.add(new LinkedList<>());
            }
            System.out.println(level + ": " + node.val);
            res.get(level).add(node.val);

            if (node.left != null) {
                localQ.add(node.left);
            }

            if (node.right != null) {
                localQ.add(node.right);
            }
        }
        q.addAll(localQ);

        go1(q, res, level + 1);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3,
                                      new TreeNode(9,
                                                   null, null),
                                      new TreeNode(20,
                                                   new TreeNode(15), new TreeNode(7)));
        System.out.println(levelOrder(root1));
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
