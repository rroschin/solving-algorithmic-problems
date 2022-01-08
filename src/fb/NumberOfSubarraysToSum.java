package fb;

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubarraysToSum {

    public static void main(String[] args) {
        //Input array = [1,-1,1,1] , target K = 2
        //Output: number of subarrays that sum to K => 2
        System.out.println(new NumberOfSubarraysToSum().numOfSubarrays(new int[] { 1, -1, 1, 1 }, 2)); // -> 2
        System.out.println(new NumberOfSubarraysToSum().numOfSubarrays(new int[] { 0, 1, -1, 1, 1, 0 }, 2)); // -> 2
        System.out.println(new NumberOfSubarraysToSum().numOfSubarrays(new int[] { 1, -1, 1, 1, 2, -2, 4 }, 2)); // -> 2
    }

    int numOfSubarrays(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            map.merge(sum, 1, Integer::sum);
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                map.merge(sum, 1, Integer::sum);
            }
        }

        return map.getOrDefault(k, 0);
    }

}
