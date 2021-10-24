package zyx.romros;

class ReverseWords {

    public static void main(String[] args) {
        final char[] sentence = "Hello World, how are you today?".toCharArray();
        new ReverseWords().reverseWords(sentence);
        System.out.println(sentence);
    }

    public void reverseWords(char[] sentence) {
        for (int i = 0; i < sentence.length / 2; i++) {
            char tmp = sentence[i];
            sentence[i] = sentence[sentence.length - 1 - i];
            sentence[sentence.length - 1 - i] = tmp;
        }

        int k1 = 0;
        int k2 = 0;
        for (int i = 0; i < sentence.length; i++) {
            if (sentence[i] == ' ') {
                int wordLength = k2 - k1;
                int j = k1;
                int c = 0;
                while  (c < wordLength / 2) {
                    char tmp = sentence[j];
                    sentence[j] = sentence[k2 - 1 - c];
                    sentence[k2 - 1 - c] = tmp;
                    j++;
                    c++;
                }
                k1 = k2 + 1;
                k2 = k1;
                continue;
            }
            k2++;
        }
        int wordLength = k2 - k1;
        int j = k1;
        int c = 0;
        while  (c < wordLength / 2) {
            char tmp = sentence[j];
            sentence[j] = sentence[k2 - 1 - c];
            sentence[k2 - 1 - c] = tmp;
            j++;
            c++;
        }
    }

    public void reverseWords2(char[] sentence) {
        int r = sentence.length - 1;
        char[] reversed = new char[sentence.length];

        char[] word = new char[sentence.length];
        int k = 0;

        for (int i = 0; i < sentence.length; i++) {
            if (sentence[i] != ' ') {
                word[k] = sentence[i];
                k++;
            } else {
                for (int j = k - 1; j >= 0; j--) {
                    reversed[r] = word[j];
                    r--;
                }
                reversed[r] = ' ';
                r--;
                k = 0;
            }
        }
        for (int j = k - 1; j >= 0; j--) {
            reversed[r] = word[j];
            r--;
        }

        for (int i = 0; i < sentence.length; i++) {
            sentence[i] = reversed[i];
        }
    }
}
