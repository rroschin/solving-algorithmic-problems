package zyx.romros;

import java.util.HashMap;
import java.util.Map;

class MaxFruitCountOf2Types {

    public static void main(String[] args) {
        //Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
        System.out.println(findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
    }

    public static int findLength(char[] arr) {
        int max = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int end = 0; end < arr.length; end++) {
            char fruit = arr[end];
            if (map.size() <= 2) {
                map.merge(fruit, 1, Integer::sum);
                max = Math.max(max, end - start + 1);
            } else {
                Integer fruitCount = map.get(arr[start]);
                if (fruitCount == 1) {
                    map.remove(arr[start]);
                } else {
                    map.put(arr[start], fruitCount - 1);
                }
                start++;
                end--;
            }
        }
        return max;
    }

}
