package fb;

import leetcode.linkedlist.ListNode;

public class RemoveKthNodeFromListEnd {

    public static void main(String[] args) {
        System.out.println(new RemoveKthNodeFromListEnd().remove(
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))),
                2));
        System.out.println(new RemoveKthNodeFromListEnd().remove(
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))),
                1));
        System.out.println(new RemoveKthNodeFromListEnd().remove(
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))),
                4));
        System.out.println(new RemoveKthNodeFromListEnd().remove(
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))),
                3));
        System.out.println(new RemoveKthNodeFromListEnd().remove(
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))),
                7));
    }

    private ListNode remove(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        int i = 1;
        while (i <= k && p2 != null) {
            i++;
            p2 = p2.next;
        }
        if (i - 1 == k && p2 == null) {
            return head.next;
        } else if (p2 == null) {
            return head;
        }

        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p1.next = p1.next.next;
        return head;
    }
}
