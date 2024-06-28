package leetcode.linkedlist;


import java.util.LinkedList;
import java.util.List;

class ReorderList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null))))); //1,2,3,4,5
        new ReorderList().reorderList(head);
        System.out.println(head); //1,5,2,4,3

        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null)))); //1,2,3,4
        new ReorderList().reorderList(head2);
        System.out.println(head2); //1,4,2,3
    }
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        int n = 0;
        ListNode node = head;
        while(node != null) {
            n++;
            node = node.next;
        }

        if (n % 2 == 1) {
            reorderWithRecursionForOdd(head, 1, n);
        } else {
            reorderWithRecursionForEven(head, 0, n);
        }

    }

    ListNode reorderWithRecursionForOdd(ListNode curr, int i, int n) { //1,1,4; 2,2,4
        if (i == n / 2 + 1) {
            ListNode ret = curr.next;
            curr.next = null;
            return ret; //3
        }
        ListNode tail = reorderWithRecursionForOdd(curr.next, i + 1, n); //2,2,4; 3
        ListNode prevNext = curr.next;
        curr.next = tail; //
        ListNode prevTailNext = tail.next;
        tail.next = prevNext;
        return prevTailNext;
    }

    ListNode reorderWithRecursionForEven(ListNode curr, int i, int n) { //1,1,4; 2,2,4
        if (i == n / 2) {
            return curr; //3
        }
        ListNode tail = reorderWithRecursionForEven(curr.next, i + 1, n); //2,2,4; 3
        ListNode prevNext = curr.next;
        curr.next = tail; //
        ListNode prevTailNext = tail.next;
        if (i+ 1 == n / 2) {
            tail.next = null;
        } else {
            tail.next = prevNext;
        }
        return prevTailNext;
    }

    public void reorderListWithList(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }

        List<ListNode> nodes = new LinkedList<>();
        ListNode node = head;
        while(node != null) {
            nodes.add(node);
            node = node.next;
        }

        int i = nodes.size() - 1;
        node = head; //1; 2
        while (node != null) {
            ListNode next = node.next; //2; 3
            ListNode newNext = nodes.get(i); //5; 4
            node.next = newNext; //1 -> 5; 2 -> 4;
            newNext.next = next; //5 -> 2; 4 -> 3

            node = next; //2; 3;
            i--;
            if (node.next == newNext) {
                node.next = null;
                break;
            }
        }
    }
}
