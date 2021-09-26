package leetcode.topinterviewquestionseasy.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class RotateImage {

    public static void rotateReverseAndTranspose(int[][] matrix) {
        reverseTopBottom(matrix);
        transpose(matrix);
    }

    public static void reverseTopBottom(int[][] matrix) {
        int top = 0;
        int bottom = matrix.length - 1;

        while (top < bottom) {
            int[] temp = matrix[top];
            matrix[top] = matrix[bottom];
            matrix[bottom] = temp;

            top++;
            bottom--;
        }
    }

    public static void transpose(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void rotateWithQueue(int[][] matrix) {

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                queue.add(matrix[j][i]);
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = queue.poll();
            }
        }
    }

    public static void rotateFlip(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < (size + 1) / 2; i++) {
            for (int j = 0; j < size / 2; j++) {
                int tmp = matrix[size - 1 - j][i];
                matrix[size - 1 - j][i] = matrix[size - 1 - i][size - 1 - j];
                matrix[size - 1 - i][size - 1 - j] = matrix[j][size - 1 - i];
                matrix[j][size - 1 - i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }

    }

    public static void rotateLevelByLevel(int[][] matrix) {
        if (matrix.length == 0 || matrix.length == 1) {
            return;
        }

        int topLeftRow = 0;
        int topLeftCol = 0;
        int bottomRightRow = matrix.length - 1;
        int bottomRightCol = matrix[0].length - 1;

        while (topLeftRow + 1 < bottomRightRow && topLeftCol + 1 < bottomRightCol) {
            topLeftRow++;
            topLeftCol++;
            bottomRightRow--;
            bottomRightCol--;
        }

        while (topLeftRow >= 0 && topLeftCol >= 0) {
            rotateLevel(matrix, topLeftRow, topLeftCol, bottomRightRow, bottomRightCol);
            topLeftRow--;
            topLeftCol--;
            bottomRightRow++;
            bottomRightCol++;
        }
    }

    private static void rotateLevel(final int[][] matrix, final int topLeftRow, final int topLeftCol, final int bottomRightRow, final int bottomRightCol) {
        /*
        11 12    21 11
        21 22 -> 22 12

        11 -> 21
        12 -> 11
        21 -> 22
        22 -> 12
         */

        /*
        00 01 02    20 10 00
        10    12 -> 21    01
        20 21 22    22 12 02

        rc    ij
        00 <- 20
        01 <- 10
        02 <- 00 !
        10 <- 21
        11 skip?
        12 <- 01 !
        20 <- 22
        21 <- 12 !
        22 <- 02 !

       tmp = 00
        00 = 20 (+2+0)
        20 = 22 (+0+2)
        22 = 02 (+2+0)
        02 = tmp (00) (+0+2)

       tmp = 01
       01 = 10 (+1+0)
       10 = 21 (+1+1)
       21 = 12 (-1+1)
       12 = tmp (01) (+1-1)
         */

        final int[] row0backup = new int[matrix.length];
        System.arraycopy(matrix[topLeftRow], 0, row0backup, 0, row0backup.length);
        final int[] colNbackup = new int[matrix.length];
        for (int r = 0; r < matrix.length; r++) {
            colNbackup[r] = matrix[r][bottomRightCol];
        }
        int j = topLeftCol;
        for (int r = topLeftRow; r < bottomRightCol + 1; r++) {
            int i = bottomRightCol;
            for (int c = topLeftCol; c < bottomRightCol + 1; c++) {
                if (r > topLeftRow && c > topLeftCol && r < bottomRightRow && c < bottomRightCol) {
                    //skip
                } else {
                    matrix[r][c] = matrix[i][j];
                }
                i--;
            }
            j++;
        }
        for (int r = topLeftRow; r < bottomRightCol + 1; r++) {
            matrix[r][bottomRightCol] = row0backup[r];
        }
        for (int c = topLeftRow, b = bottomRightRow; c < bottomRightCol + 1; c++, b--) {
            matrix[bottomRightRow][c] = colNbackup[b];
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 5, 1, 9, 11 },
                { 2, 4, 8, 10 },
                { 13, 3, 6, 7 },
                { 15, 14, 12, 16 }
        };
        rotateFlip(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
