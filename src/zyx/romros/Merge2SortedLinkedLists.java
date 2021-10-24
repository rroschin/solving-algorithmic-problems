package zyx.romros;

class Merge2SortedLinkedLists {

    public static void main(String[] args) {
        System.out.println(new Merge2SortedLinkedLists().merge_sorted(new LinkedListNode(4,
                                                                        new LinkedListNode(8,
                                                                        new LinkedListNode(15,
                                                                        new LinkedListNode(19)))),
                                                                      new LinkedListNode(7,
                                                                        new LinkedListNode(9,
                                                                        new LinkedListNode(10,
                                                                        new LinkedListNode(16,
                                                                        new LinkedListNode(99)))))));
    }

    public LinkedListNode merge_sorted(LinkedListNode head1, LinkedListNode head2) {
        LinkedListNode newHead = null;
        LinkedListNode node = newHead;

        while (head1 != null && head2 != null) {
            LinkedListNode chosen;
            if (head1.val < head2.val) {
                chosen = head1;
                head1 = head1.next;
            } else {
                chosen = head2;
                head2 = head2.next;
            }
            if (newHead == null) {
                newHead = chosen;
                node = newHead;
            } else {
                node.next = chosen;
                node = chosen;
            }
        }

        while (head1 != null) {
            node.next = head1;
            node = node.next;
            head1 = head1.next;
        }

        while (head2 != null) {
            node.next = head2;
            node = node.next;
            head2 = head2.next;
        }

        return newHead;
    }

    static class LinkedListNode {

        int val;
        LinkedListNode next;

        public LinkedListNode(int val) {
            this.val = val;
        }

        public LinkedListNode(final int val, final LinkedListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            LinkedListNode node = this;
            while (node != null) {
                sb.append(node.val).append(" -> ");
                node = node.next;
            }
            return sb.toString();
        }

    }
}
