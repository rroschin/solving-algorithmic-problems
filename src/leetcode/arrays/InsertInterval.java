package leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InsertInterval {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new InsertInterval().insert(
                new int[][]{
                        {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
                },
                new int[]{4, 8})));
        System.out.println(Arrays.deepToString(new InsertInterval().insert(
                new int[][]{
                        {1, 5}
                },
                new int[]{6, 8})));
        System.out.println(Arrays.deepToString(new InsertInterval().insert(
                new int[][]{
                        {3, 5}, {12, 15}
                },
                new int[]{6, 6})));
        System.out.println(Arrays.deepToString(new InsertInterval().insert(
                new int[][]{
                        {0, 5},
                        {9, 12}
                },
                new int[]{7, 16})));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> mergedIntervals = new ArrayList<>();
        if (intervals.length == 0) {
            mergedIntervals.add(newInterval);
            return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
        }

        int startIdx = searchStart(intervals, newInterval[0]);
        System.out.println(startIdx + " vs " + searchStartBi(intervals, newInterval[0]));
        int endIdx = searchEnd(intervals, newInterval[1]);

        if (startIdx == intervals.length) {
            Collections.addAll(mergedIntervals, intervals);
            mergedIntervals.add(newInterval);
            return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
        }
        if (endIdx == -1) {
            mergedIntervals.add(newInterval);
            Collections.addAll(mergedIntervals, intervals);
            return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
        }
        if (endIdx < startIdx) {
            Collections.addAll(mergedIntervals, intervals);
            mergedIntervals.add(startIdx, newInterval);
            return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
        }

        int[] superInterval = new int[2];
        superInterval[0] = Math.min(newInterval[0], intervals[startIdx][0]);
        superInterval[1] = Math.max(newInterval[1], intervals[endIdx][1]);
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (i == startIdx) {
                mergedIntervals.add(superInterval);
            } else if (startIdx < i && i <= endIdx) {
                continue;
            } else {
                mergedIntervals.add(interval);
            }
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
    }

    private int searchStartBi(int[][] intervals, int start) {
        int lo = 0;
        int hi = intervals.length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int[] interval = intervals[mid];
            if (interval[0] <= start && start <= interval[1]) {
                return mid;
            }

            if (interval[0] > start) {
                hi = mid - 1;
            } else if (interval[0] < start) {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private int searchStart(int[][] intervals, int start) {
        int res = 0;
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= start && start <= interval[1]) {
                return i;
            }
            if (interval[0] > start) {
                return i;
            }
            res = i;
        }
        return res + 1;
    }

    private int searchEnd(int[][] intervals, int end) {
        int res = intervals.length - 1;
        for (int i = intervals.length - 1; i >= 0; i--) {
            int[] interval = intervals[i];
            if (interval[0] <= end && end <= interval[1]) {
                return i;
            }
            if (interval[1] < end) {
                return i;
            }
            res = i;
        }
        return res - 1;
    }

    public int[][] insertA(int[][] intervals, int[] newInterval) {
        List<int[]> mergedIntervals = new ArrayList<>();

        for (int[] interval : intervals) {

            // Before the new interval
            if (interval[1] < newInterval[0]) {
                mergedIntervals.add(interval);
            }
            // After the new interval
            else if (interval[0] > newInterval[1]) {
                mergedIntervals.add(newInterval);
                newInterval = interval; // Update newInterval to current, as it will be added in order afterwards
            }
            // Overlapping intervals
            else {
                // Merge with newInterval by updating its start and end as needed
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }

        // Add the last interval, which is either the original newInterval or the one merged with others
        mergedIntervals.add(newInterval);

        return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
    }

}
