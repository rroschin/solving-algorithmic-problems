package leetcode.topinterviewquestionseasy.trees;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 */
class SymmetricTree {

    public static boolean isSymmetric(TreeNode root) {
        return isEqual(root, root);
    }

    private static boolean isEqual(TreeNode q, TreeNode p) {
        if (q == null & p == null)
            return true;
        if (q == null || p == null)
            return false;
        return q.val == p.val && isEqual(q.left, p.right) && isEqual(q.right, p.left);
    }

    public static boolean isSymmetricRec(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }

        Queue<Integer> leftVals = new LinkedList<>();
        validateLeftRec(root.left, leftVals);

        Queue<Integer> rightVals = new LinkedList<>();
        validateRightRec(root.right, rightVals);

        return leftVals.equals(rightVals);
    }

    private static void validateLeftRec(final TreeNode node, final Queue<Integer> leftVals) {
        if (node == null) {
            leftVals.add(null);
            return;
        }
        leftVals.add(node.val);
        validateLeftRec(node.left, leftVals);
        validateLeftRec(node.right, leftVals);
    }

    private static void validateRightRec(final TreeNode node, final Queue<Integer> rightVals) {
        if (node == null) {
            rightVals.add(null);
            return;
        }
        rightVals.add(node.val);
        validateRightRec(node.right, rightVals);
        validateRightRec(node.left, rightVals);
    }

    public static boolean isSymmetricWhileLoop(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }

        Queue<Integer> leftVals = new LinkedList<>();
        Deque<TreeNode> left = new LinkedList<>();
        left.push(root.left);
        leftVals.add(root.left.val);
        validateLeft(left, leftVals);

        Queue<Integer> rightVals = new LinkedList<>();
        Deque<TreeNode> right = new LinkedList<>();
        right.push(root.right);
        rightVals.add(root.right.val);
        validateRight(right, rightVals);

        return leftVals.equals(rightVals);
    }

    private static void validateLeft(final Deque<TreeNode> left, final Queue<Integer> leftVals) {
        while (!left.isEmpty()) {
            final TreeNode el = left.pop();
            if (el.left == null) {
                leftVals.add(null);
            } else {
                leftVals.add(el.left.val);
            }
            if (el.right == null) {
                leftVals.add(null);
            } else {
                leftVals.add(el.right.val);
            }
            if (el.right != null) {
                left.push(el.right);
            }
            if (el.left != null) {
                left.push(el.left);
            }
        }
    }

    private static void validateRight(final Deque<TreeNode> right, final Queue<Integer> rightVals) {
        while (!right.isEmpty()) {
            final TreeNode el = right.pop();
            if (el.right == null) {
                rightVals.add(null);
            } else {
                rightVals.add(el.right.val);
            }
            if (el.left == null) {
                rightVals.add(null);
            } else {
                rightVals.add(el.left.val);
            }
            if (el.left != null) {
                right.push(el.left);
            }
            if (el.right != null) {
                right.push(el.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root2 = new TreeNode(1,
                                      new TreeNode(2,
                                                   new TreeNode(3), new TreeNode(4)),
                                      new TreeNode(2,
                                                   new TreeNode(4), new TreeNode(3)));
        System.out.println(isSymmetric(root2));

        TreeNode root3 = new TreeNode(5,
                                      new TreeNode(4), new TreeNode(6,
                                                                    new TreeNode(3), new TreeNode(7)));
        System.out.println(isSymmetric(root3));

        TreeNode root4 = new TreeNode(1,
                                      new TreeNode(2,
                                                   null, new TreeNode(3)),
                                      new TreeNode(2,
                                                   new TreeNode(3), null));
        System.out.println(isSymmetric(root4));

        TreeNode root5 = new TreeNode(1,
                                      new TreeNode(2,
                                                   null, new TreeNode(3)),
                                      new TreeNode(2,
                                                   null, new TreeNode(3)));
        System.out.println(isSymmetric(root5));
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
