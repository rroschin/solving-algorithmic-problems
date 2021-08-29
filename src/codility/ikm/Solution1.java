package codility.ikm;

public class Solution1 {

  public static void main(String[] args) {

    CircularQ cq = new CircularQ();
    cq.add(2);
    cq.add(3);

    int times = 6;
    Node i = cq.head;
    while (times > 0) {
      System.out.println(i.val);
      i = i.next;
      times--;
    }

  }

  static class CircularQ {

    Node head;
    Node tail;

    public void add(int value) {
      Node newNode = new Node(value);

      if (head == null) {
        head = newNode;
      } else {
        tail.next = newNode;
      }

      tail = newNode;
      tail.next = head;
    }

  }

  static class Node {

    int val;
    Node next;

    public Node(int val) {
      this.val = val;
    }

  }

}
