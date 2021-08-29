package zyx.romros;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordInMatrixSolution {

  public static void main(String[] args) {
    char[][] rows = {//
                     {'a', 'm', 'a', 'z', 'o', 'q'},//
                     {'m', 'a', 'k', 'o', 'f', 'o'},//
                     {'a', 'z', 'o', 'n', 'r', 'e'},//
                     {'g', 'r', 'g', 'j', 'o', 'a'},//
                     {'t', 'r', 'e', 'e', 'b', 'o'},//
                     {'h', 'e', 'l', 'l', 'o', 'w'}//
    };

    char[] targetWord = {'a', 'm', 'a', 'z', 'o', 'n'}; //amazon --> true

    List<String> found = asList(isTargetWordPresentInMatrix(rows, targetWord));
    System.out.println("Found: " + (found.contains(null) ? "FALSE" : "TRUE") +", " +found);
  }

  private static String[] isTargetWordPresentInMatrix(char[][] rows, char[] targetWord) {
    String[] res = new String[rows.length];
    int currLetterIdx = 0;
    char currLetter = getCurrentLetter(targetWord, currLetterIdx);

    Set<String> skipPositions = new HashSet<>();

    boolean found = false;
    int i = 0;
    int j = 0;
    while (!found) {
      while (j < rows[0].length) {
        if (rows[i][j] == currLetter) {
          final String positionStr = i + ":" + j;
          skipPositions.add(positionStr);
          res[currLetterIdx] = positionStr;
          currLetterIdx++;
          currLetter = getCurrentLetter(targetWord, currLetterIdx);
          String foundNearby = checkLettersNearby(currLetter, positionStr, rows, skipPositions);
          while (nonNull(foundNearby) && (currLetterIdx != targetWord.length - 1)) {
            skipPositions.add(positionStr);
            res[currLetterIdx] = foundNearby;
            currLetterIdx++;
            currLetter = getCurrentLetter(targetWord, currLetterIdx);
            foundNearby = checkLettersNearby(currLetter, foundNearby, rows, skipPositions);
          }
          res[currLetterIdx] = foundNearby;
          found = !asList(res).contains(null);
          if (found) {
            res[currLetterIdx] = foundNearby;
            break;
          }
          j++;
          res[currLetterIdx] = null;
          currLetterIdx--;
          currLetter = getCurrentLetter(targetWord, currLetterIdx);
          continue;
        }
        j++;
      }
      i++;
      if (i == rows.length && j == rows[0].length) {
        return null;
      }
    }

    return res;
  }

  private static String checkLettersNearby(char currLetter, String ij, char[][] rows, Set<String> skipPositions) { //0:2 -> 0:1, 0:3, 1:2
    final String[] split = ij.split(":");
    int i = Integer.parseInt(split[0]);
    int j = Integer.parseInt(split[1]);

    if (!skipPositions.contains(i + ":" + (j - 1)) && getAtIndexSafe(rows, i, j - 1) == currLetter) {
      return i + ":" + (j - 1);
    }
    if (!skipPositions.contains(i + ":" + (j + 1)) && getAtIndexSafe(rows, i, j + 1) == currLetter) {
      return i + ":" + (j + 1);
    }
    if (!skipPositions.contains((i - 1) + ":" + j) && getAtIndexSafe(rows, i - 1, j) == currLetter) {
      return (i - 1) + ":" + j;
    }
    if (!skipPositions.contains((i + 1) + ":" + j) && getAtIndexSafe(rows, i + 1, j) == currLetter) {
      return (i + 1) + ":" + j;
    }

    return null;
  }

  private static char getAtIndexSafe(char[][] rows, int i, int j) {
    try {return rows[i][j];} catch (Exception e) {
      return '0';
    }
  }

  private static char getCurrentLetter(char[] targetWord, int currLetterIdx) {
    return targetWord[currLetterIdx];
  }

}
