package leetcode;

import java.util.Arrays;
import java.util.Stack;

class NextGreaterElement {

    public static void main(String[] args) {

        final int[] nums = { 40, 4, 2, 5, 25, 50, 100 };
        for (int i = 0; i < nums.length * 2; i++) {
            final int idx = i % nums.length;
            System.out.println(i + " -> " + idx + ":" + nums[idx]);
        }

        System.out.println(Arrays.toString(new NextGreaterElement().nextGreaterElements(nums)));
        System.out.println(Arrays.toString(new NextGreaterElement().nextGreaterElements(new int[] { 1, 2, 3, 4, 5, 6 })));
        System.out.println(Arrays.toString(new NextGreaterElement().nextGreaterElements(new int[] { 6, 5, 4, 3, 2, 1 })));
        System.out.println(Arrays.toString(new NextGreaterElement().nextGreaterElements(new int[] { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 })));
    }

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int[] out = new int[nums.length];
        out[out.length - 1] = -1;
        out[out.length - 2] = nums[out.length - 1] > nums[out.length - 2] ? nums[out.length - 1] : -1;

        int[] max = new int[out.length];
        max[max.length - 1] = nums[out.length - 1];
        max[max.length - 2] = Math.max(nums[out.length - 1], nums[out.length - 2]);

        for (int i = nums.length - 2; i >= 0; i--) {
            out[i] = -1;
            int maxForThis = Math.min(nums[i + 1], max[i + 1]);
            if (maxForThis > nums[i]) {
                out[i] = maxForThis;
                max[i] = maxForThis;
            } else {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] > nums[i]) {
                        out[i] = nums[j];
                        max[i] = nums[j];
                        break;
                    }
                }
            }
        }
        /*
                         100  -1
        40, 4, 2, 5, 25, 50, 100
            5  5  25 50  100 100
        */

        return out;
    }

    public int[] nextGreaterElementsOn2(int[] nums) {

        int[] out = new int[nums.length];
        out[out.length - 1] = -1;

        for (int i = 0; i < out.length; i++) {

            for (int j = i + 1; j < out.length; j++) {
                if (nums[i] < nums[j]) {
                    out[i] = nums[j];
                    break;
                }
            }
        }
        out[out.length - 1] = -1;

        return out;
    }
}
