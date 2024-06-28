package zyx.romros;

public class IsPalindromRecursively {

    public static void main(String[] args) {
        System.out.println(new IsPalindromRecursively().isPalindrom("racecar"));
        System.out.println(new IsPalindromRecursively().isPalindrom("racercar"));
    }

    private boolean isPalindrom(String str) {
        if (str.length() == 0 || str.length() == 1) {
            return true;
        }

        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            return isPalindrom(str.substring(1, str.length() - 1));
        }
        return false;
    }
}
