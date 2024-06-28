package leetcode.linkedlist;

public class RotateList {
    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        System.out.println(new RotateList().rotateRight(n1, 2));
    }
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        int n = 1;
        ListNode tail = head;
        ListNode it = head.next;
        while (it != null) {
            n++;
            it = it.next;
            tail = tail.next;
        }
        k = k % n;
        if (k == 0) {
            return head;
        }

        ListNode newTail = head;
        int counter = n - k;
        while (counter > 1) {
            newTail = newTail.next;
            counter--;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        head = newHead;

        return head;
    }
}
