package leetcode;

class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(new ContainerWithMostWater().maxArea(new int[] { 2, 3, 10, 5, 7, 8, 9 })); //36
        System.out.println(new ContainerWithMostWater().maxArea(new int[] { 4, 3, 2, 1, 4 })); //16
        System.out.println(new ContainerWithMostWater().maxArea(new int[] { 1, 1 })); //1
        System.out.println(new ContainerWithMostWater().maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 })); //49
    }

    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }

        int max = Math.min(height[0], height[height.length - 1]) * (height.length - 1);

        int p1 = 0;
        int p2 = height.length - 1;
        while (p1 < p2) {
            int h = Math.min(height[p1 + 1], height[p2]);
            int sq = h * (p2 - (p1 + 1));

            max = Math.max(max, sq);

            h = Math.min(height[p1], height[p2 - 1]);
            sq = h * ((p2 - 1) - p1);

            max = Math.max(max, sq);
            p1++;
            p2--;
        }

        return max;
    }
}
