package leetcode.topinterviewquestionseasy.linkedlist;

/*
 * Definition for singly-linked list.
 */

class RemoveNthNodeFromEndOfList {

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;

        //Move fast in front so that the gap between slow and fast becomes n
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        //Move fast to the end, maintaining the gap
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Skip the desired node
        slow.next = slow.next.next;
        return start.next;
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode tmp = head;
        for (int i = 0; i < n; i++) {
            tmp = tmp.next;
        }
        if (tmp == null) {
            return head.next;
        }
        ListNode tmp1 = head;
        while (tmp.next != null) {
            tmp = tmp.next;
            tmp1 = tmp1.next;
        }
        tmp1.next = tmp1.next.next;
        return head;
    }

    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (head.next == null && n >= 1) {
            return null;
        }

        int length = 1;
        ListNode node = head;
        while (node.next != null) {
            length++;
            node = node.next;
        }

        if (n == length) {
            return head.next;
        }

        ListNode prev = head;
        node = head;
        for (int i = 0; i < length; i++) {
            if (length - i == n) {
                if (node.next == null) {
                    prev.next = null;
                    break;
                } else {
                    prev.next = node.next;
                    break;
                }
            }
            prev = node;
            node = node.next;
        }

        return head;
    }

    public static void main(String[] args) {
        final ListNode n0_2 = new ListNode(2);
        final ListNode n0_1 = new ListNode(1, n0_2);
        removeNthFromEnd(n0_1, 2);

        final ListNode n5 = new ListNode(5);
        final ListNode n4 = new ListNode(4, n5);
        final ListNode n3 = new ListNode(3, n4);
        final ListNode n2 = new ListNode(2, n3);
        final ListNode n1 = new ListNode(1, n2);
        removeNthFromEnd(n1, 2);

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
