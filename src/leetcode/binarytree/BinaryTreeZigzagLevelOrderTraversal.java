package leetcode.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import leetcode.TreeNode;

class BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        //[3,9,20,null,null,15,7]
        TreeNode root =
                new TreeNode(3,
                        new TreeNode(9, null, null),
                        new TreeNode(20,
                                new TreeNode(15, null, null),
                                new TreeNode(7, null, null)));

        System.out.println(new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root));
        System.out.println(new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrderPostReverse(root));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();

        LinkedList<TreeNode> queue = new LinkedList<>(); //Queue
        queue.add(root);
        LinkedList<TreeNode> queueNext = new LinkedList<>(); //Queue
        List<Integer> resLevel = new ArrayList<>();
        boolean isStraight = true;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                break;
            }
            resLevel.add(curr.val);
            if (isStraight) {
                if (curr.left != null) {
                    queueNext.add(curr.left);
                }
                if (curr.right != null) {
                    queueNext.add(curr.right);
                }
            } else {
                if (curr.right != null) {
                    queueNext.add(curr.right);
                }
                if (curr.left != null) {
                    queueNext.add(curr.left);
                }
            }

            if (queue.isEmpty()) {
                isStraight = !isStraight;
                Collections.reverse(queueNext);
                queue = queueNext;
                queueNext = new LinkedList<>();
                res.add(resLevel);
                resLevel = new ArrayList<>();
            }
        }

        return res;
    }

    public List<List<Integer>> zigzagLevelOrderPostReverse(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();

        LinkedList<TreeNode> queue = new LinkedList<>(); //Queue
        queue.add(root);
        LinkedList<TreeNode> queueNext = new LinkedList<>(); //Queue
        List<Integer> resLevel = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                break;
            }
            resLevel.add(curr.val);
            if (curr.left != null) {
                queueNext.add(curr.left);
            }
            if (curr.right != null) {
                queueNext.add(curr.right);
            }

            if (queue.isEmpty()) {
                queue = queueNext;
                queueNext = new LinkedList<>();
                res.add(resLevel);
                resLevel = new ArrayList<>();
            }
        }
        int i = 0;
        for (List<Integer> l : res) {
            if (i % 2 != 0) {
                Collections.reverse(l);
            }
            i++;
        }

        return res;
    }
}
