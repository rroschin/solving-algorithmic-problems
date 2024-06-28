package leetcode.arrays;

public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new MinimumPathSum().minPathSum(matrix1));
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] pathSums = new int[m][];
        for (int r = 0; r < m; r++) {
            pathSums[r] = new int[n];
            for (int c = 0; c < grid[0].length; c++) {
                pathSums[r][c] = -1;
            }
        }
        pathSums[m - 1][n - 1] = grid[m - 1][n - 1];
        walk(grid, m - 1, n - 1, pathSums, 0);
        return pathSums[0][0];
    }

    void walk(int[][] grid, int r, int c, int[][] pathSums, int fromSum) {
        boolean isRowInbound = 0 <= r && r < grid.length;
        boolean isColInbound = 0 <= c && c < grid[0].length;
        if (!isRowInbound || !isColInbound) {
            return;
        }

        if (pathSums[r][c] != -1 && pathSums[r][c] < fromSum + grid[r][c]) {
            return;
        }
        pathSums[r][c] = fromSum + grid[r][c];

        walk(grid, r - 1, c, pathSums, pathSums[r][c]);
        walk(grid, r, c - 1, pathSums, pathSums[r][c]);
    }
}
