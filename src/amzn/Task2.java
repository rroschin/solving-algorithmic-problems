package amzn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Task2 {

    public static long getTotalImbalance(List<Integer> weight) {
        long[] result = new long[1];

        System.out.println(Arrays.deepToString(combinations(weight)));

        return result[0];
    }

    private static void permutate(final List<Integer> weight, final int index, final List<Integer> perm, final long[] result) {
        if (index == weight.size()) {
            int[] mam = findMinAndMax(perm);
            result[0] += mam[1] - mam[0];
            return;
        }
        int[] mam = findMinAndMax(perm);
        result[0] += mam[1] - mam[0];

        final ArrayList<Integer> list1 = new ArrayList<>(perm);
        permutate(weight, index + 1, list1, result);
        final ArrayList<Integer> list2 = new ArrayList<>(perm);
        list2.add(weight.get(index));
        permutate(weight, index + 1, list2, result);
    }

    public static Integer[][] combinations(List<Integer> weight) {

        List<Integer[]> combinationList = new ArrayList<>();
        // Start i at 1, so that we do not include the empty set in the results
        for (long i = 1; i < Math.pow(2, weight.size()); i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < weight.size(); j++) {
                if ((i & (long) Math.pow(2, j)) > 0) {
                    // Include j in set
                    list.add(weight.get(j));
                }
            }
            combinationList.add(list.toArray(new Integer[0]));
        }
        return combinationList.toArray(new Integer[0][0]);
    }

    private static int[] findMinAndMax(final List<Integer> list) {
        if (list.isEmpty()) {
            return new int[] { 0, 0 };
        }
        return new int[] { Collections.min(list), Collections.max(list) };
    }

    public static long getTotalImbalance2For(List<Integer> weight) {
                /*
        Time limit exceeded, O(n^2)
        */
        if (weight == null || weight.size() == 0 || weight.size() == 1) {
            return 0;
        }

        long totalImbalance = 0L;

        List<Integer> shipment = new ArrayList<>();
        for (int i = 0; i < weight.size(); i++) {
            shipment.clear();
            shipment.add(weight.get(i)); //start

            for (int j = i; j < weight.size(); j++) {
                shipment.add(weight.get(j)); //add
                int currImbalance = Collections.max(shipment) - (int) Collections.min(shipment);
                totalImbalance += currImbalance;
            }
        }
        return totalImbalance;
    }

    public static long getTotalImbalance2ForWithMap(List<Integer> weight) {
        if (weight == null || weight.size() == 0 || weight.size() == 1) {
            return 0;
        }

        long totalImbalance = 0L;

        Map<String, Integer> cache = new HashMap<>();
        List<Integer> shipment = new ArrayList<>();
        StringBuilder shipmentId;
        for (int i = 0; i < weight.size(); i++) {
            shipment.clear();
            shipmentId = new StringBuilder();

            shipment.add(weight.get(i)); //start
            shipmentId.append(weight.get(i));

            for (int j = i + 1; j < weight.size(); j++) {
                shipment.add(weight.get(j)); //add
                shipmentId.append(weight.get(j));

                String sId = shipmentId.toString();
                Integer alreadyThere = cache.get(sId);
                if (alreadyThere != null) {
                    totalImbalance += alreadyThere;
                } else {
                    int currImbalance = Collections.max(shipment) - (int) Collections.min(shipment);
                    totalImbalance += currImbalance;
                    cache.put(sId, currImbalance);
                }
            }
        }
        return totalImbalance;
    }

    public static long getTotalImbalanceNotCompleted(List<Integer> weight) {
        // Write your code here
        if (weight == null || weight.size() == 0 || weight.size() == 1) {
            return 0;
        }

        long totalImbalance = 0L;

        /*
        w   = [1, 2, 3] -> 4
        min = [1, 1, 1]
        max = [1, 2, 3]

        [1, 2]    = 2 - 1 = 1 x
        [1, 2, 3] = 3 - 1 = 2 x
        [2, 3]    = 3 - 2 = 1


        sum = 0
        w         = [1, 2, 3] -> 4
        min = 1
        max = 1
        max - min = [0      ]
        sum += 0 //0

        min = min(1, 2) = 1
        max = max(1, 2) = 2
        max - min = [   1   ]
        sum += 1 //1

        min = min(1, 3) = 1
        max = max(1, 2) = 3
        max - min = [      2]
        sum += sum + 2

        w                 = [1, 2, 3, 4] -> 10
        1:
        min = 1, max = 1
                            [0,        ]
        min = 1, max = 2
                            [0, 1,     ]
        min = 1, max = 3
                            [0, 1, 2,  ]
                            [0, 1, 3,  ] ?
        min = 1, max = 4
                            [0, 1, 2, 3] -> 6
                            [0, 1, 3, 6]
        2:
        min = 2, max = 2
                            [0, 1, 2, 3]
        min = 2, max = 3
                            [0, 1, 3, 3]
        min = 2, max = 4
                            [0, 1, 3, 5] -> 9
        3:
        min = 3, max = 3
                            [0, 1, 3, 5]
        min = 3, max = 4
                            [0, 1, 3, 6] -> 10

        */

        int[] mins = new int[weight.size()];
        int[] maxs = new int[weight.size()];

        mins[0] = weight.get(0);
        maxs[0] = weight.get(0);

        for (int i = 1; i < weight.size(); i++) {
            Integer w = weight.get(i);
            mins[i] = Math.min(mins[i - 1], w);
            maxs[i] = Math.max(maxs[i - 1], w);
        }

        int[] sums = new int[weight.size()];
        sums[0] = 0;
        for (int i = 1; i < weight.size(); i++) {
            sums[i] = maxs[i] - mins[i] + sums[i - 1];
        }
        for (int i = 0; i < weight.size(); i++) {
            totalImbalance += sums[i];
        }

        return totalImbalance;
    }

    public static void main(String[] args) {
        System.out.print(getTotalImbalance2For(asList(3, 2, 3)) + " ?= "); //1+1+1 = 3
        System.out.println(getTotalImbalance(asList(3, 2, 3))); //1+1+1 = 3

        System.out.print(getTotalImbalance2For(asList(1, 2, 3, 4)) + " ?= "); //1+2+3+1+2+1 = 10
        System.out.println(getTotalImbalance(asList(1, 2, 3, 4))); //1+2+3+1+2+1 = 10

        System.out.print(getTotalImbalance2For(asList(3, 2, 3, 4, 1)) + " ?= "); //1+1+2+3+1+2+3+1+2+3=20
        System.out.println(getTotalImbalance(asList(3, 2, 3, 4, 1))); //1+1+2+3+1+2+3+1+2+3=20

        System.out.print(getTotalImbalance2For(asList(1, 2, 3)) + " ?= "); //1+2+1 = 4
        System.out.println(getTotalImbalance(asList(1, 2, 3))); //1+2+1 = 4
    }
}
