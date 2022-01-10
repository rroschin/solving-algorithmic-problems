package leetcode;

import java.util.Arrays;
import java.util.TreeMap;

class MaximumProfitInJobScheduling {
    public static void main(String[] args) {
        System.out.println(new MaximumProfitInJobScheduling().jobScheduling(new int[] { 1, 2, 3, 4, 6 },
            new int[] { 3, 5, 10, 6, 9 },
            new int[] { 20, 20, 100, 70, 60 }
        ));
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] combine = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            combine[i] = new int[] { startTime[i], endTime[i], profit[i] };
        }
        Arrays.sort(combine, (a, b) -> a[1] - b[1]);

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = 0;

        for (int[] curr : combine) {
            Integer prev = map.floorKey(curr[0]);
            int prevSum = prev == null ? 0 : map.get(prev);
            ans = Math.max(ans, prevSum + curr[2]);
            map.put(curr[1], ans);
        }
        return ans;
    }
}
