package fb;

import java.util.HashMap;
import java.util.Map;
// Add any extra import statements you may need here

class PairSums {

  /*
    arr.length > 0 -> no need to check
    if arr.length == 1 -> no pairs -> return 0
    if arr.length == 2 -> one pair -> arr[0] + arr[1] == k -> 1

    BF:
    arr = [1, 2, 3, 4, 3]
    k = 6
    [1, 2, 3, 4, 3]
     ^
    [1, 2, 3, 4, 3]
        ^
    int numberOfWays = 0;
    1. i = 1; j = 2 -> 1 + 2 != 6 -> j++;
    2. i = 1; j = 3 -> 1 + 3 != 6 -> j++;
    ...
    n. i = 2; j = 3 -> 2 + 3 != 6 -> j++;
    n. i = 2; j = 4 -> 2 + 4 == 6 -> numberOfWays++; j++;

    O(n^2)

    ? O(n) || O(nlogn)

    int[] subst = [k - arr[0], ..., k - arr[i]]
    arr = [1, 2, 3, 4, 3]
    k = 6
    subst = [5, 4, 3, 2, 3] //O(n)
    map = {5: 1, 4: 1, 3: 2, 2: 1} -> 2 //O(n)

    for [1, 2, 3, 4, 3] //O(n)
    6 - 1 == 5 -> map.get(5) > 0 && map.get(1) > 0 -> false -> nothing //O(1)
    6 - 2 == 4 -> map.get(4) > 0 && map.get(2) > 0 -> true -> numberOfWays++ -> //numberOfWays == 1
    map.get(2)-- -> map.get(2) == 0
    map.get(4)-- -> map.get(4) == 0
    6 - 3 == 3 -> map.get(3) > 0 && map.get(3) > 0 -> true -> numberOfWays++ -> //numberOfWays == 2
    map.get(3)-- -> map.get(3) == 1
    map.get(3)-- -> map.get(3) == 0
    6 - 2 == 4 -> map.get(2) > 0 && map.get(4) > 0 -> false -> nothing
    6 - 3 == 3 -> map.get(3) > 0 && map.get(3) > 0 -> true -> numberOfWays++ -> map.get(3)-- -> map.get(3) == 0 //numberOfWays == 3 WRONG!! -> fixed


    arr = [1, 5, 3, 3, 3]
    k = 6
    subst = [5, 1, 3, 3, 3] //O(n)
    map = {5: 1, 1: 1, 3: 3} -> 4

    for [1, 5, 3, 3, 3]
    6 - 1 == 5 -> map.get(5) > 0 && map.get(1) > 0 -> true -> numberOfWays++ -> //numberOfWays == 1
    map.get(1)-- //0
    map.get(5)-- //0
    6 - 5 //counted == 1 -> map.get(1) > 0 && map.get(5) > 0 -> false -> nothing
    6 - 3 == 3 -> map.get(3) > 0 && map.get(3) > 0 -> true -> numberOfWays++ -> //numberOfWays == 2
    map.get(3)-- //2
    map.get(3)-- //1
    6 - 3 == 3 -> map.get(3) > 0 && map.get(3) > 0 -> true -> numberOfWays++ -> //numberOfWays == 3
    map.get(3)-- //0
    map.get(3)-- //-1 -> 1
    6 - 3 == 3 -> map.get(3) > 0 && map.get(3) > 0 -> true -> numberOfWays++ -> map.get(3)-- -> map.get(3) == 0 //numberOfWays == 4 WRONG!

  */

    // Add any helper functions you may need here

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    public static void main(String[] args) {
        new PairSums().run();
    }

    int numberOfWays(int[] arr, int k) {
        // Write your code here
        if (arr.length == 1) {
            return 0;
        }
        if (arr.length == 2) {
            return (arr[0] + arr[1] == k) ? 1 : 0;
        }

        int numberOfWays = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) { //O(n)
            map.merge(k - arr[i], 1, Integer::sum); //O(1)
        }
        for (int i = 0; i < arr.length; i++) { //O(n)
            int diff = k - arr[i];
            if (map.containsKey(diff) && map.get(diff) > 0 && map.containsKey(arr[i]) && map.get(arr[i]) > 0) {
                numberOfWays++;
                int currI = map.get(arr[i]);
                map.put(arr[i], Math.abs(currI - 1));
                int currD = map.get(diff);
                map.put(arr[i], Math.abs(currD - 1));
            }
        }
        return numberOfWays;
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
        int k_1 = 6;
        int[] arr_1 = { 1, 2, 3, 4, 3 };
        int expected_1 = 2;
        int output_1 = numberOfWays(arr_1, k_1);
        check(expected_1, output_1);

        int k_2 = 6;
        int[] arr_2 = { 1, 5, 3, 3, 3 };
        int expected_2 = 4;
        int output_2 = numberOfWays(arr_2, k_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}
