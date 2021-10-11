package fb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Add any extra import statements you may need here

class ReverseOperations {

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    // Add any helper functions you may need here


  /*

  n = [1, 2, 8, 6, 10, 9, 11, 2, 4, 80, 3]
  --------^............^
         [2, 8, 6, 10] -> [10, 6, 8, 2]
  n = [1, 10, 6, 8, 2, 9, 11, 2, 4, 80, 3]
  --------^............^------^.........^
          x                   x
  */

    public static void main(String[] args) {
        new ReverseOperations().run();
    }

    Node reverse(Node head) {
        // Write your code here
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return head;
        }

        //[x, x1]
        List<Integer> list = new ArrayList<>();
        Node evenStart = null;
        Node i = head;
        while (i != null) {
            if (i.data % 2 == 0) { //even
                list.add(i.data);
                if (evenStart == null) {
                    evenStart = i;
                }
            } else if ((i.data % 2 != 0 || i.next == null) && !list.isEmpty()) { //(odd OR end) AND list not empty
                if (list.size() == 1) {
                    //do nothing, reserse (1) = original
                } else {
                    Collections.reverse(list); //O(n/2)
                    int li = 0;
                    Node j = evenStart;
                    while (j != null && li < list.size()) {
                        j.data = list.get(li);
                        li++;
                        j = j.next;
                    }
                    list.clear();
                    evenStart = null;
                }
            }
            i = i.next;
        }

        if (!list.isEmpty() && evenStart != null) {
            Collections.reverse(list);
            int li = 0;
            Node j = evenStart;
            while (j != null && li < list.size()) {
                j.data = list.get(li);
                li++;
                j = j.next;
            }
        }

        return head;
    }

    void printLinkedList(Node head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.data);
            head = head.next;
            if (head != null)
                System.out.print(" ");
        }
        System.out.print("]");
    }

    void check(Node expectedHead, Node outputHead) {
        boolean result = true;
        Node tempExpectedHead = expectedHead;
        Node tempOutputHead = outputHead;
        while (expectedHead != null && outputHead != null) {
            result &= (expectedHead.data == outputHead.data);
            expectedHead = expectedHead.next;
            outputHead = outputHead.next;
        }
        if (!(expectedHead == null && outputHead == null))
            result = false;

        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printLinkedList(tempExpectedHead);
            System.out.print(" Your output: ");
            printLinkedList(tempOutputHead);
            System.out.println();
        }
        test_case_number++;
    }

    Node createLinkedList(int[] arr) {
        Node head = null;
        Node tempHead = head;
        for (int v : arr) {
            if (head == null) {
                head = new Node(v);
                tempHead = head;
            } else {
                head.next = new Node(v);
                head = head.next;
            }
        }
        return tempHead;
    }

    public void run() {

        int[] arr_1 = { 1, 2, 8, 9, 12, 16 };
        int[] expected1 = { 1, 8, 2, 9, 16, 12 };
        Node head_1 = createLinkedList(arr_1);
        Node expected_1 = createLinkedList(expected1);
        Node output_1 = reverse(head_1);
        check(expected_1, output_1);

        int[] arr_2 = { 2, 18, 24, 3, 5, 7, 9, 6, 12 };
        int[] expected2 = { 24, 18, 2, 3, 5, 7, 9, 12, 6 };
        Node head_2 = createLinkedList(arr_2);
        Node expected_2 = createLinkedList(expected2);
        Node output_2 = reverse(head_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    class Node {
        int data;
        Node next;

        Node(int x) {
            data = x;
            next = null;
        }
    }
}
