package ms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MakeZeroes {
    static int[][] make_zeroes(int[][] matrix) {

        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            if (zeroRows.contains(i)) {
                continue;
            }
            for (int j = 0; j < matrix[i].length; j++) {
                if (zeroCols.contains(j)) {
                    continue;
                }
                if (matrix[i][j] == 0) {
                    rowToZeros(matrix, i);
                    colToZeros(matrix, j);
                    zeroRows.add(i);
                    zeroCols.add(j);
                    break;
                }
            }
        }

        return matrix;
    }

    private static void rowToZeros(int[][] matrix, int row) {
        for (int j = 0; j < matrix[row].length; j++) {
            matrix[row][j] = 0;
        }
    }

    private static void colToZeros(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(make_zeroes(new int[][]
                                                                   {
                                                                           { 1,  5,  45, 0,  81 },
                                                                           { 6,  7,  2,  82, 8  },
                                                                           { 20, 22, 49, 5,  5  },
                                                                           { 0,  23, 50, 4,  92 }
                                                                   })));
    }
}
