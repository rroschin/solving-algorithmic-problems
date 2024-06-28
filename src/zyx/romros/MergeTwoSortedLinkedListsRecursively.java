package zyx.romros;

import leetcode.linkedlist.ListNode;

public class MergeTwoSortedLinkedListsRecursively {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(7))));
        ListNode head2 = new ListNode(0, new ListNode(2, new ListNode(4)));

        System.out.println(new MergeTwoSortedLinkedListsRecursively().merge1(head1, head2));

        ListNode head3 = new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(7))));
        ListNode head4 = new ListNode(0, new ListNode(2, new ListNode(4)));

        System.out.println(new MergeTwoSortedLinkedListsRecursively().merge(head3, head4));
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null) { return node2; }
        if (node2 == null) { return node1; }

        if (node1.val < node2.val) {
            node1.next = merge(node1.next, node2);
            return node1;
        } else {
            node2.next = merge(node2.next, node1);
            return node2;
        }

    }

    private ListNode merge1(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return null;
        }

        if (node1.val < node2.val) {
            ListNode tmp = node1.next;
            node1.next = node2;
            merge1(tmp, node2);
            return node1;
        } else {
            ListNode tmp = node2.next;
            node2.next = node1;
            merge1(tmp, node1);
            return node2;
        }
    }
}
