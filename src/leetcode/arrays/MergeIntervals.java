package leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeIntervals {

    public static void main(String[] args) {
        final List<int[]> mergedTwo_one = new MergeIntervals().mergeTwo(
                List.of(new int[]{1, 2}, new int[]{3, 9}),
                List.of(new int[]{4, 6}, new int[]{8, 10}, new int[]{11, 12}));
        for (int[] interval : mergedTwo_one) {
            System.out.print(Arrays.toString(interval) + ", ");
        }
        System.out.println("\n-----");

        final List<int[]> mergedTwo_two = new MergeIntervals().mergeTwoWithHeap(
                List.of(new int[]{1, 2}, new int[]{3, 9}),
                List.of(new int[]{4, 6}, new int[]{8, 10}, new int[]{11, 12}));
        for (int[] interval : mergedTwo_two) {
            System.out.print(Arrays.toString(interval) + ", ");
        }
        System.out.println("\n-----");


        System.out.println(Arrays.deepToString(new MergeIntervals().merge(
                new int[][]{
                        {1, 3}, {2, 6}, {8, 10}, {15, 18}
                }
        )));
    }

    List<int[]> mergeTwoWithHeap(List<int[]> listA, List<int[]> listB) {
        List<int[]> merged = new ArrayList<>();

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparing(ints -> ints[0]));
        heap.addAll(listA);
        heap.addAll(listB);

        int[] curr = heap.poll();
        while (!heap.isEmpty()) {
            int[] candidate = heap.poll();
            if (candidate[0] > curr[1]) {
                merged.add(curr);
                curr = candidate;
            } else {
                curr[1] = Math.max(curr[1], candidate[1]);
            }
        }
        merged.add(curr);

        return merged;

    }

    List<int[]> mergeTwo(List<int[]> listA, List<int[]> listB) {
        List<int[]> merged = new ArrayList<>();

        int[] curr;

        int idxA = 0;
        int idxB = 0;

        if (listA.get(0)[0] < listB.get(0)[0]) {
            curr = listA.get(0);
            idxA++;
        } else {
            curr = listB.get(0);
            idxB++;
        }

        while (idxA < listA.size() || idxB < listB.size()) {

            int[] candidate;
            if (idxA < listA.size() && listA.get(idxA)[0] < listB.get(idxA)[0]) {
                candidate = listA.get(idxA);
                idxA++;
            } else if (idxB < listB.size()) {
                candidate = listB.get(idxB);
                idxB++;
            } else {
                throw new RuntimeException();
            }

            if (candidate[0] > curr[1]) {
                merged.add(curr);
                curr = candidate;
                continue;
            }

            if (candidate[0] <= curr[1]) {
                curr[1] = Math.max(curr[1], candidate[1]);
            }
        }
        merged.add(curr);

        return merged;
    }


    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }

        PriorityQueue<int[]> intervalsHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        intervalsHeap.addAll(Arrays.asList(intervals));

        List<int[]> merged = new ArrayList<>();

        int[] prev = intervalsHeap.poll();
        while (!intervalsHeap.isEmpty()) {
            int[] curr = intervalsHeap.poll();

            if (curr[0] <= prev[1]) {
                prev[0] = Math.min(prev[0], curr[0]);
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                merged.add(prev);
                prev = curr;
            }
        }
        merged.add(prev);

        return merged.toArray(new int[merged.size()][2]);
    }
}
