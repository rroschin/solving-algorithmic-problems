package leetcode.strings;

class CountAndSay {

    public static String countAndSayWithForLoop(int n) {
        if (n == 1) {
            return "1";
        }
        String is = "1";
        int i = 1;

        while (n > i) {
            StringBuilder sb = new StringBuilder();
            final char[] isChars = is.toCharArray();
            char start = isChars[0];
            int counter = 1;
            for (int j = 1; j < isChars.length; j++) {
                if (isChars[j] == start) {
                    counter++;
                } else {
                    sb.append(counter).append(start);
                    counter = 1;
                    start = isChars[j];
                }
            }
            sb.append(counter).append(start);
            i++;
            is = sb.toString();
        }
        return is;
    }

    public static String countAndSayWithRecursion(int n) {
        if (n == 1) {
            return "1";
        }

        return cands(n, 1, "1");
    }

    public static String cands(int n, int i, String is) {
        if (n == i) {
            return is;
        }
        StringBuilder sb = new StringBuilder();
        final char[] isChars = is.toCharArray();
        char start = isChars[0];
        int counter = 1;
        for (int j = 1; j < isChars.length; j++) {
            if (isChars[j] == start) {
                counter++;
            } else {
                sb.append(counter).append(start);
                counter = 1;
                start = isChars[j];
            }
        }
        sb.append(counter).append(start);
        String newIs = sb.toString();
        return cands(n, i + 1, newIs);
    }

    public static void main(String[] args) {
        System.out.println(countAndSayWithForLoop(4));
        System.out.println(countAndSayWithForLoop(10));
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String rec = countAndSay(n - 1);
        int repeat = 1;
        char last = rec.charAt(0);
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < rec.length(); ++i) {
            if (rec.charAt(i) == last) {
                ++repeat;
                continue;
            }

            sb.append(repeat);
            sb.append(last);
            repeat = 1;
            last = rec.charAt(i);
        }

        sb.append(repeat);
        sb.append(last);

        return sb.toString();
    }
}
