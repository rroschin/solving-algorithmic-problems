package fb;

import java.util.SortedSet;
import java.util.TreeSet;

public class Kaitenzushi {

    public static void main(String[] args) {
        System.out.println(new Kaitenzushi().getMaximumEatenDishCount(6, new int[]{1, 2, 3, 3, 2, 1}, 1)); //5
        System.out.println(new Kaitenzushi().getMaximumEatenDishCount(6, new int[]{1, 2, 3, 3, 2, 1}, 2)); //4
        System.out.println(new Kaitenzushi().getMaximumEatenDishCount(7, new int[]{1, 2, 1, 2, 1, 2, 1}, 2)); //2
    }

    public int getMaximumEatenDishCount(int N, int[] D, int K) {
        if (D.length < 2) {
            return D.length;
        }
        if (K == 0) {
            return D.length;
        }

        SortedSet<Integer> memory = new TreeSet<>();
        int counter = 0;

        for (int dish : D) {
            if (canEat(dish, memory)) {
                counter++;
                updateEaten(dish, memory, K);
            }

        }

        return counter;
    }

    boolean canEat(int dish, SortedSet<Integer> memory) {
        return !memory.contains(dish);
    }

    void updateEaten(int dish, SortedSet<Integer> memory, int max) {
        memory.add(dish);
        if (memory.size() > max) {
            memory.remove(memory.first());
        }
    }
}
