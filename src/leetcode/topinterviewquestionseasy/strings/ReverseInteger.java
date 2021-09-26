package leetcode.topinterviewquestionseasy.strings;

class ReverseInteger {

    public static int reverseWithStr(int x) {
        String reversed = new StringBuilder().append(Math.abs(x)).reverse().toString();
        try {
            return (x < 0) ? Integer.parseInt(reversed) * -1 : Integer.parseInt(reversed);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int reverse(int x) {
        if (x < 10 && x > -10) {
            return x;
        }

        boolean negative = x < 0;

        char[] xStr = String.valueOf(x).toCharArray();
        int start = negative ? 1 : 0;
        int end = xStr.length - 1;
        while (start < end) {
            char tmp = xStr[start];
            xStr[start] = xStr[end];
            xStr[end] = tmp;
            start++;
            end--;
        }
        if (negative) {
            xStr[0] = '0';
        }
        try {
            int y = Integer.parseInt(new String(xStr));

            if (negative) {
                y *= -1;
            }
            return y;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
        System.out.println(reverse(0));
    }
}
