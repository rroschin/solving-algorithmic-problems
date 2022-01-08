package leetcode;

class MyLinkedList {

    Node head;
    Node tail;
    int size = 0;

    public MyLinkedList() {

    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList1 = new MyLinkedList();

        myLinkedList1.addAtHead(84);
        myLinkedList1.addAtTail(2);
        myLinkedList1.addAtTail(39);
        myLinkedList1.get(3);
        myLinkedList1.get(1);
        myLinkedList1.addAtTail(42);
        myLinkedList1.addAtIndex(1, 80);
        myLinkedList1.addAtHead(14);
        myLinkedList1.addAtHead(1);
        myLinkedList1.addAtTail(53);
        myLinkedList1.addAtTail(98);
        myLinkedList1.addAtTail(19);
        myLinkedList1.addAtTail(12);
        myLinkedList1.get(2);
        myLinkedList1.addAtHead(16);
        myLinkedList1.addAtHead(33);
        myLinkedList1.addAtIndex(4, 17);
        myLinkedList1.addAtIndex(6, 8);
        myLinkedList1.addAtHead(37);
        myLinkedList1.addAtTail(43);
        myLinkedList1.deleteAtIndex(11);
        myLinkedList1.addAtHead(80);
        myLinkedList1.addAtHead(31);
        myLinkedList1.addAtIndex(13, 23);
        myLinkedList1.addAtTail(17);
        myLinkedList1.get(4);
        myLinkedList1.addAtIndex(10, 0);
        myLinkedList1.addAtTail(21);
        myLinkedList1.addAtHead(73);
        myLinkedList1.addAtHead(22);
        myLinkedList1.addAtIndex(24, 37);
        myLinkedList1.addAtTail(14);
        myLinkedList1.addAtHead(97);
        myLinkedList1.addAtHead(8);
        myLinkedList1.get(6);
        myLinkedList1.deleteAtIndex(17);
        myLinkedList1.addAtTail(50);
        myLinkedList1.addAtTail(28);
        myLinkedList1.addAtHead(76);
        myLinkedList1.addAtTail(79);
        myLinkedList1.get(18);
        myLinkedList1.deleteAtIndex(30);
        myLinkedList1.addAtTail(5);
        myLinkedList1.addAtHead(9);
        myLinkedList1.addAtTail(83);
        myLinkedList1.deleteAtIndex(3);
        myLinkedList1.addAtTail(40);
        myLinkedList1.deleteAtIndex(26);
        myLinkedList1.addAtIndex(20, 90);
        myLinkedList1.deleteAtIndex(30);
        myLinkedList1.addAtTail(40);
        myLinkedList1.addAtHead(56);
        myLinkedList1.addAtIndex(15, 23);
        myLinkedList1.addAtHead(51);
        myLinkedList1.addAtHead(21);
        myLinkedList1.get(26);
        myLinkedList1.addAtHead(83);
        myLinkedList1.get(30);
        myLinkedList1.addAtHead(12);
        myLinkedList1.deleteAtIndex(8);
        myLinkedList1.get(4);
        myLinkedList1.addAtHead(20);
        myLinkedList1.addAtTail(45);
        myLinkedList1.get(10);
        myLinkedList1.addAtHead(56);
        myLinkedList1.get(18);
        myLinkedList1.addAtTail(33);
        myLinkedList1.get(2);
        myLinkedList1.addAtTail(70);
        myLinkedList1.addAtHead(57);
        myLinkedList1.addAtIndex(31, 24);
        myLinkedList1.addAtIndex(16, 92);
        myLinkedList1.addAtHead(40);
        myLinkedList1.addAtHead(23);
        myLinkedList1.deleteAtIndex(26);
        myLinkedList1.get(1);
        myLinkedList1.addAtHead(92);
        myLinkedList1.addAtIndex(3, 78);
        myLinkedList1.addAtTail(42);
        myLinkedList1.get(18);
        myLinkedList1.addAtIndex(39, 9);
        myLinkedList1.get(13);
        myLinkedList1.addAtIndex(33, 17);
        myLinkedList1.get(51);
        myLinkedList1.addAtIndex(18, 95);
        myLinkedList1.addAtIndex(18, 33);
        myLinkedList1.addAtHead(80);
        myLinkedList1.addAtHead(21);
        myLinkedList1.addAtTail(7);
        myLinkedList1.addAtIndex(17, 46);
        myLinkedList1.get(33);
        myLinkedList1.addAtHead(60);
        myLinkedList1.addAtTail(26);
        myLinkedList1.addAtTail(4);
        myLinkedList1.addAtHead(9);
        myLinkedList1.get(45);
        myLinkedList1.addAtTail(38);
        myLinkedList1.addAtHead(95);
        myLinkedList1.addAtTail(78);
        myLinkedList1.get(54);
        myLinkedList1.addAtIndex(42, 86);
    }

    @Override
    public String toString() {
        Node node = head;
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.val).append(" -> ");
            node = node.next;
        }
        return sb.toString();
    }

    public int get(int index) {
        if (this.size - 1 < index) {
            return -1;
        }
        if (index == 0) {
            return head.val;
        }

        Node n = head;
        int i = 0;
        while (i < index) {
            n = n.next;
            i++;
        }

        return n.val;
    }

    public void addAtHead(int val) {
        if (head == null) {
            head = new Node(val);
            tail = head;
        } else {
            Node newHead = new Node(val, head);
            head = newHead;
        }
        size++;
    }

    public void addAtTail(int val) {
        if (tail == null) {
            addAtHead(val);
        } else {
            Node n = new Node(val);
            tail.next = n;
            tail = n;
            size++;
        }
    }

    public void addAtIndex(int index, int val) {
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (this.size - 1 < index) {
            return;
        }

        Node prev = null;
        Node n = head;
        int i = 0;
        while (i < index) {
            prev = n;
            n = n.next;
            i++;
        }

        Node node = new Node(val, prev.next);
        prev.next = node;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (this.size - 1 < index) {
            return;
        }
        if (index == 0) {
            if (tail == head) {
                tail = null;
            }
            head = head.next;
            return;
        }

        Node prev = null;
        Node n = head;
        int i = 0;
        while (i < index) {
            prev = n;
            n = n.next;
            i++;
        }

        prev.next = n.next;
        if (index == size) {
            tail = prev;
        }
        size--;
    }

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this(val, null);
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
