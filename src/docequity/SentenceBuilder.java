package docequity;

import java.util.ArrayList;
import java.util.List;

class SentenceBuilder {
    public static void main(String[] args) {
/*        var sentence = createSentence(List.of("this", " is", " a", " sentence"));
        System.out.println(sentence);

        int searchFrom = binarySearchFor(sentence, 6, 0, sentence.texts.size());
        System.out.println(searchFrom);

        var content = getRangeContent(sentence, 6, 12, searchFrom);
        System.out.println(content);*/

        //

        var sentence = createSentence(List.of("this", " is", " a", " longer", " sentence", " with", " more", " words"));
        System.out.println(sentence);

        var searchFrom = binarySearchFor(sentence, 23, 0, sentence.texts.size());
        System.out.println(searchFrom);

        var content = getRangeContent(sentence, 23, 35, searchFrom);
        System.out.println(content);

    }

    private static Sentence createSentence(List<String> input) {
        List<Text> texts = new ArrayList<>(input.size());

        int counter = 0;
        for (String in : input) {
            int startIndex = counter;
            int endIndex = startIndex + in.length();
            texts.add(new Text(startIndex, endIndex, in));
            counter = endIndex;
        }

        return new Sentence(texts);
    }

    private static int binarySearchForRec(Sentence sentence, int startIndex, int begin, int end) {
        int mid = begin + (end - begin) / 2;
        Text text = sentence.texts.get(mid);
        if (text.startIndex <= startIndex && startIndex < text.endIndex) {
            return mid;
        } else if (text.startIndex > startIndex) { //left side
            return binarySearchFor(sentence, startIndex, begin, mid - 1);
        } else if (text.endIndex <= startIndex) { //right side
            return binarySearchFor(sentence, startIndex, mid + 1, end);
        } else {
            return -1;
        }
    }

    private static int binarySearchFor(Sentence sentence, int startIndex, int begin, int end) {
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            Text text = sentence.texts.get(mid);

            if (text.startIndex <= startIndex && startIndex < text.endIndex) {
                return mid;
            } else if (text.startIndex > startIndex) { //left side
                end = mid - 1;
            } else { //right side
                begin = mid + 1;
            }
        }
        return -1;
    }

    private static String getRangeContent(Sentence sentence, int startIndex, int endIndex, int searchFrom) {
        //check if range outside of content
        String content = "";

        for (int i = searchFrom; i < sentence.texts.size(); i++) {
            Text text = sentence.texts.get(i);
            if (text.startIndex <= startIndex && startIndex < text.endIndex) {
                int from = startIndex - text.startIndex;
                int to;
                if (text.startIndex <= endIndex && endIndex < text.endIndex) {
                    to = endIndex - text.startIndex;
                } else {
                    to = text.endIndex - text.startIndex;
                }
                content += text.content.substring(from, to);

            } else if (text.startIndex < endIndex && endIndex <= text.endIndex) {
                int from = 0;
                int to = endIndex - text.startIndex;
                content += text.content.substring(from, to);
                return content;
            } else if (text.startIndex > startIndex && text.endIndex < endIndex) {
                content += text.content;
            }
        }

        return content;
    }

    static class Sentence {
        List<Text> texts;

        public Sentence(List<Text> texts) {
            this.texts = texts;
        }

        @Override
        public String toString() {
            return "Sentence{" +
                   "texts=" + texts +
                   '}';
        }
    }

    static class Text {
        int startIndex;
        int endIndex;
        String content;

        public Text(int startIndex, int endIndex, String content) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Text{" +
                   "startIndex=" + startIndex +
                   ", endIndex=" + endIndex +
                   ", content='" + content + '\'' +
                   '}';
        }
    }
}
