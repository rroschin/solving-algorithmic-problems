package leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * Definition for singly-linked list.
 */
public class HasCycle {

    public static boolean hasCycleWithSet(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        if (head == head.next) {
            return true;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return true;
            }
            set.add(node);
            node = node.next;
        }

        return false;
    }

    public static boolean hasCycleWithTwoPointers(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        if (head == head.next) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != null && fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    public static void main(String[] args) {
        final ListNode n4 = new ListNode(-4);
        final ListNode n3 = new ListNode(0, n4);
        final ListNode n2 = new ListNode(2, n3);
        final ListNode n1 = new ListNode(3, n2);

        n4.next = n2;

        System.out.println(hasCycleWithSet(n1));
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
