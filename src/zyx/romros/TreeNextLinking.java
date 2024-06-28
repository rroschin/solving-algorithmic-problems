package zyx.romros;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNextLinking {

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode next;
    }

    public static void main(String[] args) {

    }

    void updateLinks2(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> levelQ = new LinkedList<>();
        levelQ.add(root);
        Queue<TreeNode> nextLevelQ = new LinkedList<>();

        while (!levelQ.isEmpty()) {
            TreeNode curr = levelQ.poll();
            if (curr.left != null) {
                nextLevelQ.add(curr.left);
            }
            if (curr.right != null) {
                nextLevelQ.add(curr.right);
            }

            if (levelQ.isEmpty()) { //end of level
                levelQ = new LinkedList<>(nextLevelQ);
                if (!nextLevelQ.isEmpty()) {
                    TreeNode prev = nextLevelQ.poll();
                    while (!nextLevelQ.isEmpty()) {
                        TreeNode curr2 = nextLevelQ.poll();
                        prev.next = curr2;
                        prev = curr2;
                    }
                }
            }
        }
    }

    void updateLinks(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        traverse(queue);
    }

    private void traverse(Queue<TreeNode> queue) {
        if (queue.isEmpty()) {
            return;
        }

        Queue<TreeNode> nextQueue = new LinkedList<>();
        TreeNode curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.left != null) {
                nextQueue.add(curr.left);
            }
            if (curr.right != null) {
                nextQueue.add(curr.right);
            }
            curr.next = queue.peek();

        }
        traverse(nextQueue);
    }
}
