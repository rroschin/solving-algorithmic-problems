package fb;

public class StackStabilization {

    public static void main(String[] args) {
        System.out.println(new StackStabilization().getMinimumDeflatedDiscCount(5, new int[]{2, 5, 3, 6, 5})); //3
        System.out.println(new StackStabilization().getMinimumDeflatedDiscCount(3, new int[]{100, 100, 100})); //2
    }

    public int getMinimumDeflatedDiscCount(int N, int[] R) {

        int min = 0;

        int prev = R[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            int curr = R[i];

            if (curr >= prev) {
                curr = prev - 1; //try to deflate
                if (curr <= 0) {
                    return -1;
                }
                min++;
            }
            prev = curr;
        }

        return min;
    }
}
