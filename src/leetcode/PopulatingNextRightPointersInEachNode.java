package leetcode;/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

import java.util.LinkedList;
import java.util.Queue;

class PopulatingNextRightPointersInEachNode {

    public Node connect2(Node node) {
        if (node == null) return null;

        if (node != null && node.left != null) {
            node.left.next = node.right;
        }

        if (node.next != null && node.right != null) {
            node.right.next = node.next.left;
        }

        connect(node.left);
        connect(node.right);

        return node;
    }
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> q = new LinkedList<>(); //O(n)
        q.add(root);
        walk(q);

        return root; //O(n)
    }

    private void walk(Queue<Node> q) {
        if (q.isEmpty()) {
            return;
        }

        Queue<Node> localQueue = new LinkedList<>();
        Node prev = null;
        while (!q.isEmpty()) {
            Node n = q.poll(); //n = 1
            if (n.left != null) {
                localQueue.add(n.left); //q = [2]
            }
            if (n.right != null) {
                localQueue.add(n.right); //q = [2, 3]
            }

            if (prev != null) {
                prev.next = n;
            }
            prev = n; //prev = 1
        }

        walk(localQueue);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
