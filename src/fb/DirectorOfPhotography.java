package fb;

import java.util.ArrayList;
import java.util.List;

public class DirectorOfPhotography {
    public static void main(String[] args) {
        System.out.println(new DirectorOfPhotography().getArtisticPhotographCount(8, ".PBAAP.B", 1, 3)); //3
    }

    public int getArtisticPhotographCount(int N, String C, int X, int Y) {
        List<Integer> Ps = new ArrayList<>();
        List<Integer> As = new ArrayList<>();
        List<Integer> Bs = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            char c = C.charAt(i - 1);
            if (c == 'P') {
                Ps.add(i);
            } else if (c == 'A') {
                As.add(i);
            } else if (c == 'B') {
                Bs.add(i);
            }
        }

        if (Ps.isEmpty() || As.isEmpty() || Bs.isEmpty()) {
            return 0;
        }

        int counter = 0;
        for (int P : Ps) {
            for (int A : As) {

                int paDist = Math.abs(A - P);
                boolean isArtisticCand = (X <= paDist && paDist <= Y);
                if (!isArtisticCand) {
                    continue;
                }

                for (int B : Bs) {
                    boolean isPhoto = (P < A && A < B) || (P > A && A > B);
                    if (isPhoto) {
                        int abDist = Math.abs(B - A);
                        boolean isArtistic = X <= abDist && abDist <= Y;
                        if (isArtistic) {
                            counter++;
                        }
                    }
                }
            }
        }

        return counter;
    }
}
