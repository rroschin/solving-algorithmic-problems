package fb;

// Big Integer Addition
// Given two strings representing integers, return the sum of two integers as string type. E.g. "123", "23" => "146"; "123", "-23" => "100".
public class AddTwoBigIntegers {

    public static void main(String[] args) {
        System.out.println(new AddTwoBigIntegers().customAdd(Integer.MAX_VALUE + "", Integer.MAX_VALUE + ""));
        long val = (long) Integer.MAX_VALUE + (long) Integer.MAX_VALUE;
        System.out.println(val);
    }

    public String customAdd(String s1, String s2) {
        StringBuilder res = new StringBuilder();

        char s1Sign = s1.charAt(0);
        char s2Sign = s2.charAt(0);

        int resultype; //0, 1, 2
        if (s1Sign == '-' && s2Sign == '-') {
            resultype = 1; //addition with negative result
        } else if (s1Sign == '-' || s2Sign == '-') {
            resultype = 2; //subtraction
        } else {
            resultype = 0; //addition
        }

        if (resultype == 0 || resultype == 1) {
            return addition(s1, s2, res, resultype);
        } else {
            return subtraction(s1, s2, res);
        }

    }

    private String addition(final String s1, final String s2, final StringBuilder res, final int resultype) {
        String s1Rev = reverse(s1); //321
        String s2Rev = reverse(s2); //32

        int length = Math.max(s1Rev.length(), s2Rev.length()); //3
        int start = resultype == 1 ? 1 : 0; //0

        boolean carry = false; //+10
        for (int i = start; i < length; i++) {

            char c1 = safeCharAt(s1Rev, i); //3 -> 2 -> 1
            char c2 = safeCharAt(s2Rev, i); //3 -> 2 -> 0

            int i1 = Integer.parseInt(String.valueOf(c1)); //3 -> 2 -> 1
            int i2 = Integer.parseInt(String.valueOf(c2)); //3 -> 2 -> 0

            int sum = i1 + i2; //6 -> 4 -> 1
            if (carry) {
                sum += 1;
            }
            //sum > 9 -> carry = true
            if (sum > 9) {
                res.append(sum - 10); //15 - 10 (carry) : 5
                carry = true;
            } else {
                res.append(sum); //res = 641
                carry = false;
            }
        }

        if (carry) {
            res.append("1");
        }

        final String value = reverse(res.toString());
        return resultype == 1 ? "-" + value : value;
    }

    private String subtraction(final String s1, final String s2, final StringBuilder res) {
        String bigger = getBigger(s1, s2);   //remove sign   -> 123
        String smaller = getSmaller(s1, s2); //remove sign ->  23
        //bigger - smaller

        String biggerRev = reverse(bigger); //321
        String smallerRev = reverse(smaller); //32

        int length = Math.max(biggerRev.length(), smallerRev.length()); //3
        boolean carry = false;

        for (int i = 0; i < length; i++) {

            char c1 = safeCharAt(biggerRev, i); //3 -> 2 -> 1
            char c2 = safeCharAt(smallerRev, i); //3 -> 2 -> 0

            int i1 = Integer.parseInt(String.valueOf(c1)); //3 -> 2 -> 1
            int i2 = Integer.parseInt(String.valueOf(c2)); //3 -> 2 -> 0

            int subs = i1 - i2; //

            if (carry) {
                subs -= 1;
            }

            if (subs < 0) { //
                res.append(10 + subs);
                carry = true;
            } else {
                res.append(subs); //001
                carry = false;
            }
        }
        //resultype == 2
        final String value = reverse(res.toString());
        return value;
    }

    private String getBigger(final String s1, final String s2) {
        return s1;
    }

    private String getSmaller(final String s1, final String s2) {
        return s2;
    }

    char safeCharAt(String s, int i) {
        if (i >= 0 && i < s.length()) {
            return s.charAt(i);
        } else {
            return '0';
        }
    }

    String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
