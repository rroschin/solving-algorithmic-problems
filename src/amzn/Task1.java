package amzn;

import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static long countDecreasingRatings(List<Integer> ratings) {
        // Write your code here
        if (ratings == null || ratings.size() == 0) {
            return 0;
        }
        long numberOfGroups = ratings.size(); //include each element r as told by the task

        List<Integer> activeGroupList = new ArrayList<>();

        for (int i = 1; i < ratings.size(); i++) {
            if (ratings.get(i - 1) - 1 == ratings.get(i)) { //r, r-1
                if (activeGroupList.isEmpty()) { //new group
                    activeGroupList.add(ratings.get(i - 1));
                }
                activeGroupList.add(ratings.get(i)); //ongoing group
            } else {
                if (!activeGroupList.isEmpty()) { //group exists and ends, calculate
                    /* Triangular Number Sequence = n * (n + 1) / 2 */
                    final int n = activeGroupList.size() - 1;
                    long numsToAdd = (long) n * (n + 1) / 2;
                    numberOfGroups += numsToAdd;

                    activeGroupList.clear();
                }
            }
        }
        if (!activeGroupList.isEmpty()) { //group exists and ends, calculate
            /* Triangular Number Sequence = n * (n + 1) / 2 */
            final int n = activeGroupList.size() - 1;
            long numsToAdd = (long) n * (n + 1) / 2;
            numberOfGroups += numsToAdd;

            activeGroupList.clear();
        }


        return numberOfGroups;
    }

    public static void main(String[] args) {
        System.out.println(countDecreasingRatings(List.of(4, 3, 5, 4, 3)));
    }
}
