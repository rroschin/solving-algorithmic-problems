package zyx.romros;

public class WordSearch {
    public static void main(String[] args) {
        String[] board = new String[]{
                "helloworld",
                "airbuffete",
                "youmrehinr",
                "isempfoyee",
                "ahaipffear",
                "nocrbuuter"
        };
        String word0 = "airbuf";
        String word1 = "airbuffer";
        String word2 = "airbuilder";

        System.out.println(new WordSearch().search(board, word0)); //true
        System.out.println(new WordSearch().search(board, word1)); //true
        System.out.println(new WordSearch().search(board, word2)); //false
    }

    record Point(int row, int col) { }

    int[][] dirs = new int[][]{
            {0,1},
            {1,0},
            {0,-1},
            {-1,0}
    };

    boolean search(String[] board, String word) {
        Point start = findStart(board, word.charAt(0), new Point(0,0));
        while(start != null) {
            boolean[][] seen = new boolean[board.length][board[0].length()];
            if (scan(board, word, 0, start, seen)) {
                return true;
            }
            start = findStart(board, word.charAt(0), new Point(start.row, start.col + 1));
        }

        return false;
    }

    private boolean scan(String[] board, String word, int cidx, Point curr, boolean[][] seen) {
        if (curr.row < 0 || curr.row >= board.length || curr.col < 0 || curr.col >= board[0].length()) {
            return false;
        }
        if (seen[curr.row][curr.col]) {
            return false;
        }
        if (board[curr.row].charAt(curr.col) != word.charAt(cidx)) {
            return false;
        }
        if (cidx == word.length() - 1) {
            return true;
        }

        seen[curr.row][curr.col] = true;
        for (int[] dir : dirs) {
            Point p = new Point(curr.row + dir[0], curr.col + dir[1]);
            if (scan(board, word, cidx + 1, p, seen)) {
                return true;
            }
        }

        return false;
    }

    private Point findStart(String[] board, char c, Point start) {
        for (int col = start.col; col < board[0].length(); col++) {
            if (board[start.row].charAt(col) == c) {
                return new Point(start.row, col);
            }
        }

        for (int row = start.row + 1; row < board.length; row++) {
            for (int col = 0; col < board[0].length(); col++) {
                if (board[row].charAt(col) == c) {
                    return new Point(row, col);
                }
            }
        }
        return null;
    }

}
