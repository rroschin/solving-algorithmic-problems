package leetcode.kth;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class OptimalPartitionOfString {

    public static void main(String[] args) {
        System.out.println(new OptimalPartitionOfString().partitionString("abacaba")); //4
        System.out.println(new OptimalPartitionOfString().partitionString("ababab")); //3
        System.out.println(new OptimalPartitionOfString().partitionString("abbbaa")); //5
    }

    public int partitionString(String s) {
        int counter = 0;
        int[] substring = new int[26];
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (substring[index] != 0) {
                counter++;
                Arrays.fill(substring, 0);
            }
            substring[index] = 1;
        }

        return counter + 1;
    }

    public int partitionStringWithSet(String s) {
        //ababab -> [ab, ab, ab]; min == max(a) == 3
        //abbbaa -> [ab, b, ba, a]; min != max(a) == 4

        //edge cases s == "", etc.

        int counter = 0;
        Set<Character> substring = new HashSet<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (!substring.contains(arr[i])) { //1:[], 2:[a], 3:[ab]
                substring.add(arr[i]); //1: [a], 2: [ab]
            } else {
                counter++;
                substring.clear();
                substring.add(arr[i]);
            }
        }

        return !substring.isEmpty() ? counter + 1 : counter;
    }
}
