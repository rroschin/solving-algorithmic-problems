package zyx.romros;

import leetcode.linkedlist.ListNode;

public class OddEvenLinkedList {

    public static void main(String[] args) {
        System.out.println(new OddEvenLinkedList().group(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))))));
        System.out.println(new OddEvenLinkedList().group(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))))));
    }

    private ListNode group(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode headOdd = head;
        ListNode headEven = head.next;

        ListNode pOdd = headOdd; //1st
        ListNode pEven = headEven; //2d

        ListNode curr = headEven.next;
        int counter = 3;
        while (curr != null) {
            if (counter % 2 == 0) {
                pEven.next = curr;
                pEven = curr;
            } else {
                pOdd.next = curr;
                pOdd = curr;
            }
            curr = curr.next;
            counter++;
        }

        pOdd.next = headEven;
        pEven.next = null;
        return headOdd;
    }


}
