package zyx.romros;

import java.util.ArrayList;
import java.util.List;

public class RiverSizes {

    public static void main(String[] args) {
        System.out.println(new RiverSizes().getSizes(new int[][]{
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0},
                {0, 0, 0, 1, 1}
        })); //2, 1, 2, 2, 7
    }

    private List<Integer> getSizes(int[][] grid) {
        List<Integer> sizes = new ArrayList<>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    sizes.add(exploreRiver(grid, r, c));
                }
            }
        }

        return sizes;
    }

    int[][] dirs = new int[][]{
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    private int exploreRiver(int[][] grid, int r, int c) {
        boolean isRowInbound = 0 <= r && r < grid.length;
        boolean isColInbound = 0 <= c && c < grid[0].length;
        if (!isRowInbound || !isColInbound) {
            return 0;
        }
        if (grid[r][c] != 1) {
            return 0;
        }
        int currSize = 1;
        grid[r][c] = 0;
        for (int[] dir : dirs) {
            currSize += exploreRiver(grid, r + dir[0], c + dir[1]);
        }
        return currSize;
    }
}
