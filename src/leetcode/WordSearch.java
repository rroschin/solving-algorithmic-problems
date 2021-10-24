package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

class WordSearch {

    public static void main(String[] args) {
        System.out.println(new WordSearch().exist(new char[][] {
                                                          { 'A', 'B', 'C', 'E' },
                                                          { 'S', 'F', 'C', 'S' },
                                                          { 'A', 'D', 'E', 'E' }
                                                  },
                                                  "ABCB"));

        System.out.println(new WordSearch().exist(new char[][] {
                                                          { 'H', 'E', 'L', 'U' },
                                                          { 'H', 'O', 'L', 'E' },
                                                          { 'O', 'W', 'A', 'R' }
                                                  },
                                                  "HELLOHOWAREU"));

        System.out.println(new WordSearch().exist(new char[][] {
                                                          { 'A', 'B', 'C', 'E' },
                                                          { 'S', 'F', 'E', 'S' },
                                                          { 'A', 'D', 'E', 'E' }
                                                  },
                                                  "ABCEFSADEESE"));

        System.out.println(new WordSearch().exist(new char[][] {
                                                          { 'A', 'B', 'C', 'E' },
                                                          { 'S', 'F', 'C', 'S' },
                                                          { 'A', 'D', 'E', 'E' }
                                                  },
                                                  "ABCCED"));
    }

    public List<String> findWords(final char[][] board, String[] words) {

        @SuppressWarnings("unchecked")
        final CompletableFuture<String>[] futures = Arrays.stream(words)
                .map(word -> CompletableFuture.supplyAsync(() -> {
                    final boolean exist = this.exist(board, word);
                    return exist ? word : null;
                }))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        return Arrays.stream(futures)
                .map(f -> {
                    try {
                        return f.get();
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(toList());
    }

    public boolean exist(char[][] board, String word) {
        char firstLetter = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == firstLetter) {
                    //give it a try
                    int[][] visited = new int[board.length][board[0].length]; //0s
                    if (scanForWord(board, word, "", 0, i, j, visited)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean scanForWord(char[][] board, String word, String soFar, int charToFindIndex, int i, int j, int[][] visited) {
        if (soFar.equals(word)) {
            return true;
        }
        if (i < 0 || j < 0 || i == board.length || j == board[0].length) {
            return false;
        }
        if (charToFindIndex == word.length()) {
            return false;
        }
        if (board[i][j] != word.charAt(charToFindIndex)) {
            return false;
        }
        if (visited[i][j] == 1) {
            return false;
        }
        visited[i][j] = 1;

        //check: up, right, down, left
        boolean up = scanForWord(board, word, soFar + word.charAt(charToFindIndex), charToFindIndex + 1, i - 1, j, visited);
        if (up) {
            return true;
        }
        boolean right = scanForWord(board, word, soFar + word.charAt(charToFindIndex), charToFindIndex + 1, i, j + 1, visited);
        if (right) {
            return true;
        }
        boolean down = scanForWord(board, word, soFar + word.charAt(charToFindIndex), charToFindIndex + 1, i + 1, j, visited);
        if (down) {
            return true;
        }
        boolean left = scanForWord(board, word, soFar + word.charAt(charToFindIndex), charToFindIndex + 1, i, j - 1, visited);
        if (left) {
            return true;
        }

        visited[i][j] = 0;
        return false;
    }
}
