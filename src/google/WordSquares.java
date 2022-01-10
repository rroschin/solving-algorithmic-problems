package google;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

public class WordSquares {

    public static void main(String[] args) {
        System.out.println(new WordSquares().isWordSquare(new char[][] {
            { 'B', 'A', 'L', 'L' },
            { 'A', 'R', 'E', 'A' },
            { 'L', 'E', 'A', 'D' },
            { 'L', 'A', 'D', 'Y' }
        }));

        System.out.println(new WordSquares().isWordSquare(asList("BALL", "AREA", "LEAD", "LADY")));

        System.out.println(
            new WordSquares().possibleWordSquares(asList("AREA", "BALL", "DEAR", "LADY", "LEAD", "YARD")));
    }

    public List<List<String>> possibleWordSquares(List<String> candidates) {

        List<List<String>> result = new ArrayList<>();
        final List<List<String>> squareCandidates = buildSquareCandidates(candidates);
        for (List<String> squareCandidate : squareCandidates) {
            final List<List<String>> generatedPermutations = generatePermutations(squareCandidate);
            for (List<String> generatedPermutation : generatedPermutations) {
                final boolean wordSquare = isWordSquare(generatedPermutation);
                if (wordSquare) {
                    result.add(generatedPermutation);
                }
            }

        }
        return result;
    }

    public List<List<String>> generatePermutations(List<String> original) {
        if (original.isEmpty()) {
            List<List<String>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        String firstElement = original.remove(0);
        List<List<String>> returnValue = new ArrayList<>();
        List<List<String>> permutations = generatePermutations(original);
        for (List<String> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<String> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    private List<List<String>> buildSquareCandidates(final List<String> candidates) {
        List<List<String>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        for (String candidate : candidates) {
            List<List<String>> subres = new ArrayList<>();
            for (List<String> set : subsets) {
                final List<String> l = new ArrayList<>(set);
                l.add(candidate);
                subres.add(l);
            }
            subsets.addAll(subres);
        }

        int length = candidates.get(0).length();
        List<List<String>> squares = new ArrayList<>();
        for (List<String> subset : subsets) {
            if (subset.size() == length) {
                squares.add(subset);
            }
        }

        return squares;
    }

    public boolean isWordSquare(List<String> wordList) {
        char[][] words = new char[wordList.size()][wordList.size()];
        for (int i = 0; i < wordList.size(); i++) {
            char[] charArray = wordList.get(i).toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                words[i][j] = charArray[j];
            }
        }

        return isWordSquare(words);
    }

    public boolean isWordSquare(char[][] words) {
        if (words.length != words[0].length) {
            return false;
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                if (words[i][j] != words[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
