package coursera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JustifyString {
    public static String textJustify(String input, int L) {
        //edge: null, "", 1 word.length < L
        if (input == null || input.isEmpty() || input.length() <= L) {
            return input;
        }
        StringBuilder result = new StringBuilder();

        String divider = " ";
        String[] words = input.split(divider);
        System.out.println(words.length);

        LinkedList<String> q = new LinkedList<>(Arrays.asList(words));
        List<String> line = new ArrayList<>();
        int lineSize = 0;
        while (!q.isEmpty()) {
            String word = q.poll();
            int newWordLength = word.length();
            if (lineSize + line.size() + newWordLength == L) {
                result.append(String.join(" ", line)).append(" ").append(word).append("\n");
                line.clear();
                lineSize = 0;
            } else if (lineSize + line.size() + newWordLength < L) { //add more
                line.add(word);
                lineSize += word.length();
            } else { //lineSize > L
                q.addFirst(word);

                int diff = L - lineSize;
                int mandatorySpaces = diff / (line.size() - 1);
                int extraSpaces = diff - (mandatorySpaces * (line.size() - 1)); // 0, 1, 2

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < line.size(); i++) {
                    if (i == line.size() - 1) {
                        sb.append(line.get(i));
                    } else {
                        sb.append(line.get(i)).append(" ".repeat(mandatorySpaces));
                        if (extraSpaces > 0) {
                            sb.append(" ");
                            extraSpaces--;
                        }
                    }
                }

                result.append(sb).append("\n");
                line.clear();
                lineSize = 0;
            }
        }
        result.append(String.join(" ", line));

        return result.toString();
    }

    public static void main(String args[]) {
        String input = "Coursera provides universal access to the world's best education, partnering with top universities and organizations to offer courses"
                       + " online.";
        int L = 30;

        System.out.println(textJustify(input, L));
    }
}
