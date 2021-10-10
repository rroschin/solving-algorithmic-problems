package fb;

import java.util.ArrayList;
import java.util.List;
// Add any extra import statements you may need here

class MatchingPairs {

    // Add any helper functions you may need here
  /*

  if s == t
  then max = s.length = 2

  s = mno -> mon
  t = mno -> mno
             1 (m=m)
  --
  s = mon -> mno | nom | omn
  t = mno -> mno | mno | mno
             3     0     0
  --
  s = abcdx -> bacdx | acbdx | abdcx | abcxd | cbadx | dbcax | xbcda | adcbx | axcdb | abxdc (10)
  t = adcbx -> adcbx | adcbx | adcbx | adcbx | adcbx | adcbx | adcbx | adcbx | adcbx | adcbx
               2(c,x)  2(a,x)  2(a,x)  2(a,x)  1(x)    2(c,x)  1(c)    5       2(a,c)  1(a)

  */

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new MatchingPairs().run();
    }

    int countPairs(char[] s, char[] t) {
        int pairs = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == t[i]) {
                pairs += 1;
            }
        }
        return pairs;
    }

    int matchingPairs(String s, String t) {
        // Write your code here
        if (s.equals(t)) {
            return s.length() - 2; //must swap
        }

        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();

    /* O(n^2)
    int max = 0;
    for (int i = 0; i < cs.length; i++) {
      for (int j = i + 1; j < cs.length; j++) {
        char tmp = cs[i];
        cs[i] = cs[j];
        cs[j] = tmp;
        max = Math.max(max, countPairs(cs, ct));
        tmp = cs[i];
        cs[i] = cs[j];
        cs[j] = tmp;
      }
    }
    */

    /*
    swap b(i=1) and d(i=3)
    s = [a b c d x]
    t = [a d c b x]
    a = [1 0 1 0 1 0 0 0 1 0 1 1]
    */
        int[] a = new int[cs.length];
        List<Integer> ai = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < cs.length; i++) { //O(n)
            if (cs[i] == ct[i]) {
                a[i] = 1;
                max += 1;
            } else {
                ai.add(i);
            }
        }

        for (int i = 0; i < ai.size(); i++) { //O(n)
            for (int j = i + 1; j < ai.size(); j++) { //O(n)
                int li = ai.get(i);
                int lj = ai.get(j);

                char tmp = cs[li];
                cs[li] = cs[lj];
                cs[lj] = tmp;
                max = Math.max(max, countPairs(cs, ct));
                tmp = cs[li];
                cs[li] = cs[lj];
                cs[lj] = tmp;
            }
        }

        return max; //O(n^2) -> //O(nlogn)
    }

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {
        String s_1 = "abcde";
        String t_1 = "adcbe";
        int expected_1 = 5;
        int output_1 = matchingPairs(s_1, t_1);
        check(expected_1, output_1);

        String s_2 = "abcd";
        String t_2 = "abcd";
        int expected_2 = 2;
        int output_2 = matchingPairs(s_2, t_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
