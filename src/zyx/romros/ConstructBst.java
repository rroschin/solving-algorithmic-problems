package zyx.romros;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConstructBst {

    public static void main(String[] args) {
        TreeNode root = new ConstructBst().constructRandomTree();
        root.printTree();
    }

    public TreeNode constructRandomTree() {
        Random r = new Random();
        int n = 10;
        List<Integer> list = new ArrayList<>(10);
        for (int i = 0; i < n; i++) {
            list.add(r.nextInt(99));
        }

        TreeNode root = new TreeNode(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            insert(list.get(i), root);
        }
        return root;
    }

    private static void insert(Integer value, TreeNode node) {
        if (value < node.val) {
            if (node.left == null) {
                node.left = new TreeNode(value);
            } else {
                insert(value, node.left);
            }
        } else if (value > node.val) {
            if (node.right == null) {
                node.right = new TreeNode(value);
            } else {
                insert(value, node.right);
            }
        }
    }
}
