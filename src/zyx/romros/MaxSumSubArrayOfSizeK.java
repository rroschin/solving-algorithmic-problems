package zyx.romros;

class MaxSumSubArrayOfSizeK {
    public static int findMaxSumSubArray(int k, int[] arr) {
        if (arr.length < k) {
            return -1;
        }

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        int max = sum;

        int start = 0;
        for (int end = k; end < arr.length; end++) {
            sum -= arr[start];
            sum += arr[end];
            start++;
            max = Math.max(max, sum);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(findMaxSumSubArray(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 }));
    }
}
