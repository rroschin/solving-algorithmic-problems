package leetcode.strings;

import java.util.ArrayList;
import java.util.List;

class TextJustification {

    public static void main(String[] args) {
        System.out.println(new TextJustification().fullJustify(
                new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"},
                20
        ));
        System.out.println(List.of("Science  is  what we","understand      well","enough to explain to","a  computer.  Art is","everything  else  we","do                  "));

        System.out.println(new TextJustification().fullJustify(
                new String[]{"This", "is", "an", "example", "of", "text", "justification."},
                16
        ));
        System.out.println(List.of("This    is    an", "example  of text", "justification.  "));
    }
    public List<String> fullJustify(String[] words, int maxWidth) {
        //special condition(s), e.g. one word, one line, ...
        if (words.length == 1) {
            return List.of(formatLastLine(List.of(words[0]), maxWidth));
        }

        //main text body:
        //fill line by line for List<String>
        //loop through "words" saving them into "tmpString"
        //until sum(words[i].length) + words.length <= maxWidth
        //when ^ condition is met -> format the accumulated string, then move to next one

        List<String> res = new ArrayList<>();

        int currLineWordsLength = 0;
        int currLineWordsCount = 0;
        List<String> currLineWords = new ArrayList<>();
        for (String candidate : words) {
            if (currLineWordsLength + (currLineWordsCount) + candidate.length() <= maxWidth) {
                //can fit more
                currLineWordsCount++;
                currLineWordsLength += candidate.length();
            } else {
                //format line
                String line = formatLine(currLineWords, maxWidth);
                res.add(line);
                currLineWordsCount = 1;
                currLineWordsLength = candidate.length();
                currLineWords.clear();
            }
            currLineWords.add(candidate);
        }

        //special condition for last line (left + spaces)
        String line = formatLastLine(currLineWords, maxWidth);
        res.add(line);

        return res;
    }

    String formatLine(List<String> lineWords, int maxWidth) {
        if (lineWords.size() == 1) {
            return lineWords.get(0) + spaces(maxWidth - lineWords.get(0).length());
        }

        int minSpaces = lineWords.size() - 1;
        int wordsLength = String.join("", lineWords).length();
        int extraSpaces = maxWidth - (wordsLength + minSpaces);

        List<String> spaces = new ArrayList<>(lineWords.size() - 1);
        for (int i = 0; i < lineWords.size() - 1; i++) {
            spaces.add(" ");
        }
        int i = 0;
        while (extraSpaces > 0) {
            spaces.set(i, spaces.get(i) + " ");
            extraSpaces--;
            i++;
            if (i >= spaces.size()) {
                i = 0;
            }
        }

        //lineWords = [one, two, three]; spaces = [....., ....]
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < lineWords.size() - 1; j++) {
            sb.append(lineWords.get(j));
            sb.append(spaces.get(j));
        }
        sb.append(lineWords.get(lineWords.size() - 1));
        return sb.toString();
    }

    String formatLastLine(List<String> lineWords, int maxWidth) {
        String line = String.join(" ", lineWords);
        return line + spaces(maxWidth - line.length());
    }

    String spaces(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(" ");
            n--;
        }
        return sb.toString();
    }
}
