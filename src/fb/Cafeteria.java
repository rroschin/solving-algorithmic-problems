package fb;

import java.util.Arrays;

public class Cafeteria {

    public static void main(String[] args) {
        System.out.println(new Cafeteria().getMaxAdditionalDinersCount(10, 1, 2, new int[]{2, 6}));
        System.out.println(new Cafeteria().getMaxAdditionalDinersCount(15, 2, 3, new int[]{11, 6, 14}));
    }

    public int getMaxAdditionalDinersCount(int N, int K, int M, int[] S) {
        int[] seats = new int[N + 1 + K + 1];
        for (int s : S) {
            seats[s] = 1;
        }
        seats[N + K + 1] = 1;

        int counter = 0;
        int prev = -K;
        for (int i = 1; i <= N + K + 1; i++) {
            if (seats[i] == 1) {
                int curr = i;
                while (curr > prev) {
                    int cand = (curr - K - 1);
                    if (cand > prev + K) {
                        seats[cand] = 1;
                        counter++;
                        curr = cand;
                    } else {
                        break;
                    }
                }
                prev = i;
            }
        }
        return counter;
    }

    public int getMaxAdditionalDinersCount2(int N, int K, int M, int[] S) {

        Arrays.sort(S);

        int guests = 0;
        int start = 1;
        int range;

        for (int seatedDiner : S) {
            range = seatedDiner - start;
            guests += range / (K + 1);
            start = seatedDiner + K + 1;
        }

        range = N - start + 1;
        guests += (int) Math.ceil((double) range / (K + 1));

        return guests;
    }
}
