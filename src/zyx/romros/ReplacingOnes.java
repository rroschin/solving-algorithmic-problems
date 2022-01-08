package zyx.romros;

class ReplacingOnes {
    public static void main(String[] args) {
        System.out.println(new ReplacingOnes().findLength(new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 }, 2)); //9
        System.out.println(new ReplacingOnes().findLength(new int[] { 0, 1, 1, 0, 0, 1 }, 2));
    }

    public int findLength(int[] arr, int k) {
        int windowStart = 0;
        int maxLength = 0;
        int maxOnesCount = 0;
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            if (arr[windowEnd] == 1) {
                maxOnesCount++;
            }

            // current window size is from windowStart to windowEnd, overall we have a maximum of 1s
            // repeating a maximum of 'maxOnesCount' times, this means that we can have a window with
            // 'maxOnesCount' 1s and the remaining are 0s which should replace with 1s.
            // now, if the remaining 0s are more than 'k', it is the time to shrink the window as we
            // are not allowed to replace more than 'k' Os
            if (windowEnd - windowStart + 1 - maxOnesCount > k) {
                if (arr[windowStart] == 1) {
                    maxOnesCount--;
                }
                windowStart++;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public int findLength1(int[] arr, int k) {
        int max = -1;
        int oK = k;

        int start = 0;
        for (int end = 0; end < arr.length; end++) {
            int el = arr[end];

            if (el == 1) {
                max = Math.max(end - start + 1, max);
            } else {
                if (k > 0) {
                    k--;
                    max = Math.max(end - start + 1, max);
                } else {
                    if (arr[start] == 1) {
                        start++;
                        end--;
                    } else {
                        start++;
                        end--;
                        if (k < oK) {
                            k++;
                        }
                    }
                }
            }
        }

        return max;
    }
}
