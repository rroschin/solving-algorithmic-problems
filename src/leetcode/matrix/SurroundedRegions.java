package leetcode.matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class SurroundedRegions {

    private static void printCharArrayTable(char[][] charArray) {
        for (char[] row : charArray) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println("-----");
    }

    public static void main(String[] args) {
        char[][] board5 = {
                {'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'X', 'O'}
        };
        new SurroundedRegions().solve(board5);
        printCharArrayTable(board5);
        char[][] board4 = {
                {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'O'},
                {'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'O'},
                {'O', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'X', 'O', 'O', 'X', 'X', 'X'},
                {'O', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O'},
                {'O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O'},
                {'X', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        };
        new SurroundedRegions().solve(board4);
        System.out.println(Arrays.deepToString(board4));
        char[][] board3 = {
                {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'O'},
                {'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'O'},
                {'O', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'X', 'O', 'O', 'X', 'X', 'X'},
                {'O', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O'},
                {'O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O'},
                {'X', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        };
        new SurroundedRegions().solve(board3);
        System.out.println(Arrays.deepToString(board3));
        char[][] board2 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        new SurroundedRegions().solve(board2);
        System.out.println(Arrays.deepToString(board2));
        char[][] board1 = {
                {'X', 'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'X'}
        };
        new SurroundedRegions().solve(board1);
        System.out.println(Arrays.deepToString(board1));
    }

    public void solve(char[][] board) {
        if (board.length == 0) { return; }

        //fix top and bottom rows
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == 'O') {
                flip(board, new Point(0, col));
            }
            if (board[board.length - 1][col] == 'O') {
                flip(board, new Point(board.length - 1, col));
            }
        }
        //fix left and right cols
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == 'O') {
                flip(board, new Point(row, 0));
            }
            if (board[row][board[0].length - 1] == 'O') {
                flip(board, new Point(row, board[0].length - 1));
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 'O') {
                    board[row][col] = 'X';
                } else if (board[row][col] == 'N') {
                    board[row][col] = 'O';
                }
            }
        }
    }

    void flip(char[][] board, Point curr) {
        if (curr.row < 0 || curr.row >= board.length || curr.col < 0 || curr.col >= board[0].length || board[curr.row][curr.col] != 'O') {
            return;
        }
        board[curr.row][curr.col] = 'N';
        for (int[] dir : dirs) {
            flip(board, new Point(curr.row + dir[0], curr.col + dir[1]));
        }
    }

    public void solveByScanningAll(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    List<Point> flipped = new LinkedList<>();
                    int isFlipped = flip(board, new Point(i, j), flipped);
                    if (isFlipped == -1) {
                        for (Point point : flipped) {
                            board[point.row][point.col] = 'O';
                        }
                    }
                }
            }
        }
    }

    record Point(int row, int col) { }
    int[][] dirs = new int[][]{ {0, -1}, {-1, 0}, {0, 1}, {1, 0} };

    int flip(char[][] board, Point curr, List<Point> flipped) {
        if (curr.row < 0 || curr.row >= board.length || curr.col < 0 || curr.col >= board[0].length) {
            return 0;
        }
        if (board[curr.row][curr.col] == 'X') {
            return 0;
        }
        if (board[curr.row][curr.col] == 'O' &&
            (curr.row == 0 || (curr.row == board.length - 1) || curr.col == 0 || (curr.col == board[0].length - 1))) {
            return -1;
        }

        if (board[curr.row][curr.col] == 'O') {
            board[curr.row][curr.col] = 'X';
            flipped.add(new Point(curr.row, curr.col));
        }
        for (int[] dir : dirs) {
            int isFlipped = flip(board, new Point(curr.row + dir[0], curr.col + dir[1]), flipped);
            if (isFlipped == -1) { //unflip
                board[curr.row][curr.col] = 'O';
                return -1;
            }
        }
        return 1;
    }
}
