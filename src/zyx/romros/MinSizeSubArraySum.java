package zyx.romros;

class MinSizeSubArraySum {

    public static int findMinSubArray(int S, int[] arr) {
        int windowSum = 0, minLength = Integer.MAX_VALUE;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; // add the next element
            // shrink the window as small as possible until the 'windowSum' is smaller than 'S'
            while (windowSum >= S) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                windowSum -= arr[windowStart]; // subtract the element going out
                windowStart++; // slide the window ahead
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static int findMinSubArray1(int S, int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr[0] >= S) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        int sum = arr[0];
        int start = 0;
        int end = 0;
        while (start <= end && end < arr.length) {
            if (sum >= S) {
                min = Math.min(min, end - start + 1);
                sum -= arr[start];
                start++;
            } else {
                end++;
                if (end < arr.length) {
                    sum += arr[end];
                }
            }
        }

        return min;
    }

    public static void main(String[] args) {
        System.out.println(findMinSubArray(7, new int[] { 2, 1, 5, 2, 3, 2 }));
    }
}
