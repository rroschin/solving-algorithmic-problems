package leetcode;

import java.util.Deque;
import java.util.LinkedList;

class TrappingRainWater {

    public static void main(String[] args) {
        System.out.println(new TrappingRainWater().trap(new int[] { 5, 4, 1, 2 })); //1
        System.out.println(new TrappingRainWater().trap(new int[] { 4, 2, 0, 3, 2, 5 })); //9
        System.out.println(new TrappingRainWater().trap(new int[] { 4, 2, 3 })); //1
        System.out.println(new TrappingRainWater().trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 })); //6
    }

    public int trap(int[] height) {
        int sum = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int prev = stack.pop();
                if (stack.isEmpty())
                    continue;
                int prev2 = stack.peek();
                int h = Math.min(height[i], height[prev2]) - height[prev];
                sum += h * (i - prev2 - 1);
            }
            stack.push(i);
        }
        return sum;
    }

    public int trap2(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int leftMax = 0;
        int rightMax = 0;
        int res = 0;

        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax)
                    leftMax = height[left];
                else
                    res += leftMax - height[left];
                left++;
            } else {
                if (height[right] >= rightMax)
                    rightMax = height[right];
                else
                    res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }

    public int trap1(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        int trapped = 0;

        int start = calculateStart(height);
        int end = start + 1;
        while (start < height.length && end < height.length) {
            end = searchNextHeight(start, height);
            if (end < 0 || end > height.length) {
                start = start + 1;
                continue;
            }
            trapped += calculateTrappedSection(start, end, height);
            start = end;
        }

        return trapped;

    }

    int calculateStart(int[] height) {
        int el = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] >= height[el]) {
                el = i;
            } else {
                return el;
            }
        }
        return el;
    }

    int searchNextHeight(int start, int[] height) {
        int min = height[start];
        int nextMax = -1;
        for (int i = start + 1; i < height.length; i++) {
            if (nextMax != -1 && height[nextMax] > height[i]) {
                return nextMax;
            }
            if (height[i] < min) {
                min = height[i];
            } else if (height[i] >= height[start]) {
                return i;
            } else {
                nextMax = i;
            }
        }
        return nextMax;
    }

    int calculateTrappedSection(int start, int end, int[] height) {
        int min = Math.min(height[start], height[end]);
        int sum = 0;
        for (int i = start + 1; i < end; i++) {
            if (min > height[i]) {
                sum += min - height[i];
            }
        }
        return sum;
    }
}
