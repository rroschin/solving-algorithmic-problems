package leetcode.topinterviewquestionseasy.linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Definition for singly-linked list.
 */
class IsPalindrome {

    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        ListNode curr = head;

        while (fast != null && fast.next != null) {
            curr = slow;
            fast = fast.next.next;
            slow = slow.next;
            curr.next = prev;
            prev = curr;
        }
        if (fast != null)
            slow = slow.next;
        while (slow != null && curr != null) {
            if (slow.val != curr.val)
                return false;
            slow = slow.next;
            curr = curr.next;
        }
        return true;
    }

    public static boolean isPalindromeWithReverse(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }

        ListNode tail = reverse(head);
        while (head != null && tail != null) {
            if (head.val != tail.val) {
                return false;
            }
            head = head.next;
            tail = tail.next;
        }

        return true;
    }

    private static ListNode reverse(ListNode head) {
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

    public static boolean isPalindromeWithStack(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }

        int counter = 0;
        ListNode node = head;
        while (node != null) {
            counter++;
            node = node.next;
        }

        Deque<Integer> d = new LinkedList<>();
        node = head;
        for (int i = 0; i < counter / 2; i++) {
            d.push(node.val);
            node = node.next;
        }

        if (counter % 2 != 0) { //middle one
            node = node.next;
        }
        for (int i = 0; i < counter / 2; i++) {
            final Integer v = d.pop();
            if (v != node.val) {
                return false;
            }
            node = node.next;
        }

        return true;
    }

    public static boolean isPalindromeWithString(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }

        StringBuilder sb = new StringBuilder();
        ListNode node = head;
        while (node != null) {
            sb.append(node.val);
            node = node.next;
        }
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != sb.charAt(sb.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        final ListNode m5 = new ListNode(1);
        final ListNode m4 = new ListNode(2, m5);
        final ListNode m3 = new ListNode(3, m4);
        final ListNode m2 = new ListNode(2, m3);
        final ListNode m1 = new ListNode(1, m2);

        System.out.println(isPalindrome(m1));
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
