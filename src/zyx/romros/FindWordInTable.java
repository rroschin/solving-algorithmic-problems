package zyx.romros;

import java.util.Deque;
import java.util.LinkedList;

public class FindWordInTable {

    public static boolean isWordFoundInTable(final char[] word, final char[][] table) {
        //some edge cases like empty table, empty word, word is bigger than table, etc.

        int wordPointer = 0;
        Deque<String> visited = new LinkedList<>();

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {

                final char startingLetter = word[wordPointer];
                if (table[i][j] == startingLetter) { //found first letter, check for full word
                    visited.push(i + ":" + j);
                    wordPointer++;
                    boolean found = findWordStartingFromLetter(i, j, visited, table, wordPointer, word);
                    if (found) {
                        return true;
                    } else {
                        wordPointer--;
                        visited.clear();
                    }
                }
            }
        }

        return false;
    }

    private static boolean findWordStartingFromLetter(final int i,
                                                      final int j,
                                                      final Deque<String> visited,
                                                      final char[][] table,
                                                      final int wordPointer, final char[] word) {
        if (wordPointer == word.length) {
            return true;
        }
        final char nextLetter = word[wordPointer];

        char top = getAtIndexSafe(table, i - 1, j);
        if (top == nextLetter && !visited.contains((i - 1) + ":" + j)) { //found next letter!
            visited.push((i - 1) + ":" + j);
            final boolean found = findWordStartingFromLetter(i - 1, j, visited, table, wordPointer + 1, word);
            if (found) {
                return true;
            } else {
                visited.pop();
            }
        }
        char left = getAtIndexSafe(table, i, j - 1);
        if (left == nextLetter && !visited.contains(i + ":" + (j - 1))) { //found next letter!
            visited.push(i + ":" + (j - 1));
            final boolean found = findWordStartingFromLetter(i, j - 1, visited, table, wordPointer + 1, word);
            if (found) {
                return true;
            } else {
                visited.pop();
            }
        }
        char bottom = getAtIndexSafe(table, i + 1, j);
        if (bottom == nextLetter && !visited.contains((i + 1) + ":" + j)) { //found next letter!
            visited.push((i + 1) + ":" + j);
            final boolean found = findWordStartingFromLetter(i + 1, j, visited, table, wordPointer + 1, word);
            if (found) {
                return true;
            } else {
                visited.pop();
            }
        }
        char right = getAtIndexSafe(table, i, j + 1);
        if (right == nextLetter && !visited.contains(i + ":" + (j + 1))) { //found next letter!
            visited.push(i + ":" + (j + 1));
            final boolean found = findWordStartingFromLetter(i, j + 1, visited, table, wordPointer + 1, word);
            if (found) {
                return true;
            } else {
                visited.pop();
            }
        }

        return false;
    }

    private static char getAtIndexSafe(char[][] rows, int i, int j) {
        try {
            return rows[i][j];
        } catch (Exception e) {
            return '0';
        }
    }

    public static void main(String[] args) {
        char[][] table = {
                { 'f', 'm', 'a', 'z', 'o', 'q' },
                { 'm', 'a', 'k', 'o', 'n', 'o' },
                { 'a', 'x', 'o', 'n', 'r', 'e' },
                { 'g', 'r', 'g', 'j', 'o', 'a' },
                { 't', 'r', 'e', 'e', 'b', 'o' },
                { 'h', 'e', 'l', 'l', 'o', 'w' }
        };

        char[] word = { 'a', 'm', 'a', 'z', 'o', 'n' }; //amazon --> true

        System.out.println(isWordFoundInTable(word, table));
    }
}
