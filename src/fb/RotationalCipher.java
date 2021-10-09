package fb;

// Add any extra import statements you may need here

class RotationalCipher {

    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new RotationalCipher().run();
    }

    String rotationalCipher(String input, int rotationFactor) {
        // Write your code here

        char[] chars = input.toCharArray();
    /*
    '0' .. '9' -> 48 .. 57
    'A' .. 'Z' -> 65 .. 90
    'a' .. 'z' -> 97 .. 122

    numbers
    rf = 3: 0 -> 3, 1 -> 4, .... 6 -> 9, 7 -> 0, 8 -> 1, 9 -> 2
    rf = 10: 0 -> 0, 1 -> 1, .... 6 -> 6, 7 -> 7, 8 -> 8, 9 -> 9
    if (rf % 10 == 0) -> nothing
    else if (rf < 10) -> rotate
    else if (rf > 10) -> 13 - 10 = 3; 24 - 10 - 10 = 4; ... while (rf > 10) rf -= 10

    26 letters
    if (rf % 26 == 0) -> nothing
    else if (rf < 26) -> rotate
    else if (rf > 26) -> 27 - 26 = 1; 56 - 26 - 26 = 4; ... while (rf > 26) rf -= 26

    e.g. rf = 3
    x    y    z -> a   b   c
    120  121  122  97  98  99
    a + rf - ? -> ? = z - char[i] -> z - z = 0 -> (a + 3 - 1 - 0) = c
    a + rf - ? -> ? = z - char[i] -> z - x = 2 -> (a + 3 - 1 - 2) = a
    if ('z' is char && )
    if (char[i] is char && char[i])
    */

        int rfNum = rotationFactor;
        if (rfNum % 10 == 0) {
            //no rotation
            rfNum = 0;
        } else if (rfNum < 10) {
            //rotate
        } else if (rfNum > 10) {
            while (rfNum > 10) {
                rfNum -= 10;
            }
        }

        int rfChar = rotationFactor;
        if (rfChar % 26 == 0) {
            //no rotation
            rfChar = 0;
        } else if (rfChar < 26) {
            //rotate
        } else if (rfChar > 26) {
            while (rfChar > 26) {
                rfChar -= 26;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            final char cx;

            if (Character.isDigit(c)) {
                final int diff = '9' - c;
                if (diff < rfNum) { //wrap
                    cx = (char) ('0' + rfNum - 1 - diff);
                } else {
                    cx = (char) (c + rfNum);
                }
            } else if (Character.isLetter(c) && Character.isUpperCase(c)) {
                final int diff = 'Z' - c;
                if (diff < rfChar) { //wrap
                    cx = (char) ('A' + rfChar - 1 - diff);
                } else {
                    cx = (char) (c + rfChar);
                }
            } else if (Character.isLetter(c) && Character.isLowerCase(c)) {
                final int diff = 'z' - c;
                if (diff < rfChar) { //wrap
                    cx = (char) ('a' + rfChar - 1 - diff);
                } else {
                    cx = (char) (c + rfChar);
                }
            } else {
                cx = c;
            }
            sb.append(cx);
        }

        return sb.toString();
    }

    void check(String expected, String output) {
        boolean result = (expected.equals(output));
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printString(expected);
            System.out.print(" Your output: ");
            printString(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printString(String str) {
        System.out.print("[\"" + str + "\"]");
    }

    public void run() {
        String input_1 = "All-convoYs-9-be:Alert1.";
        int rotationFactor_1 = 4;
        String expected_1 = "Epp-gsrzsCw-3-fi:Epivx5.";
        String output_1 = rotationalCipher(input_1, rotationFactor_1);
        check(expected_1, output_1);

        String input_2 = "abcdZXYzxy-999.@";
        int rotationFactor_2 = 200;
        String expected_2 = "stuvRPQrpq-999.@";
        String output_2 = rotationalCipher(input_2, rotationFactor_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
