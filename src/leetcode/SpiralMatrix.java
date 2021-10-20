package leetcode;

import java.util.ArrayList;
import java.util.List;

class SpiralMatrix {

    public static void main(String[] args) {
        System.out.println(new SpiralMatrix().spiralOrder(new int[][] {
                { 2, 5 },
                { 8, 4 },
                { 0, -1 }
        }));
        System.out.println(new SpiralMatrix().spiralOrder(new int[][] {
                { 1, 2 },
                { 3, 4 }
        }));
        System.out.println(new SpiralMatrix().spiralOrder(new int[][] {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        }));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 1) {
            for (Integer i : matrix[0]) {
                list.add(i);
            }
            return list;
        }
        if (matrix[0].length == 1) {
            for (final int[] ints : matrix) {
                list.add(ints[0]);
            }
            return list;
        }

        int colLeftBorder = 0;
        int colRightBorder = matrix[0].length - 1;
        int rowTopBorder = 1;
        int rowBottomBorder = matrix.length - 1;

        int i = 0;
        int j = 0;

        int count = matrix.length * matrix[0].length;
        int type = 0; //0: row, 1: col, 2: revRow, 3: revCol
        while (count > 0) {
            if (type == 0) {
                list.add(matrix[i][j]);
                if (j == colRightBorder) { //last in a row
                    type = 1;
                    colRightBorder--;
                    i++;
                } else {
                    j++;
                }
            } else if (type == 1) {
                list.add(matrix[i][j]);
                if (i == rowBottomBorder) { //last in a col
                    type = 2;
                    rowBottomBorder--;
                    j--;
                } else {
                    i++;
                }
            } else if (type == 2) {
                list.add(matrix[i][j]);
                if (j == colLeftBorder) {
                    type = 3;
                    colLeftBorder++;
                    i--;
                } else {
                    j--;
                }
            } else if (type == 3) {
                list.add(matrix[i][j]);
                if (i == rowTopBorder) {
                    type = 0;
                    rowTopBorder++;
                    j++;
                } else {
                    i--;
                }
            }
            count--;
        }

        return list;
    }
}
