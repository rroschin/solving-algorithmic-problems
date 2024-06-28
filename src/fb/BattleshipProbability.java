package fb;

public class BattleshipProbability {

    public static void main(String[] args) {
        System.out.println(new BattleshipProbability().getHitProbability(2, 3, new int[][]{
                new int[]{0, 0, 1},
                new int[]{1, 0, 1},
        }));
    }

    public double getHitProbability(int R, int C, int[][] G) {
        int totalOnes = 0;
        int totalZeros = 0;

        for (int r = 0; r < G.length; r++) {
            for (int c = 0; c < G[r].length; c++) {
                if (G[r][c] == 1) {
                    totalOnes++;
                } else if (G[r][c] == 0) {
                    totalZeros++;
                }
            }
        }

        return (double) totalOnes / (totalOnes + totalZeros);
    }
}
