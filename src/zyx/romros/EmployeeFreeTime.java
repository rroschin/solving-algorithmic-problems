package zyx.romros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EmployeeFreeTime {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new EmployeeFreeTime().findFreeTimeslots(new int[][][]{
                {{3, 5}, {8, 10}},
                {{4, 6}, {9, 12}},
                {{5, 6}, {8, 10}}
        })));

        System.out.println(Arrays.deepToString(new EmployeeFreeTime().findFreeTimeslots2(new int[][][]{
                {{3, 5}, {8, 10}},
                {{4, 6}, {9, 12}},
                {{5, 6}, {8, 10}}
        })));
        System.out.println(Arrays.deepToString(new EmployeeFreeTime().findFreeTimeslots3(new int[][][]{
                {{3, 5}, {8, 10}},
                {{4, 6}, {9, 12}},
                {{5, 6}, {8, 10}}
        })));

        System.out.println(Arrays.deepToString(new EmployeeFreeTime().findFreeTimeslots(new int[][][]{
                {{1, 3}, {5, 6}, {9, 10}},
                {{2, 4}, {7, 8}},
                {{8, 11}, {12, 14}}
        })));
        System.out.println(Arrays.deepToString(new EmployeeFreeTime().findFreeTimeslots2(new int[][][]{
                {{1, 3}, {5, 6}, {9, 10}},
                {{2, 4}, {7, 8}},
                {{8, 11}, {12, 14}}
        })));
        System.out.println(Arrays.deepToString(new EmployeeFreeTime().findFreeTimeslots3(new int[][][]{
                {{1, 3}, {5, 6}, {9, 10}},
                {{2, 4}, {7, 8}},
                {{8, 11}, {12, 14}}
        })));
        System.out.println(Arrays.deepToString(new EmployeeFreeTime().findFreeTimeslots3(new int[][][]{
                {{1, 2}, {3, 4}},
                {{2, 3}, {4, 6}}
        })));
    }

    private int[][] findFreeTimeslots3(int[][][] input) {
        int[][] sorted = input[0];
        for (int i = 1; i < input.length; i++) {
            sorted = mergeTwo(sorted, input[i]);
        }

        List<int[]> merged = new ArrayList<>();
        int[] isBeingProcessed = sorted[0];
        for (int i = 1; i < sorted.length; i++) {
            int[] curr = sorted[i];
            if (curr[0] > isBeingProcessed[1]) {
                merged.add(isBeingProcessed);
                isBeingProcessed = curr;
            } else {
                isBeingProcessed[1] = Math.max(isBeingProcessed[1], curr[1]);
            }
        }
        merged.add(isBeingProcessed);
        if (merged.size() == 1) {
            return new int[][]{{}};
        }

        List<int[]> res = new ArrayList<>();
        int[] prev = merged.get(0);
        int i = 1;
        while (i < merged.size()) {
            int[] curr = merged.get(i);

            int[] interval = new int[2];
            interval[0] = prev[1];
            interval[1] = curr[0];
            res.add(interval);

            i++;
            prev = curr;
        }

        return res.toArray(new int[0][]);
    }

    private int[][] mergeTwo(int[][] arrA, int[][] arrB) {
        int[][] merged = new int[arrA.length + arrB.length][];
        int i = 0;
        int a = 0;
        int b = 0;

        while (a < arrA.length && b < arrB.length) {
            if (arrA[a][0] < arrB[b][0]) {
                merged[i++] = arrA[a++];
            } else {
                merged[i++] = arrB[b++];
            }
        }

        while (a < arrA.length) {
            merged[i++] = arrA[a++];
        }
        while (b < arrB.length) {
            merged[i++] = arrB[b++];
        }

        return merged;
    }

    private int[][] findFreeTimeslots2(int[][][] input) {
        List<int[]> all = new ArrayList<>();
        for (int[][] employeeTimeSlots : input) {
            all.addAll(Arrays.asList(employeeTimeSlots));
        }
        if (all.isEmpty()) {
            return new int[][]{{0, 0}};
        }
        if (all.size() == 1) {
            return new int[][]{{}};
        }
        all.sort(Comparator.comparingInt(o -> o[0]));

        List<int[]> merged = new ArrayList<>();
        int[] isBeingProcessed = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            int[] curr = all.get(i);
            if (curr[0] > isBeingProcessed[1]) {
                merged.add(isBeingProcessed);
                isBeingProcessed = curr;
            } else {
                isBeingProcessed[1] = Math.max(isBeingProcessed[1], curr[1]);
            }
        }
        merged.add(isBeingProcessed);
        if (merged.size() == 1) {
            return new int[][]{{}};
        }

        List<int[]> res = new ArrayList<>();
        int[] prev = merged.get(0);
        int i = 1;
        while (i < merged.size()) {
            int[] curr = merged.get(i);

            int[] interval = new int[2];
            interval[0] = prev[1];
            interval[1] = curr[0];
            res.add(interval);

            i++;
            prev = curr;
        }

        return res.toArray(new int[0][]);
    }

    private int[][] findFreeTimeslots(int[][][] input) {
        /*
        [0-1,1-2,2-3,3-4,4-5,5-6,6-7,7-8,8-9,9-10,10-11,11-12,12-13,13-14,14-15,15-16,16-17,17-18,18-19,19-20,20-21,21-22,22-23,23-0]
        [0,  1,  2,  3,                                                                                                         24  ]
        [false,...,false]
        [false,true,true,true,false,true,false,false,false,true,]

          [inf, 3]
          [6, 8]
          [12, inf]

          remove inf -> [6, 8]
          n - number of occupied timeslots
         */

        boolean[] timeslots = new boolean[25];

        for (int[][] employeeTimeSlots : input) {
            for (int[] employeeTimeSlot : employeeTimeSlots) {
                int tsStart = employeeTimeSlot[0];
                int tsEnd = employeeTimeSlot[1];

                while (tsStart < tsEnd) {
                    timeslots[tsStart] = true;
                    tsStart++;
                }

            }
        }

        List<int[]> res = new ArrayList<>();

        int[] tmp = new int[2];
        boolean interval = false;
        for (int i = 0; i < timeslots.length; i++) {
            if (timeslots[i] == false) {
                if (!interval) {
                    tmp = new int[2];
                    tmp[0] = i - 1;
                    interval = true;
                }
            } else {
                if (interval) {
                    tmp[1] = i;
                    interval = false;
                    res.add(tmp);
                }
            }
        }

        if (!res.isEmpty()) {
            if (res.get(0)[0] == -1) {
                res.remove(0);
            }
        }

        return res.toArray(new int[0][]);
    }

}
