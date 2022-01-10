package google;

import java.util.Arrays;

class GameOfLife {
    public static void main(String[] args) {
        final int[][] board = {
            { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 }
        };
        new GameOfLife().gameOfLife(board);
        System.out.println(Arrays.deepToString(board)); //[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
    }

    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = calculateCellNextState(board, i, j);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = result[i][j];
            }
        }
    }

    int calculateCellNextState(int[][] board, int i, int j) {
        int cell = board[i][j];
        int liveNeighbors = calculateNumberOfLiveNeighbors(board, i, j);

        if (cell == 1) { //live
            if (liveNeighbors < 2) {
                return 0;
            } else if (liveNeighbors == 2 || liveNeighbors == 3) {
                return 1;
            } else {
                return 0;
            }
        } else { //dead
            return liveNeighbors == 3 ? 1 : 0;
        }
    }

    int calculateNumberOfLiveNeighbors(int[][] board, int i, int j) {
        int liveNeighbors = 0;

        for (int i1 = Math.max(0, i - 1); i1 < Math.min(board.length, i + 2); i1++) {
            for (int j1 = Math.max(0, j - 1); j1 < Math.min(board[0].length, j + 2); j1++) {
                if (i == i1 && j == j1) {
                    continue;
                }
                if (board[i1][j1] == 1) {
                    liveNeighbors++;
                }
            }
        }
        return liveNeighbors;
    }
}
