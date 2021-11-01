package leetcode;

class LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(new LargestRectangleInHistogram().largestRectangleArea(new int[] { 4, 2, 0, 3, 2, 4, 3, 4 })); //10
        System.out.println(new LargestRectangleInHistogram().largestRectangleArea(new int[] { 2, 1, 2 })); //3
        System.out.println(new LargestRectangleInHistogram().largestRectangleArea(new int[] { 1, 1 })); //2
        System.out.println(new LargestRectangleInHistogram().largestRectangleArea(new int[] { 2, 1, 5, 6, 2, 3 })); //10
    }

    public int largestRectangleArea(int[] heights) {

        if (heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        if (heights.length == 2) {
            int min = Math.min(heights[0], heights[1]);
            return min > 0 ? min * 2 : Math.max(heights[0], heights[1]);
        }

        int[] ls = new int[heights.length];
        int[] rs = new int[heights.length];

        ls[0] = -1;
        rs[heights.length - 1] = heights.length;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;

            while (p >= 0 && heights[p] >= heights[i]) {
                p = ls[p];
            }
            ls[i] = p;
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < heights.length && heights[p] >= heights[i]) {
                p = rs[p];
            }
            rs[i] = p;
        }

        //        for (int i = 0; i < heights.length; i++) {
        //            ls[i] = findLeft(i, heights);
        //            rs[i] = findRight(i, heights);
        //        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] * (rs[i] - ls[i] - 1));
        }
        //        for (int i = 0; i < heights.length; i++) {
        //            int sq = calculateSq(i, ls[i], rs[i], heights);
        //            max = Math.max(max, sq);
        //        }

        return max;

    }

    int findLeft(int i, int[] heights) {
        if (i == 0) {
            return i;
        }
        if (heights[i] == 0) {
            return i;
        }

        int ret = i;
        for (int j = i - 1; j >= 0; j--) {
            if (heights[j] >= heights[i]) {
                ret = j;
                continue;
            } else {
                return j + 1;
            }
        }
        return ret;
    }

    int findRight(int i, int[] heights) {
        if (i == heights.length - 1) {
            return i;
        }
        if (heights[i] == 0) {
            return i;
        }
        int ret = i;
        for (int j = i + 1; j < heights.length; j++) {
            if (heights[j] >= heights[i]) {
                ret = j;
                continue;
            } else {
                return j - 1;
            }
        }
        return ret;
    }

    int calculateSq(int i, int l, int r, int[] heights) {
        return heights[i] * (r - l + 1);
    }
}
