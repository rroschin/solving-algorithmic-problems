package zyx.romros;

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
class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode head = new ReverseNodesInKGroup().reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
        System.out.println(head);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // Base case: null node or single node.
        if (head == null || head.next == null) {
            return head;
        }

        // Check if the current list has greater than or equal to k nodes.
        int count = 0;
        ListNode curr = head;

        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }

        // If the list has less than k nodes, then return the head.
        if (count != k) {
            return head;
        }

        // Otherwise, before we reverse the nodes, we pass in the next sublist,
        // which we will assume that it will return us the reversed sublist.
        ListNode subHead = reverseKGroup(curr, k);

        // Now, reverse the current list.
        count = 0;
        ListNode prev = null;
        curr = head;
        ListNode next = null;

        while (curr != null && count < k) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }

        // Then, append the end of the list to the returned sublist head.
        head.next = subHead;

        // Return the new head.
        return prev;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = null;

        int i = 0;

        ListNode prev = null;
        ListNode curr = head;

        ListNode prevLastInGroup = null;
        ListNode lastInGroup = curr;
        boolean captureLastInGroup = false;

        while (isKNodesAvailable(lastInGroup, k)) {

            while (i < k) {
                if (!captureLastInGroup) {
                    lastInGroup = curr; //1
                    captureLastInGroup = true;
                }
                ListNode next = curr.next; //2; 3

                curr.next = prev; //1.next = null; 2.next = 1
                prev = curr; //prev = 1; prev = 2
                curr = next; //curr = 2; curr = 3
                i++; //1; 2
            }

            if (newHead == null) {
                newHead = prev; //2
            }
            i = 0;
            lastInGroup.next = curr; //2 -> 1 -> 3 -> 4 -> 5
            if (prevLastInGroup != null) {
                prevLastInGroup.next = prev;
            }
            prevLastInGroup = lastInGroup;
            captureLastInGroup = false;
            lastInGroup = curr;
            prev = null;
        }

        return newHead;
    }

    boolean isKNodesAvailable(ListNode start, int k) {
        ListNode it = start;
        int i = 0;
        while (it != null) {
            i++;
            it = it.next;
        }
        return i >= k;
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

        @Override
        public String toString() {
            ListNode node = this;
            StringBuilder sb = new StringBuilder();
            while (node != null) {
                sb.append(node.val).append(" -> ");
                node = node.next;
            }
            return sb.toString();
        }
    }

}
