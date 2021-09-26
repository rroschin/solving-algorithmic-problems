package leetcode.topinterviewquestionseasy.linkedlist;

/**
 * Definition for singly-linked list.
 */
class ReverseList {
    public static ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode first = head;
        ListNode second = head.next;

        final ListNode newHead = reverse(first, second);
        head.next = null;
        return newHead;
    }

    private static ListNode reverse(final ListNode first, final ListNode second) {
        if (second == null) {
            return first;
        }

        final ListNode next = first.next;
        final ListNode nextnext = second.next;

        final ListNode reverse = reverse(next, nextnext);
        second.next = first;
        return reverse;
    }

    public static ListNode reverseListLoop(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = head;
        ListNode node = head.next;
        while (node != null) {
            ListNode curr = node;
            ListNode next = curr.next;
            curr.next = prev;
            node = next;
            prev = curr;
        }
        head.next = null;
        return prev;
    }

    public static void main(String[] args) {
        final ListNode n5 = new ListNode(5);
        final ListNode n4 = new ListNode(4, n5);
        final ListNode n3 = new ListNode(3, n4);
        final ListNode n2 = new ListNode(2, n3);
        final ListNode n1 = new ListNode(1, n2);
        reverseListRecursive(n1);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
