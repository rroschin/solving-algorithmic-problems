package fb;

// Add any extra import statements you may need here

class EncryptedWords {

    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new EncryptedWords().run();
    }

    /*

    [a] -> [a]
    [a b] -> [a b]
    --
    [a b c] -> [b a c]

    R = [             ] // R.l == S.l
        [a b c x c b a] l = 7, odd -> mid = 7/2 = 3
        R.append(S[mid])
        [x            ]
        same(S[0..x],     R) //start = 0, end = mid - 1
        same(S[x+1..S.l], R) //start = mid + 1, end = S.l - 1
    ret R

        [a b c c b a x y z x] l = 10, even -> mid = 10/2 = 5-1 = 4
        mid = 4 -> b
        R = [b]
        same(0, 4-1=3)
          [a b c c]// l = 4 = 3-0+1
          mid = 4/2-1 = 1 -> b
          R = [b, b]
          same(0, 1-1=0)
            [a] //l=1 = 0-0+1
            mid = 0
            R = [b, b, a]
            same(0, 0-1=-1)
              return


        same(4+1=5, 9)
          [a x y z x] //l=5 = 9-5+1 = 5
          mid = 5/2 = 2 -> y
          R = [..., y]
            same()

    */
    String findEncryptedWord(String s) {
        // Write your code here
        int sl = s.length();
        if (sl == 0 || sl == 1 || sl == 2) {
            return s;
        }
        // sl > 2
        StringBuilder R = new StringBuilder();
        encode(s, R);

        return R.toString();
    }

    private void encode(String s, StringBuilder R) {
        if (s.isEmpty()) {
            return;
        }
        int l = s.length();
        int mid = (l % 2 == 0) ? /*even*/(l / 2 - 1) : /*odd*/ (l / 2);
        R.append(s.charAt(mid));
        encode(s.substring(0, mid), R); //mid - 1: exclusive
        encode(s.substring(mid + 1, l), R);
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
        String s_1 = "abc";
        String expected_1 = "bac";
        String output_1 = findEncryptedWord(s_1);
        check(expected_1, output_1);

        String s_2 = "abcd";
        String expected_2 = "bacd";
        String output_2 = findEncryptedWord(s_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
