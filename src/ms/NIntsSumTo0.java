package ms;

import java.util.Arrays;

class NIntsSumTo0 {


    public static int[] solution(int N) {
        int sum = 0;
        int[] nums = new int[N]; //1, 2, 3, -6
        for (int i = 0; i < N - 1; i++) {
            nums[i] = i;
            sum += i;
        }
        nums[N - 1] = -sum;
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(4)));
        System.out.println(Arrays.toString(solution(3)));
    }
}
