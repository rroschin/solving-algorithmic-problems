package zyx.romros;

public class LinkedListCycleSolution {

  public static void main(String[] args) {
    LinkedListNode root = createLinkedList(true);
    boolean hasCycle = checkForCycle(root);
    System.out.println(hasCycle);
  }

  private static boolean checkForCycle(LinkedListNode root) {
    LinkedListNode p2 = root;
    LinkedListNode p1 = root.next;

    boolean increase = false;
    while (p2 != null) {
      if (p1 == p2) {
        return true;
      }
      if (p1.next == null) {
        return false;
      }
      p1 = p1.next;
      if (increase) {
        p2 = p2.next;
        increase = false;
      } else {
        increase = true;
      }
    }
    return false;
  }

  private static LinkedListNode createLinkedList(boolean addCycle) {
    // 1 -> 2 -> 3 -> 4 -> 2
    LinkedListNode one = new LinkedListNode(1);
    LinkedListNode two = new LinkedListNode(2);
    one.next = two;
    LinkedListNode three = new LinkedListNode(3);
    two.next = three;
    LinkedListNode four = new LinkedListNode(4);
    three.next = four;

    if (addCycle) {
      four.next = two;
    }

    return one;
  }

}

class LinkedListNode {

  int val;
  LinkedListNode next;

  public LinkedListNode(int val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return "val=" + val;
  }

}
