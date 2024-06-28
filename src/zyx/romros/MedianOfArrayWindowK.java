package zyx.romros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MedianOfArrayWindowK {

    public static void main(String[] args) {
        int[] arr = {3, 6, 11, -5, 2, 10, 8};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(new MedianOfArrayWindowK().find1(new int[]{3, 6, 11, -5, 2, 10, 8}, 7));
        System.out.println(new MedianOfArrayWindowK().find(new int[]{3, 6, 11, -5, 2, 10, 8}, 7));
        System.out.println(new MedianOfArrayWindowK().find1(new int[]{3, 6, 11, -5, 2, 10, 8}, 6));
        System.out.println(new MedianOfArrayWindowK().find(new int[]{3, 6, 11, -5, 2, 10, 8}, 6));
        System.out.println(new MedianOfArrayWindowK().find1(new int[]{3, 6, 11, -5, 2, 10, 8}, 5));
        System.out.println(new MedianOfArrayWindowK().find(new int[]{3, 6, 11, -5, 2, 10, 8}, 5));
        System.out.println(new MedianOfArrayWindowK().find1(new int[]{3, 6, 11, -5, 2, 10, 8}, 4));
        System.out.println(new MedianOfArrayWindowK().find(new int[]{3, 6, 11, -5, 2, 10, 8}, 4));
        System.out.println(new MedianOfArrayWindowK().find1(new int[]{3, 6, 11, -5, 2, 10, 8}, 3));
        System.out.println(new MedianOfArrayWindowK().find(new int[]{3, 6, 11, -5, 2, 10, 8}, 3));
        System.out.println(new MedianOfArrayWindowK().find1(new int[]{3, 6, 11, -5, 2, 10, 8}, 2));
        System.out.println(new MedianOfArrayWindowK().find(new int[]{3, 6, 11, -5, 2, 10, 8}, 2));
        System.out.println(new MedianOfArrayWindowK().find1(new int[]{3, 6, 11, -5, 2, 10, 8}, 1));
        System.out.println(new MedianOfArrayWindowK().find(new int[]{3, 6, 11, -5, 2, 10, 8}, 1));
    }

    private List<Double> find(int[] arr, int k) {
        //add k elements to "heap keeper"
        //get median
        List<Double> res = new ArrayList<>();

        MedianOfDataStream obj = new MedianOfDataStream();
        for (int i = 0; i < k; i++) {
            obj.add(arr[i]);
        }
        res.add(obj.findMedian());

        int lo = 1;
        int hi = k;

        while (hi < arr.length) {
            obj.remove(arr[lo - 1]);
            obj.add(arr[hi]);
            res.add(obj.findMedian());
            lo++;
            hi++;
        }

        return res;
    }

    private List<Double> find2(int[] arr, int k) {
        List<Double> res = new ArrayList<>();

        /*
        [3, 6, 11], k = 2
        heap [3, 6 ,11]

        windows = arr.length - k + 1
        skip = k / 2
         */

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : arr) {
            heap.add(num);
        }

        int skip = k / 2;
        if (k % 2 == 0) {
            skip--;
        }
        while (skip > 0) {
            heap.poll();
            skip--;
        }

        int windows = (arr.length - k) + 1;
        while (windows > 0) {
            double med = (double) heap.poll();
            if (k % 2 == 0) {
                int med2 = heap.peek();
                med = (double) (med + med2) / 2;
            }
            res.add(med);
            windows--;
        }

        return res;
    }

    private List<Double> find1(int[] arr, int k) {
        Arrays.sort(arr);
        int lo = 0;
        int hi = lo + k; //excluding

        List<Double> res = new ArrayList<>();
        while (hi <= arr.length) {
            int medIdx = lo + (hi - lo) / 2; //1 + (5 - 1) / 2 -> 1 + 2 = 3
            if (k % 2 == 0) {
                res.add((double) (arr[medIdx] + arr[medIdx - 1]) / 2);
            } else {
                res.add((double) arr[medIdx]);
            }
            lo++;
            hi++;
        }

        return res;
    }
}
