package fb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IceCreamParlor {

    public static void main(String[] args) {
        int m1 = 4;
        int[] arr1 = {1, 4, 5, 3, 2};
        System.out.println(Arrays.toString(new IceCreamParlor().solve(m1, arr1)));

        int m2 = 4;
        int[] arr2 = {2, 2, 4, 3};
        System.out.println(Arrays.toString(new IceCreamParlor().solve(m2, arr2)));
    }

    private int[] solve(int m, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                int i1 = map.get(arr[i]);
                return new int[]{i1 + 1, i + 1};
            }

            Integer target = m - arr[i];
            map.put(target, i);
        }

        return new int[]{-1, -1};
    }
}
