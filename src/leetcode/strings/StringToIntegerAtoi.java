package leetcode.strings;

class StringToIntegerAtoi {
    public static int myAtoi(String s) {
        int i = 0, sign = 1, d = 0, r = 0, res = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                i++;
            } else {
                break;
            }
        }
        if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                d = s.charAt(i) - '0';

                if (Integer.MAX_VALUE / 10 < res || ((Integer.MAX_VALUE / 10 == res) && (d > 7))) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                res = r * 10 + d;
                r = res;
                i++;
            } else {
                break;
            }
        }
        return sign * r;
    }

    public static int myAtoi2(String str) {

        final int len = str.length();

        if (len == 0)
            return 0;

        int index = 0;

        // skipping white spaces
        while (index < len && str.charAt(index) == ' ')
            ++index;

        boolean isNegative = false;

        // to handle sign cases
        if (index < len) {

            if (str.charAt(index) == '-') {
                isNegative = true;
                ++index;
            } else if (str.charAt(index) == '+')
                ++index;

        }

        int result = 0;

        // converting digit(in character form) to integer form
        // iterate until non-digit character is not found or we can say iterate till found character is a digit
        while (index < len && Character.isDigit(str.charAt(index))) {

      /* str.charAt(index) - '0' is to convert the char digit into int digit eg: '5' - '0' --> 5
      or else it will store the ASCII value of 5 i.e. 53,
      so we do 53(ASCII of 5) - 48(ASCII of 0(zero)) to get 5 as int*/
            int digit = str.charAt(index) - '0';

            // to avoid integer overflow
            if (result > (Integer.MAX_VALUE / 10) || (result == (Integer.MAX_VALUE / 10) && digit > 7))
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            // adding digits at their desired place-value
            result = (result * 10) + digit;

            ++index;
        }
        return isNegative ? -result : result;
    }

    public static int myAtoi1(String s) {
        final String s1 = s.strip();
        if (s1.length() == 0) {
            return 0;
        }

        final char[] chars = s1.toCharArray();
        if (chars.length == 1 && (chars[0] == '+' || chars[0] == '-')) {
            return 0;
        }

        boolean negative = chars[0] == '-';

        int start = (chars[0] == '+' || chars[0] == '-') ? 1 : 0;
        boolean numberSoFar = (start == 1 && Character.isDigit(chars[1])) || (start == 0 && Character.isDigit(chars[0]));
        if (!numberSoFar) {
            return 0;
        }

        StringBuilder number = new StringBuilder();
        for (int i = start; i < chars.length; i++) {
            numberSoFar = Character.isDigit(chars[i]);
            if (!numberSoFar) {
                break;
            }
            number.append(chars[i]);
        }
        try {

            final long num = Long.parseLong(number.toString());
            final long i = negative ? num * -1 : num;
            if (i > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (i < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            } else {
                return (int) i;
            }
        } catch (NumberFormatException e) {
            if (negative) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("20000000000000000000"));
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("   -42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
    }
}
