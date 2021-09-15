package leetcode.linkedlist;

/**
 * Definition for singly-linked list.
 */
class MergeTwoLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null && l2 != null) {
            return l2;
        }
        if (l1 != null && l2 == null) {
            return l1;
        }

        ListNode newHead;
        if (l1.val < l2.val) {
            newHead = l1;
            l1 = l1.next;
        } else {
            newHead = l2;
            l2 = l2.next;
        }

        ListNode curr = newHead;
        while (l1 != null || l2 != null) {
            ListNode next;
            if (l1 == null) {
                next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                next = l1;
                l1 = l1.next;
            } else if (l1.val < l2.val) {
                next = l1;
                l1 = l1.next;
            } else {
                next = l2;
                l2 = l2.next;
            }
            curr.next = next;
            curr = next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        final ListNode n3 = new ListNode(4);
        final ListNode n2 = new ListNode(2, n3);
        final ListNode n1 = new ListNode(1, n2);

        final ListNode m3 = new ListNode(4);
        final ListNode m2 = new ListNode(3, m3);
        final ListNode m1 = new ListNode(1, m2);

        mergeTwoLists(n1, m1);
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
