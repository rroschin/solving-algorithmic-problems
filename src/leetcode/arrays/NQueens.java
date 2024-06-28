package leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        printGrid(nQueens.solveNQueens(1));
        printGrid(nQueens.solveNQueens(4));
        printGrid(nQueens.solveNQueens(5));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        placeNQueens(n, res);
        return res;
    }

    static void printGrid(List<List<String>> res) {
        for (List<String> r : res) {
            for (String s : r) {
                System.out.println(s);
            }
            System.out.println("----");
        }
    }
    int[][] placeNQueens(int n, List<List<String>> res) {
        int[][] grid = new int[n][];
        for (int r = 0; r < n; r++) {
            grid[r] = new int[n];
        }

        int queensLeft = n;
        int maxTries = Math.max(1, n * n / 2);
        int preOccupy = 0;
        while (queensLeft > 0 && maxTries > 0) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (grid[r][c] == 0) {
                        grid[r][c] = 1;
                        occupyRow(r, c, grid);
                        occupyCol(r, c, grid);
                        occupyLeftDiagonal(r, c, grid);
                        occupyRightDiagonal(r, c, grid);
                        queensLeft--;
                    }
                }
            }
            if (queensLeft == 0) {
                saveGridToRes(grid, res);
            }
            maxTries--;
            queensLeft = n;
            for (int r = 0; r < n; r++) {
                grid[r] = new int[n];
            }
            preOccupy++;
            int i = 0;
            for (int r = 0; r < n && i < preOccupy; r++) {
                for (int c = 0; c < n && i < preOccupy; c++) {
                    grid[r][c] = -1;
                    i++;
                }
            }
        }
        return grid;
    }

    private void saveGridToRes(int[][] grid, List<List<String>> res) {
        List<String> ans = new ArrayList<>();
        for (int[] gridRow : grid) {
            StringBuilder row = new StringBuilder();
            for (int cell : gridRow) {
                if (cell == 1) {
                    row.append("Q");
                } else {
                    row.append(".");
                }
            }
            ans.add(row.toString());
        }
        res.add(ans);
    }

    void occupyRow(int row, int col, int[][] grid) {
        for (int c = 0; c < grid.length; c++) {
            grid[row][c] = -1;
        }
        grid[row][col] = 1;
    }
    void occupyCol(int row, int col, int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            grid[r][col] = -1;
        }
        grid[row][col] = 1;
    }
    void occupyLeftDiagonal(int row, int col, int[][] grid) {
        for (int r = row, c = col; r < grid.length && c < grid.length; r++, c++) {
            grid[r][c] = -1;
        }
        for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            grid[r][c] = -1;
        }
        grid[row][col] = 1;
    }
    void occupyRightDiagonal(int row, int col, int[][] grid) {
        for (int r = row, c = col; r < grid.length && c >= 0; r++, c--) {
            grid[r][c] = -1;
        }
        for (int r = row, c = col; r >= 0 && c < grid.length; r--, c++) {
            grid[r][c] = -1;
        }
        grid[row][col] = 1;
    }
}
