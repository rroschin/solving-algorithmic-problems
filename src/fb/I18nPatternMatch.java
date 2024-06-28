package fb;

public class I18nPatternMatch {

    public static void main(String[] args) {
        System.out.println(new I18nPatternMatch().isMatch("i18n", "internationalization")); //true
        System.out.println(new I18nPatternMatch().isMatch("8", "Facebook")); //true
        System.out.println(new I18nPatternMatch().isMatch("F2eb2k", "Facebook")); //true
        System.out.println(new I18nPatternMatch().isMatch("F1eb2k", "Facebook")); //false
        System.out.println(new I18nPatternMatch().isMatch("ABC", "abx")); //false
        System.out.println(new I18nPatternMatch().isMatch("AB1", "abc")); //false
        System.out.println(new I18nPatternMatch().isMatch("AB1", "ABC")); //true

        System.out.println(new I18nPatternMatch().isMatch("F2eb0ook", "Facebook")); //true
        System.out.println(new I18nPatternMatch().isMatch("F2eb2k", "FacebookXYZ")); //false
    }

    private boolean isMatch(String pattern, String input) {

        int i = 0;
        int j = 0;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == input.charAt(j)) {
                i++;
                j++;
            } else {
                if (!Character.isDigit(pattern.charAt(i))) {
                    return false;
                }

                String number = extractNumber(i, pattern);
                Integer numberInt = Integer.valueOf(number);

                try {
                    input.substring(j, j + numberInt);
                } catch (Exception e) {
                    return false;
                }

                i += number.length();
                j += numberInt;
            }
        }

        return j == input.length();
    }

    private String extractNumber(int i, String pattern) {
        StringBuilder sb = new StringBuilder();
        while (i < pattern.length() && Character.isDigit(pattern.charAt(i))) {
            sb.append(pattern.charAt(i));
            i++;
        }
        return sb.toString();
    }
}
