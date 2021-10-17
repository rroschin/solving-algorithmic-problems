package fb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
// Add any extra import statements you may need here

class CountingTriangles { //CountingTriangles

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    // Add any helper functions you may need here

    public static void main(String[] args) {
        new CountingTriangles().run();
    }

    int countDistinctTriangles(ArrayList<Sides> arr) {
        // Write your code here
        if (arr.size() == 0 || arr.size() == 1) {
            return arr.size();
        }

        Set<String> set = new HashSet<>();

        for (Sides sides : arr) {
            set.add(sides.asSortedString());
        }

        return set.size();
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
        ArrayList<Sides> arr_1 = new ArrayList<>();
        arr_1.add(new Sides(7, 6, 5));
        arr_1.add(new Sides(5, 7, 6));
        arr_1.add(new Sides(8, 2, 9));
        arr_1.add(new Sides(2, 3, 4));
        arr_1.add(new Sides(2, 4, 3));
        int expected_1 = 3;
        int output_1 = countDistinctTriangles(arr_1);
        check(expected_1, output_1);

        ArrayList<Sides> arr_2 = new ArrayList<>();
        arr_2.add(new Sides(3, 4, 5));
        arr_2.add(new Sides(8, 8, 9));
        arr_2.add(new Sides(7, 7, 7));
        int expected_2 = 3;
        int output_2 = countDistinctTriangles(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    class Sides {
        int a;
        int b;
        int c;

        Sides(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public String asSortedString() {

            int[] s = new int[] { a, b, c }; //3, 2, 1
            if (s[0] > s[1]) {
                int t = s[0];
                s[0] = s[1];
                s[1] = t;
            }
            //2, 3, 1
            if (s[1] > s[2]) {
                int t = s[1];
                s[1] = s[2];
                s[2] = t;
            }
            //2, 1, 3
            if (s[0] > s[1]) {
                int t = s[0];
                s[0] = s[1];
                s[1] = t;
            }
            //1, 2, 3

            return s[0] + "_" + s[1] + "_" + s[2];
        }

    }
}
