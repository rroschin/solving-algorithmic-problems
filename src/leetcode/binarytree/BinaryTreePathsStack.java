package leetcode.binarytree;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePathsStack {

    public static void main(String[] args) {
        System.out.println(new BinaryTreePathsStack().binaryTreePaths(
                new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), new TreeNode(3))
        ));
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return List.of();
        }
        List<String> res = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        Deque<List<String>> paths = new LinkedList<>();
        stack.push(root);
        paths.push(List.of(root.val + ""));

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            List<String> path = paths.pop();
            if (node.right == null && node.left == null) {
                res.add(String.join("->", path));
                continue;
            }

            if (node.right != null) {
                stack.push(node.right);
                List<String> nodePath = new ArrayList<>(path);
                nodePath.add(node.right.val + "");
                paths.push(nodePath);
            }
            if (node.left != null) {
                stack.push(node.left);
                List<String> nodePath = new ArrayList<>(path);
                nodePath.add(node.left.val + "");
                paths.push(nodePath);
            }
        }

        return res;
    }
}
