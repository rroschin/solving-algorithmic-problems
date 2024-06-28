package zyx.romros;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianOfDataStream {

    PriorityQueue<Integer> leftHeap = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue).reversed());
    PriorityQueue<Integer> rightHeap = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue));

    public static void main(String[] args) {
        MedianOfDataStream obj = new MedianOfDataStream();

        obj.add(3);
        System.out.println(obj.findMedian()); //[3] -> 3
        obj.add(6);
        System.out.println(obj.findMedian()); //[3, 6] -> 4.5
        obj.add(11);
        System.out.println(obj.findMedian()); //[3, 6, 11] -> 6
        obj.add(-5);
        System.out.println(obj.findMedian()); //[3, 6, 11, -5] -> 4.5
        obj.add(2);
        System.out.println(obj.findMedian()); //[3, 6, 11, -5, 2] -> 3
        obj.add(10);
        System.out.println(obj.findMedian()); //[3, 6, 11, -5, 2, 10] -> 4.5
        obj.add(8);
        System.out.println(obj.findMedian()); //[3, 6, 11, -5, 2, 10, 8] -> 6
    }

    public void remove(int num) {
        if (!leftHeap.remove(num)) {
            rightHeap.remove(num);
        }
        rebalalnce();
    }

    public void add(int num) {
        leftHeap.add(num);
        rebalalnce();
    }

    private void rebalalnce() {
        if (!leftHeap.isEmpty() && !rightHeap.isEmpty() && leftHeap.peek() > rightHeap.peek()) {
            rightHeap.add(leftHeap.poll());
        }
        if (leftHeap.size() > rightHeap.size() + 1) {
            rightHeap.add(leftHeap.poll());
        }
        if (rightHeap.size() > leftHeap.size() + 1) {
            leftHeap.add(rightHeap.poll());
        }
    }

    public double findMedian() {
        if (leftHeap.size() > rightHeap.size()) {
            return leftHeap.peek();
        }
        if (rightHeap.size() > leftHeap.size()) {
            return rightHeap.peek();
        }
        return (double) (leftHeap.peek() + rightHeap.peek()) / 2;
    }

}
