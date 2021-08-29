package zyx.romros;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DfsSolution {

  public static void main(String[] args) {
    Node root = createTree();
    int val = 6;

    Deque<Node> d = new ArrayDeque<>();

    boolean found = dfs(root, val, d);
    System.out.println(found);

    boolean found2 = dfs2(root, val, d);
    System.out.println(found2);

  }

  private static boolean dfs2(Node node, int val, Deque<Node> d) {
    if (node == null) {
      return false;
    }
    System.out.println("Visited " + node.value);
    if (node.value == val) {
      System.out.println("Found " + node.value);
      return true;
    }

    for (int i = node.children.size() - 1; i >= 0; i--) {
      d.push(node.children.get(i));
    }
    while (!d.isEmpty()) {
      final Node popped = d.pop();
      return dfs2(popped, val, d);
    }
    return false;
  }

  private static boolean dfs(Node node, int val, Deque<Node> d) {
    System.out.println("Visited " + node.value);
    if (node.value == val) {
      System.out.println("Found " + val);
      return true;
    }

    for (int i = node.children.size() - 1; i >= 0; i--) {
      d.push(node.children.get(i));
    }
    while (!d.isEmpty()) {
      return dfs(d.pop(), val, d);
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
