package leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FourSum {

    public static void main(String[] args) {
        System.out.println(new FourSum().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)); //[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        System.out.println(new FourSum().fourSum(new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90}, 200)); //[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int start = j + 1;
                int end = n - 1;

                while (start < end) {
                    int sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum == target) {
                        result.add(List.of(nums[i], nums[j], nums[start], nums[end]));
                        start++;
                        end--;
                        while (start < end && nums[start] == nums[start - 1]) {
                            start++;
                        }
                        while (start < end && nums[end] == nums[end + 1]) {
                            end--;
                        }
                    } else if (sum < target) {
                        start++;
                    } else {
                        end--;
                    }
                }

            }
        }

        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {

        Map<Integer, Set<List<Integer>>> mapAB = new HashMap<>();
        for (int a = 0; a < nums.length; a++) {
            for (int b = a + 1; b < nums.length; b++) {
                Integer sumAB = nums[a] + nums[b];
                Set<List<Integer>> sumVal = mapAB.get(sumAB);
                if (sumVal == null) {
                    sumVal = new HashSet<>();
                }
                sumVal.add(List.of(a, b));
                mapAB.put(sumAB, sumVal);
            }
        }

        Set<List<Integer>> result = new HashSet<>();
        Map<Integer, Set<List<Integer>>> mapCD = new HashMap<>();
        for (int c = 0; c < nums.length; c++) {
            for (int d = c + 1; d < nums.length; d++) {

                Integer sumCD = nums[c] + nums[d];
                Set<List<Integer>> sumVal = mapCD.get(sumCD);
                if (sumVal == null) {
                    sumVal = new HashSet<>();
                }
                sumVal.add(List.of(c, d));
                mapCD.put(sumCD, sumVal);
            }
        }

        for (Integer sumCD : mapCD.keySet()) {
            Set<List<Integer>> sumABVal = mapAB.get(target - sumCD);
            if (sumABVal == null) {
                continue;
            }
            Set<List<Integer>> sumCDVal = mapCD.get(sumCD);
            for (List<Integer> candCD : sumCDVal) {
                int c = candCD.get(0);
                int d = candCD.get(1);

                for (List<Integer> candAB : sumABVal) {
                    int a = candAB.get(0);
                    int b = candAB.get(1);

                    if (a != c && a != d && b != c && b != d) {
                        List<Integer> tmpList = Arrays.asList(nums[a], nums[b], nums[c], nums[d]);
                        Collections.sort(tmpList);
                        result.add(tmpList);
                    }
                }
            }

        }

        return new ArrayList<>(result);
    }

}
