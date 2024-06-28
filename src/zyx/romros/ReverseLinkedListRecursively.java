package zyx.romros;

import leetcode.linkedlist.ListNode;

public class ReverseLinkedListRecursively {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println(new ReverseLinkedListRecursively().reverse(head));
    }

    private ListNode reverse(ListNode node) {
        // 1 -> 2 -> 3 -> 4
        // 1 <- 2 <- 3 <- 4
        return reverseDirection(node, null);

    }
    private ListNode reverseDirection(ListNode node, ListNode prev) {
        if (node.next == null) {
            node.next = prev;
            return node;
        }

        ListNode head = reverseDirection(node.next, node);
        node.next = prev;

        return head;
    }
}
