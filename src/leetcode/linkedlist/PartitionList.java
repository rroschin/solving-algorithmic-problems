package leetcode.linkedlist;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class PartitionList {

    public static void main(String[] args) {

        System.out.println(new PartitionList().partition(new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2)))))), 3));
        System.out.println(new PartitionList().partition(new ListNode(1, new ListNode(1)), 0));
    }

    ListNode leftHead = null;
    ListNode rightHead = null;

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        step(null, null, x, head);
        if (leftHead != null) {
            return leftHead;
        } else {
            return rightHead;
        }
    }

    void step(ListNode leftTail, ListNode rightTail, int x, ListNode node) {
        if (node == null) {
            if (leftTail != null) {
                leftTail.next = rightHead;
            }
            if (rightTail != null) {
                rightTail.next = null;
            }
            return;
        }
        if (node.val < x) {
            if (leftTail == null) {
                leftTail = node;
                leftHead = node;
            } else {
                leftTail.next = node;
                leftTail = node;
            }
        } else {
            if (rightTail == null) {
                rightTail = node;
                rightHead = node;
            } else {
                rightTail.next = node;
                rightTail = node;
            }
        }
        step(leftTail, rightTail, x, node.next);
    }

    public ListNode partition2(ListNode head, int x) {
        //empty or one -> return
        //all <x -> iterate and return
        //all >=x -> iterate and return
        //mixed -> iterate, partition and return

        //find pivot node = first node >= x
        //keep last node of left part
        //keep pivot and last node of right part
        //attach each node to either leftTail or rightTail
        //at the end attach pivot (rightHead) to leftEnd

        ListNode leftHead = null;
        ListNode leftTail = null;
        ListNode rightHead = null; //pivot
        ListNode rightTail = null;

        ListNode node = head;
        while (node != null) {
            if (node.val < x) {
                if (leftHead == null) {
                    leftHead = node;
                    leftTail = node;
                } else {
                    leftTail.next = node;
                    leftTail = node;
                }
            } else { //node.val >= x
                if (rightHead == null) {
                    rightHead = node;
                    rightTail = node;
                } else {
                    rightTail.next = node;
                    rightTail = node;
                }
            }
            node = node.next;
        }
        if (leftHead != null && rightHead != null) {
            leftTail.next = rightHead;
            rightTail.next = null;
            return leftHead;
        } else if (leftHead != null) {
            return leftHead;
        } else {
            return rightHead;
        }
    }
}
