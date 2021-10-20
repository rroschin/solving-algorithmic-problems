package ms;

import java.util.HashMap;
import java.util.Map;

class ArbitraryPointer {

    public static ListNode deep_copy_arbitrary_pointer(ListNode head) {
        if (head == null) {
            return null;
        }

        /*
         7 -> 14 -> 21 -> null
         21   null  7

         7' -> 14' -> 21' -> null
         21    null   7
        */

        Map<ListNode, ListNode> oldToNew = new HashMap<>();
        ListNode copyHead = new ListNode();
        ListNode copy = copyHead;

        ListNode i = head;
        while (i != null) {
            copy.val = i.val;
            copy.next = new ListNode();
            oldToNew.put(i, copy);
            copy = copy.next;

            i = i.next;
        }

        copy = copyHead;
        i = head;
        while (i != null) {
            if (i.garbage != null) {
                ListNode j = oldToNew.get(i.garbage);
                copy.garbage = j;
            }

            i = i.next;
            copy = copy.next;
        }

        return copyHead;
    }

    public static void main(String[] args) {
        final ListNode n3 = new ListNode(21);
        final ListNode n2 = new ListNode(14, n3);
        final ListNode head = new ListNode(7, n2);
        head.garbage = n3;
        n3.garbage = head;

        final ListNode copy = deep_copy_arbitrary_pointer(head);
        System.out.println(copy);
        head.val = 70;
        n2.val = 140;
        n3.val = 210;
        System.out.println(copy);
        System.out.println(head);
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode garbage;

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
