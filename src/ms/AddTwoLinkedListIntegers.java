package ms;

public class AddTwoLinkedListIntegers {


    /*
    l1 = 1 0 9 9
    l2 = 7 3 2 -
    lr = 8 3 1 0 1
    */

    static ListNode add_integers(ListNode integer1, ListNode integer2) {

        ListNode head = new ListNode();

        ListNode result = head;
        while (integer1 != null || integer2 != null) {

            int val1 = integer1 != null ? integer1.val : 0;
            int val2 = integer2 != null ? integer2.val : 0;

            int sum = val1 + val2 + result.val;
            int carry = 0;
            if (sum >= 10) { //15 - 10
                sum = sum - 10;
                carry = 1;
            }
            result.val = sum;

            ListNode next = new ListNode(carry);
            result.next = next;
            result = next;

            if (integer1 != null) {
                integer1 = integer1.next;
            }
            if (integer2 != null) {
                integer2 = integer2.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        System.out.println(add_integers(new ListNode(1, new ListNode(0, new ListNode(9, new ListNode(9)))),
                                        new ListNode(7, new ListNode(3, new ListNode(2)))));

    }
}

class ListNode {
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
