package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

class KthLargestElementInArray {

    public static void main(String[] args) {
        System.out.println(new KthLargestElementInArray().findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 2));
        System.out.println(new KthLargestElementInArray().findKthLargest(new int[] { 3,2,3,1,2,4,5,5,6 }, 4));
    }

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int n : nums) {
            pq.offer(n);
        }

        int last = 0;
        while (k > 0) {
            last = pq.poll();
            k--;
        }
        return last;

    }
}
