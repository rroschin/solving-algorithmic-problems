package fb;

public class DesinerPDFViewer {

    public static void main(String[] args) {
        int[] h1 = {1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        String word1 = "abc aac";
        System.out.println(new DesinerPDFViewer().designerPdfViewer(h1, word1)); //9 + 3 = 12

        int[] h2 = {1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7};
        String word2 = "zaba";
        System.out.println(new DesinerPDFViewer().designerPdfViewer(h2, word2)); //28
    }

    private int designerPdfViewer(int[] h, String word) {
        if (word.isBlank()) {
            return 0;
        }

        int total = 0;

        int count = 0;
        int maxHeight = 1;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == ' ') {
                total += count * maxHeight;
                count = 0;
                maxHeight = 1;
            } else {
                count++;
                int pos = c - 'a';
                maxHeight = Math.max(maxHeight, h[pos]);
            }
        }

        total += count * maxHeight;
        return total;
    }
}
