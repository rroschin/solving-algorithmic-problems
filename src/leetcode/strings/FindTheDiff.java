package leetcode.strings;

public class FindTheDiff {

    public static void main(String[] args) {
        System.out.println(new FindTheDiff().findTheDifference("abc", "abcd"));
        System.out.println(new FindTheDiff().findTheDifference("abc", "adbc"));
    }

    public char findTheDifference(String s, String t) {
        int i = 0, result = 0;
        while (i < s.length()) {
            char cT = t.charAt(i);
            char cS = s.charAt(i);
            int cXor = cT ^ cS;
            result ^= cXor;
            i++;
        }
        if (i < t.length())
            result ^= t.charAt(i);
        return (char) result;
    }
}
