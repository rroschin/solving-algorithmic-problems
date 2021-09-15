package leetcode.linkedlist;

/*
 * Definition for singly-linked list.
 */


class DeleteNodeInLinkedList {
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        final ListNode n4 = new ListNode(4);
        final ListNode n3 = new ListNode(3);
        n3.next = n4;
        final ListNode n2 = new ListNode(2);
        n2.next = n3;
        final ListNode n1 = new ListNode(1);
        n1.next = n2;
        deleteNode(n3);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
