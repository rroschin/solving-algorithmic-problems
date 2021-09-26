package leetcode.design;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class ShuffleAnArray {

    int[] original;

    public ShuffleAnArray(int[] nums) {
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        ShuffleAnArray obj = new ShuffleAnArray(nums);
        System.out.println(Arrays.toString(obj.reset()));
        System.out.println(Arrays.toString(obj.shuffle()));
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return this.original;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        final int l = this.original.length;
        int[] shuffled = new int[l];

        Set<Integer> set = new HashSet<>();
        Random random = new Random();

        for (int el : this.original) {
            int idx = random.nextInt(l);
            while (set.contains(idx)) {
                idx = random.nextInt(l);
            }
            set.add(idx);

            shuffled[idx] = el;
        }

        return shuffled;
    }

}
