package zyx.romros;

import java.util.HashMap;
import java.util.Map;

class SumOfThree {
    public static void main(String[] args) {
        System.out.println(new SumOfThree().find_sum_of_three(new int[] { -25, -10, -7, -3, 2, 4, 8, 10 }, 1));
    }

    public boolean find_sum_of_three(int arr[], int rsum) {
        // TODO: Write - Your - Code

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(i, rsum - arr[i]); //0(3): 20-3, 1(7): 20-7, ...
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int rest = arr[i] + arr[j];
                Map<Integer, Integer> tmp = new HashMap<>(map);
                tmp.remove(i);
                tmp.remove(j);
                if (tmp.containsValue(rest)) {
                    return true;
                }
            }
        }

        return false;
    }
}
