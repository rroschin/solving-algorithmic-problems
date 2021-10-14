package leetcode;

import java.util.HashSet;
import java.util.Set;

class BattleshipsInBoard {

    public static void main(String[] args) {
        char[][] board = {
                { 'X', 'X', 'X', '.', 'X', '.', 'X', '.', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
                { '.', '.', '.', 'X', '.', 'X', '.', 'X', '.', '.', '.', '.', '.', '.', '.' },
                { '.', 'X', 'X', '.', '.', 'X', '.', '.', 'X', 'X', 'X', 'X', '.', 'X', '.' },
                { 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', 'X' },
                { '.', 'X', 'X', 'X', 'X', 'X', 'X', 'X', '.', 'X', 'X', '.', 'X', '.', 'X' },
                { 'X', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', 'X', '.', 'X' },
                { 'X', '.', '.', 'X', '.', 'X', '.', 'X', '.', 'X', 'X', '.', 'X', '.', 'X' },
                { '.', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { 'X', '.', '.', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
                { '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', 'X', '.', 'X', 'X', '.', 'X', '.', 'X', '.', 'X', '.', 'X', '.', '.' },
                { 'X', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', 'X', '.', 'X' },
                { '.', 'X', 'X', 'X', '.', 'X', 'X', 'X', '.', '.', 'X', '.', 'X', '.', 'X' },
                { 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' },
                { '.', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', '.', '.', '.', 'X', 'X' }
        };
        System.out.println(new BattleshipsInBoard().countBattleships(board));
    }

    public int countBattleships(char[][] board) {

        int m = board.length;
        if (m == 0)
            return 0;
        int n = board[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.')
                    continue;
                if (i > 0 && board[i - 1][j] == 'X')
                    continue;
                if (j > 0 && board[i][j - 1] == 'X')
                    continue;
                count++;
            }
        }

        return count;
    }

    public int countBattleshipsWithSet(char[][] board) {

        int w = board.length;
        int h = board[0].length;

        Set<String> visited = new HashSet<>();

        int count = 0;

        for (int i = 0; i < w; i++) { //cols
            for (int j = 0; j < h; j++) { //rows
                if (visited.contains(i + "_" + j)) {
                    continue;
                }
                char pos = board[i][j]; //X or .
                if (pos == 'X') {
                    markShipBorders(i, j, board, visited);
                    count += 1;
                }
            }
        }

        return count;
    }

    private void markShipBorders(int iStart, int jStart, char[][] board, Set<String> set) {
        boolean goRight = jStart + 1 != board[iStart].length && board[iStart][jStart + 1] == 'X';
        boolean goDown = iStart + 1 != board.length && board[iStart + 1][jStart] == 'X';

        //3 cases: goRight or goDown or single
        if (!goRight && !goDown) { //single
            set.add((iStart - 1) + "_" + jStart);
            set.add(iStart + "_" + (jStart - 1));
            set.add(iStart + "_" + jStart);
            set.add(iStart + "_" + (jStart + 1));
            set.add((iStart + 1) + "_" + jStart);
        } else if (goRight) {
            set.add((iStart - 1) + "_" + jStart);
            set.add(iStart + "_" + (jStart - 1));
            set.add(iStart + "_" + jStart);
            set.add((iStart + 1) + "_" + jStart);

            int last = jStart;
            for (int j = jStart + 1; j < board[0].length; j++) {
                if (board[iStart][j] == 'X') {
                    set.add((iStart - 1) + "_" + j);
                    set.add(iStart + "_" + j);
                    set.add((iStart + 1) + "_" + j);
                    last = j;
                } else {
                    break;
                }
            }
            set.add(iStart + "_" + (last + 1));
        } else if (goDown) {
            set.add((iStart - 1) + "_" + jStart);
            set.add(iStart + "_" + (jStart - 1));
            set.add(iStart + "_" + jStart);
            set.add(iStart + "_" + (jStart + 1));

            int last = iStart;
            for (int i = iStart + 1; i < board.length; i++) {
                if (board[i][jStart] == 'X') {
                    set.add(i + "_" + (jStart - 1));
                    set.add(i + "_" + jStart);
                    set.add(i + "_" + (jStart + 1));
                    last = i;
                } else {
                    break;
                }
            }
            set.add((last + 1) + "_" + jStart);
        }
    }
}

