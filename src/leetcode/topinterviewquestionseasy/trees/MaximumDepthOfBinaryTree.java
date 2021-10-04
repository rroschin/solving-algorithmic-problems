package leetcode.topinterviewquestionseasy.trees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Definition for a binary tree node.
 */
class MaximumDepthOfBinaryTree {

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int depth = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            final TreeNode n = q.poll();
            if (n == null) {
                depth++;
            }
            if (n != null) {
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            } else if (!q.isEmpty()) {
                q.add(null);
            }
        }

        return depth;
    }

    public static int maxDepthRec(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public static int maxDepthMapAndSet(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int currD = 1;
        int maxD = 1;
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        TreeNode n = root;
        while (n != null) {
            if (n.left != null && !set.contains(n.left.val)) {
                currD++;
                map.put(n.left.val, n);
                set.add(n.left.val);
                n = n.left;
                continue;
            }

            if (n.right != null && !set.contains(n.right.val)) {
                currD++;
                map.put(n.right.val, n);
                set.add(n.right.val);
                n = n.right;
                continue;
            }

            if (currD > maxD) {
                maxD = currD;
            }

            if ((n.left == null || set.contains(n.left.val)) && (n.right == null || set.contains(n.right.val))) {
                n = map.get(n.val);
                currD--;
            }
        }

        return maxD;
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(2);
        TreeNode root = new TreeNode(1, null, r1);
        System.out.println(maxDepth(root));

        //        TreeNode r21 = new TreeNode(15);
        //        TreeNode r22 = new TreeNode(7);
        //
        //        TreeNode l1 = new TreeNode(9);
        //        TreeNode r1 = new TreeNode(20, r21, r22);
        //
        //        TreeNode root = new TreeNode(3, l1, r1);
        //
        //        System.out.println(maxDepth(root));

        //        TreeNode l21 = new TreeNode(4);
        //        TreeNode r21 = new TreeNode(5);
        //
        //        TreeNode l1 = new TreeNode(2, l21, null);
        //        TreeNode r1 = new TreeNode(3, r21, null);
        //
        //        TreeNode root = new TreeNode(1, l1, r1);
        //
        //        System.out.println(maxDepth(root));
    }

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

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
