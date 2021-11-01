package leetcode;

import java.util.Arrays;

class KokoEatingBananas {

    public static void main(String[] args) {
        System.out.println(new KokoEatingBananas().minEatingSpeed(new int[] { 30, 11, 23, 4, 20 }, 6));
        System.out.println(new KokoEatingBananas().minEatingSpeed(new int[] { 3, 6, 7, 11 }, 8));
    }

    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int l = 1;
        int r = piles[piles.length - 1];
        while (l < r) {
            int m = (l + r) / 2;

            /*
            if (canEatAll(Arrays.copyOf(piles, piles.length), h, m)) {
                r = m;
            } else {
                l = m + 1;
            }
             */

            int total = 0;

            for (int p : piles)
                total += (p + m - 1) / m;
            if (total > h)
                l = m + 1;
            else
                r = m;

        }
        return l;
    }

    private boolean canEatAll(int[] piles, int h, int speed) {
        while (h > 0) {
            if (ateAll(piles)) {
                return true;
            }
            for (int i = 0; i < piles.length; i++) {
                if (piles[i] > 0) {
                    piles[i] -= speed;
                    h--;
                }
            }
        }

        return ateAll(piles);
    }

    private boolean ateAll(final int[] piles) {
        for (final int pile : piles) {
            if (pile > 0) {
                return false;
            }
        }
        return true;
    }
}
