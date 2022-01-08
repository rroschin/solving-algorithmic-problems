package zyx.romros;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class AboveAverageSubarrays {

    public static void main(String[] args) {
        /*
         * A = [3, 4, 2]
         * output = [[1, 2], [1, 3], [2, 2]]
         * The above-average subarrays are [3, 4], [3, 4, 2], and [4].
         */

        System.out.println(new AboveAverageSubarrays().aboveAverageSubarrays(new int[] { 3, 4, 2 }));
    }

    List<Subarray> aboveAverageSubarrays(int[] A) {
        if (A.length == 0) {
            return new ArrayList<>();
        }
        if (A.length == 1) {
            return List.of(new Subarray(1, 1));
        }

        PriorityQueue<Subarray> pq = new PriorityQueue<>((o1, o2) -> {
            int r = Integer.compare(o1.left, o2.left);
            if (r != 0) {
                return r;
            } else {
                return Integer.compare(o1.right, o2.right);
            }
        });

        int arrSum = 0;
        for (int i = 0; i < A.length; i++) {
            arrSum += A[i];
        }
        int start = 0;
        int end = 0;
        int windowSum = 0;
        while (start <= end && end < A.length) {
            windowSum += A[end];
            int windowAvg = windowSum / (end - start + 1);

            arrSum -= A[end];
            int arrAvg = (A.length - end - 1) > 0 ? arrSum / (A.length - end - 1) : 0;

            if (windowAvg > arrAvg) {
                pq.add(new Subarray(start + 1, end + 1));
                end++;
            } else {
                end++;
            }
            if (end == A.length) {
                start++;
                end = start;
                windowSum = 0;
                arrSum = 0;
                for (int i = end; i < A.length; i++) {
                    arrSum += A[i];
                }
            }
        }

        List<Subarray> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        return res;

    }

    static class Subarray {
        int left;
        int right;

        public Subarray(final int left, final int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "{" +
                   "left=" + left +
                   ", right=" + right +
                   "}";
        }
    }

}
