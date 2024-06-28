package zyx.romros;

import java.util.PriorityQueue;

public class KthLargestInStream {

    PriorityQueue<Integer> heap = new PriorityQueue<>();
    int k;

    public KthLargestInStream(int k) {
        this.k = k;
    }

    public static void main(String[] args) {
        KthLargestInStream obj = new KthLargestInStream(4);
        obj.add(5).add(0).add(-4).add(11).add(6).add(19).add(-43);
        System.out.println(obj.find());

        System.out.println(obj.add(87).find());
    }

    private KthLargestInStream add(int num) {
        if (heap.size() < k) {
            heap.add(num);
            return this;
        }

        if (heap.peek() > num) {
            return this;
        }

        heap.poll();
        heap.add(num);
        return this;
    }

    private int find() {
        return heap.peek();
    }
}
