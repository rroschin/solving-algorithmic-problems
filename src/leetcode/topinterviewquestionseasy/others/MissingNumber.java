package leetcode.topinterviewquestionseasy.others;

import java.util.HashSet;
import java.util.Set;

class MissingNumber {

    public static int missingNumber(int[] nums) {
        if (nums.length == 0) { //O(1)
            return 0; //O(1)
        }
        int n = nums.length; //[0..n] //O(1)
        Set<Integer> setAll = new HashSet<>(); //O(1)
        Set<Integer> setSome = new HashSet<>(); //O(1)
        for (int i = 0; i < n; i++) { //O(n)
            setAll.add(i);//O(1)
            setSome.add(nums[i]);//O(1)
        }
        setAll.add(n);//O(1)

        //set = 0,1,2,3, nums = 3,0,1
        for (Integer i : setAll) { //O(n)
            if (!setSome.contains(i)) {//O(1)
                return i;//O(1)
            }
        }
        return -1;//O(1)
    }

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[] { 3, 0, 1 }));
    }
}
