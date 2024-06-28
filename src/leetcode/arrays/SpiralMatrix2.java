package leetcode.arrays;

import java.util.Arrays;

public class SpiralMatrix2 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new SpiralMatrix2().generateMatrix(3)));
    }
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][];
        for (int r = 0; r < n; r++) {
            res[r] = new int[n]; //0s
        }

        int counter = 1;
        int end = n * n;

        int r = 0;
        int c = 0;
        Direction dir = Direction.LEFT;
        while(counter <= end) {
            if (canPlace(r, c, res)) {
                res[r][c] = counter;
                counter++;
                r = updateRow(dir, r);
                c = updateCol(dir, c);
            } else {
                if (dir == Direction.LEFT) {
                    c--;
                } else if (dir == Direction.DOWN) {
                    r--;
                } else if (dir == Direction.RIGHT) {
                    c++;
                } else if (dir == Direction.UP) {
                    r++;
                }
                dir = nextDirection(dir);
                r = updateRow(dir, r);
                c = updateCol(dir, c);
            }
        }

        return res;
    }

    boolean canPlace(int r, int c, int[][] res) {
        boolean isRowInbound = 0 <= r && r < res.length;
        boolean isColInbound = 0 <= c && c < res[0].length;
        if (!isRowInbound || !isColInbound) {
            return false;
        }
        if (res[r][c] != 0) {
            return false;
        }
        return true;
    }

    int updateCol(Direction dir, int c) {
        switch (dir) {
            case LEFT -> { return c + 1; }
            case DOWN -> { return c; }
            case RIGHT -> { return c - 1; }
            case UP -> { return c; }
        }
        return -1;
    }

    int updateRow(Direction dir, int r) {
        switch (dir) {
            case LEFT -> { return r; }
            case DOWN -> { return r + 1; }
            case RIGHT -> { return r; }
            case UP -> { return r - 1; }
        }
        return -1;
    }

    Direction nextDirection(Direction curr) {
        switch (curr) {
            case LEFT -> { return Direction.DOWN; }
            case DOWN -> { return Direction.RIGHT; }
            case RIGHT -> { return Direction.UP; }
            case UP -> { return Direction.LEFT; }
        }
        throw new RuntimeException();
    }

    enum Direction {
        LEFT,
        DOWN,
        RIGHT,
        UP
    }
}

