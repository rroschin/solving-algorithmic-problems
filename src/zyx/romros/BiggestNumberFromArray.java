package zyx.romros;

import java.util.ArrayList;
import java.util.List;

public class BiggestNumberFromArray {

    public static void main(String[] args) {
        //Had an array of Integers [23, 452, 231, 654] and asked to create the biggest number. For example, it was 65445223231
        System.out.println(new BiggestNumberFromArray().biggestInteger(new int[] { 23, 452, 231, 654 }));
    }

    private String biggestInteger(final int[] nums) {
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }

        List<Integer> list = new ArrayList<>();
        for (final int i : nums) {
            list.add(i);
        }
        list.sort((o1, o2) -> {
            String s1 = String.valueOf(o1);
            String s2 = String.valueOf(o2);

            return (s2 + s1).compareTo(s1 + s2);
        });
        StringBuilder sb = new StringBuilder();
        for (final Integer i : list) {
            sb.append(i);
        }
        return sb.toString();
    }
}
