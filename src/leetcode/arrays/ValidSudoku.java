package leetcode.arrays;

import java.util.HashSet;
import java.util.Set;

class ValidSudoku {

    public static boolean isValidSudokuSimpleSet(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char cur = board[i][j];
                if (cur != '.') {
                    if (!seen.add(cur + " added in row " + i) ||
                        !seen.add(cur + " added in col " + j) ||
                        !seen.add(cur + " added in sub-matrix " + i / 3 + "-" + j / 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isValidSudokuWithBitwise(char[][] board) {
        int[] row = new int[9];
        int[] col = new int[9];
        int[] blk = new int[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.')
                    continue;
                int bit = 1 << (c - '1');

                if ((row[i] & bit) == bit)
                    return false;
                row[i] |= bit;

                if ((col[j] & bit) == bit)
                    return false;
                col[j] |= bit;

                final int sq = 3 * (i / 3) + j / 3;
                if ((blk[sq] & bit) == bit)
                    return false;
                blk[sq] |= bit;
            }
        }
        return true;
    }

    public static boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            Set<Character> row = new HashSet<>();
            Set<Character> col = new HashSet<>();
            for (int j = 0; j < board[i].length; j++) {
                final char inRow = board[i][j];
                if (inRow != '.') {
                    if (row.contains(inRow)) {
                        return false;
                    } else {
                        row.add(inRow);
                    }
                }

                final char inCol = board[j][i];
                if (inCol != '.') {
                    if (col.contains(inCol)) {
                        return false;
                    } else {
                        col.add(inCol);
                    }
                }
            }
        }

        for (int sqRow = 0; sqRow < board.length; sqRow += 3) {
            for (int sqCol = 0; sqCol < board.length; sqCol += 3) {
                Set<Character> sq = new HashSet<>();
                for (int i = sqRow; i < sqRow + 3; i++) {
                    for (int j = sqCol; j < sqCol + 3; j++) {

                        final char inSq = board[i][j];
                        if (inSq != '.') {
                            if (sq.contains(inSq)) {
                                return false;
                            } else {
                                sq.add(inSq);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] validBoard =
                {
                        { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                        { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                        { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                        { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                        { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                        { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                        { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                        { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                        { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
                };
        char[][] invalidBoard =
                {
                        { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
                        { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                        { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                        { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                        { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                        { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                        { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                        { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                        { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
                };

        System.out.println(isValidSudokuWithBitwise(validBoard));
        System.out.println(isValidSudokuWithBitwise(invalidBoard));

        char[][] invalidBoard2 = {
                { '.', '.', '.', '.', '5', '.', '.', '1', '.' },
                { '.', '4', '.', '3', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '3', '.', '.', '1' },
                { '8', '.', '.', '.', '.', '.', '.', '2', '.' },
                { '.', '.', '2', '.', '7', '.', '.', '.', '.' },
                { '.', '1', '5', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '2', '.', '.', '.' },
                { '.', '2', '.', '9', '.', '.', '.', '.', '.' },
                { '.', '.', '4', '.', '.', '.', '.', '.', '.' }
        };
        System.out.println(isValidSudokuWithBitwise(invalidBoard2));
    }
}
