package ms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MinPositiveInArray {
    public static int solution(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (final int j : A) {
            set.add(j);
        }
        int min = 1;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (set.contains(i)) {
                min++;
            } else {
                return min;
            }
        }
        return min;
    }

    public static int solution1(int[] A) {
        // write your code in Java SE 11
        if (A.length == 0) {
            return 1;
        }
        int min = 1;

        Arrays.sort(A);
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] != min && A[i + 1] != min) {
                return min;
            } else {
                min++;
            }
        }

        return min == A[A.length - 1] ? min + 1 : min;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[] { 1, 3, 6, 4, 1, 2 }));
        System.out.println(solution(new int[] { 1, 2, 3 }));
        System.out.println(solution(new int[] { -1, -3 }));
    }
}
