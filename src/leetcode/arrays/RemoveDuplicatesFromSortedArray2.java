package leetcode.arrays;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray2 {

    public static void main(String[] args) {
        tc_1(new int[]{1, 1, 1, 2, 2, 3}, new int[]{1, 1, 2, 2, 3});
        tc_1(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 3, 3});
        tc_1(new int[]{-3, -1, -1, 0, 0, 0, 0, 0, 2}, new int[]{-3, -1, -1, 0, 0, 2});
        tc_1(new int[]{0, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4}, new int[]{0, 1, 2, 2, 3, 4, 4});
        tc_1(new int[]{1, 1, 1}, new int[]{1, 1});
    }

    private static void tc_1(int[] nums, int[] expectedNums) {
        RemoveDuplicatesFromSortedArray2 obj = new RemoveDuplicatesFromSortedArray2();

        int k = obj.removeDuplicates(nums);
        System.out.println(k);
        System.out.println(Arrays.toString(nums));

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }


    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        int left = 0;
        int right = 0;

        while (right < nums.length) {
            int seq = 1;
            while ((right + 1) < nums.length && nums[right] == nums[right + 1]) {
                right++;
                seq++;
            }
            for (int i = 0; i < Math.min(seq, 2); i++) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left;
    }

    public int removeDuplicates3(int[] nums) {

        int index = 1;
        int occurance = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                occurance++;
            } else {
                occurance = 1;
            }

            if (occurance <= 2) {
                nums[index] = nums[i];
                index++;
            }
        }

        return index;

    }

    public int removeDuplicates2(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        //[-4,1,1,1,1,2,5,5,5,5,6,7,7] -> [-4,1,1,2,5,5,13], k = 7
        //    ^ ^

        int k = nums.length;

        int lo = 0;
        int hi = 1;

        boolean isSeq = false;
        int seqStart = -1;
        int seqShouldEnd = -1;
        while (hi < nums.length) {
            if (nums[lo] > nums[hi]) {
                break;
            } else if (nums[lo] == nums[hi]) {
                if (!isSeq) {
                    isSeq = true;
                    seqStart = lo;
                } else if (hi - seqStart == 2) {
                    seqShouldEnd = hi;
                }
                hi++;
            } else {
                if (isSeq) {
                    if (seqShouldEnd - seqStart <= 1) {
                        //do nothing
                        lo = hi;
                        hi++;
                    } else {
                        replaceFrom(nums, seqShouldEnd, hi);
                        k -= hi - seqShouldEnd;
                        lo = seqShouldEnd;
                        hi = lo + 1;
                    }
                    isSeq = false;
                    seqStart = -1;
                    seqShouldEnd = -1;
                } else {
                    lo = hi;
                    hi++;
                }
            }
        }
        if (seqShouldEnd != -1) {
            for (int i = seqShouldEnd; i < nums.length; i++) {
                if (nums[i] == nums[seqShouldEnd - 1]) {
                    nums[i] = Integer.MIN_VALUE;
                    k--;
                }
                if ((i + 1) < nums.length && nums[i] > nums[i + 1]) {
                    break;
                }
            }
        }
        return k;
    }

    void replaceFrom(int[] nums, int replacee, int replacer) {
        while (replacer < nums.length) {
            nums[replacee] = nums[replacer];
            replacee++;
            replacer++;
        }
        while (replacee < nums.length) {
            nums[replacee] = Integer.MIN_VALUE;
            replacee++;
        }
    }
}
