package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Definition for singly-linked list.
 */
class MergeKSortedLists {

    public static void main(String[] args) {
        //[[1,4,5],[1,3,4],[2,6]]
        final ListNode head = new MergeKSortedLists().mergeKLists(new ListNode[] {
                new ListNode(1, new ListNode(4, new ListNode(5, new ListNode(9)))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))
        });
        System.out.println(ListNode.print(head));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> all = new LinkedList<>(Arrays.asList(lists));
        while (all.size() > 1) {
            ListNode one = all.poll();
            ListNode two = all.poll();
            ListNode three = mergeTwo(one, two);
            all.add(three);
        }
        return all.poll();
    }

    public ListNode mergeTwo(ListNode head1, ListNode head2) {
        ListNode newHead = null;
        ListNode node = newHead;

        while (head1 != null && head2 != null) {
            ListNode chosen;
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

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        PriorityQueue<ListNode> pointers = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode head : lists) {
            if (head != null) {
                pointers.add(head);
            }
        }
        ListNode result = new ListNode(0);
        ListNode newHead = result;

        while (!pointers.isEmpty()) {
            ListNode minNode = pointers.poll();

            result.next = new ListNode(minNode.val);
            result = result.next;

            if (minNode.next != null) {
                pointers.add(minNode.next);
            }
        }

        return newHead.next;

    }

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode[] pointers = new ListNode[lists.length];
        int i = 0;
        for (ListNode head : lists) {
            if (head != null) {
                pointers[i] = head;
                i++;
            }
        }
        if (i == 0) {
            return null;
        }
        int activeLists = i;

        ListNode result = new ListNode(0);
        ListNode newHead = result;

        while (activeLists > 0) {
            ListNode minNode = new ListNode(Integer.MAX_VALUE);
            int minNodeIndex = -1;

            for (int j = 0; j < i; j++) {
                if (pointers[j] != null && pointers[j].val < minNode.val) {
                    minNode = pointers[j];
                    minNodeIndex = j;
                }
            }

            result.next = new ListNode(minNode.val);
            result = result.next;

            pointers[minNodeIndex] = minNode.next;
            if (minNode.next == null) {
                activeLists--;
            }

        }

        return newHead.next;

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

        public static String print(ListNode node) {
            StringBuilder sb = new StringBuilder();
            while (node != null) {
                sb.append(node.val).append(" -> ");
                node = node.next;
            }
            return sb.toString();
        }
    }
}

