package zyx.romros;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsSolution {

  public static void main(String[] args) {
    Node root = createTree();
    int val = 6;

    Queue<Node> q = new LinkedList<>();

    boolean found = bfs(root, val, q);
    System.out.println(found);
  }

  private static boolean bfs(Node node, int val, Queue<Node> q) {
    System.out.println("Visited " + node.value);
    if (node.value == val) {
      System.out.println("Found " + val);
      return true;
    }

    q.addAll(node.children);
    while (!q.isEmpty()) {
      return bfs(q.poll(), val, q);
    }

    return false;
  }

  private static Node createTree() {
    Node root = new Node(1);

    Node lvl11 = new Node(4);
    root.addChild(lvl11);
    Node lvl12 = new Node(7);
    root.addChild(lvl12);
    Node lvl13 = new Node(9);
    root.addChild(lvl13);

    Node lvl21 = new Node(2);
    lvl11.addChild(lvl21);
    Node lvl22 = new Node(6);
    lvl12.addChild(lvl22);
    Node lvl23 = new Node(5);
    lvl12.addChild(lvl23);
    Node lvl24 = new Node(0);
    lvl13.addChild(lvl24);

    return root;
  }

  static class Node {

    int value;
    List<Node> children = new LinkedList<>();

    public Node(int value) {
      this.value = value;
    }

    void addChild(Node child) {
      children.add(child);
    }

  }

}
