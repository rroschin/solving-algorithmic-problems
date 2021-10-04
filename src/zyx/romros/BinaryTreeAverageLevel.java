package zyx.romros;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeAverageLevel {

    public static void main(String[] args) {
        Node root = new Node(4,
                             new Node(7,
                                      new Node(10, null, null),
                                      new Node(2, null,
                                               new Node(6,
                                                        new Node(2, null, null),
                                                        null))),
                             new Node(9, null, new Node(6, null, null)));
        System.out.println(Arrays.toString(new BinaryTreeAverageLevel().calculateAverage(root)));
    }

    public Integer[] calculateAverage(Node root) {
        if (root == null) {
            return new Integer[0];
        }
        if (root.left == null && root.right == null) {
            return new Integer[] {root.val};
        }
        List<Integer> avg = new LinkedList<>();
        List<Integer> sum = new LinkedList<>();
        List<Integer> num = new LinkedList<>();

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        bfs(q, sum, num, 0);

        for (int i = 0; i < sum.size(); i++) { //O(m)
            Integer lAvg = sum.get(i) / num.get(i);
            avg.add(lAvg);
        }

        return avg.toArray(new Integer[0]);
    }

    private void bfs(final Queue<Node> q, final List<Integer> sum, final List<Integer> num, final int level) {
        if (q.isEmpty()) {
            return;
        }

        Queue<Node> lq = new LinkedList<>();
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (sum.size() == level) {
                sum.add(0);
                num.add(0);
            }
            Integer nSum = sum.get(level) + node.val;
            sum.set(level, nSum);
            Integer nNum = num.get(level) + 1;
            num.set(level, nNum);

            if (node.left != null) {
                lq.add(node.left);
            }
            if (node.right != null) {
                lq.add(node.right);
            }
        }
        q.addAll(lq);
        bfs(q, sum, num, level + 1);
    }

    public Integer[] calculateAverageDfs(Node root) {
        if (root == null) {
            return new Integer[0];
        }
        if (root.left == null && root.right == null) {
            return new Integer[] {root.val};
        }
        List<Integer> avg = new LinkedList<>();

        List<Integer> sum = new LinkedList<>();
        List<Integer> num = new LinkedList<>();

        /*
              4             -> 4
             / \
            7   9           -> (7 + 9) / 2 -> sum (left+right+...) / numOfNodesOnLevel
           / \    \
         10   2    6        -> (10 + 2 + 6) / 3 = 6
               \
                6           -> (6) / 1
               /
             2              -> (2) / 1
         */

        traverse(root, sum, num, 0); //O(n)

        for (int i = 0; i < sum.size(); i++) { //O(m)
            Integer lAvg = sum.get(i) / num.get(i);
            avg.add(lAvg);
        }

        return avg.toArray(new Integer[0]);
    }

    private void traverse(final Node node, final List<Integer> sum, final List<Integer> num, final int level) {
        if (node == null) {
            return;
        }
        if (sum.size() == level) {
            sum.add(0);
            num.add(0);
        }
        Integer nSum = sum.get(level) + node.val;
        sum.set(level, nSum);
        Integer nNum = num.get(level) + 1;
        num.set(level, nNum);

        traverse(node.left, sum, num, level + 1);
        traverse(node.right, sum, num, level + 1);
    }

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val,
                    Node left,
                    Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }
}
