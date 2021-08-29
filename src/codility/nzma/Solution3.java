package codility.nzma;

import java.util.List;

public class Solution3 {

  static int ROWS;
  static int COLUMN;

  int numberAmazonTreasureTrucks(int rows, int column, List<List<Integer>> grid) {
    ROWS = rows;
    COLUMN = column;

    boolean[][] visitedParkCells = new boolean[rows][column];
    int count = 0;
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < column; ++j) {
        final Integer currParkCell = grid.get(i).get(j);
        if (currParkCell == 1 && !visitedParkCells[i][j]) {
          visitCloseParkCells(i, j, grid, visitedParkCells);
          count++;
        }
      }
    }
    return count;
  }

  boolean canUseParkCell(int row, int col, List<List<Integer>> grid, boolean[][] visited) {
    return (row >= 0) //
           && (row < ROWS) //
           && (col >= 0) //
           && (col < COLUMN) //
           && (grid.get(row).get(col) == 1 && !visited[row][col]);
  }

  void visitCloseParkCells(int row, int col, List<List<Integer>> grid, boolean[][] visited) {
    int[] closeParkCellsRow = {-1, 0, 0, 1};
    int[] closeParkCellsCol = {0, -1, 1, 0};

    visited[row][col] = true;

    for (int k = 0; k < 4; k++) {
      if (canUseParkCell(row + closeParkCellsRow[k], col + closeParkCellsCol[k], grid, visited)) {
        visitCloseParkCells(row + closeParkCellsRow[k], col + closeParkCellsCol[k], grid, visited);
      }
    }
  }

}
