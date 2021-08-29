package zyx.romros;

public class IsBinaryTreeBalancedSolution {

  static int invocations = 0;

  public static void main(String[] args) {
    Node node4 = new Node();
    node4.val = 4;

    Node node2 = new Node();
    node2.val = 2;
    node4.left = node2;

    Node node6 = new Node();
    node6.val = 6;
    node4.right = node6;

    Node node1 = new Node();
    node1.val = 1;
    node2.left = node1;

    Node node3 = new Node();
    node3.val = 3;
    node2.right = node3;

    Node node5 = new Node();
    node5.val = 5;
    node6.left = node5;

    Node node7 = new Node();
    node7.val = 7;
    node6.right = node7;



    boolean is = isBalanced(node4);
    System.out.println(is);
    System.out.println(invocations);
  }

  private static boolean isBalanced(Node root) {
    invocations++;
    if (root == null || root.left == null || root.right == null) {
      return true; //error
    }

    final boolean rootBalanced = root.val > root.left.val && root.val < root.right.val;
    if (rootBalanced) {
      final boolean leftBalanced = isBalanced(root.left);
      if (leftBalanced) {
        return isBalanced(root.right);
      } else {
        return false;
      }
    } else {
      return false;
    }

  }

  static class Node {
    int val;
    Node left;
    Node right;
  }
}
