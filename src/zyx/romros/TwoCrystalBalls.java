package zyx.romros;

public class TwoCrystalBalls {

    public static void main(String[] args) {
        System.out.println(new TwoCrystalBalls().dropTwoBalls(new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}));
        System.out.println(new TwoCrystalBalls().dropTwoBalls(new int[]{0, 1}));
        System.out.println(new TwoCrystalBalls().dropTwoBalls(new int[]{0, 1, 1}));
        System.out.println(new TwoCrystalBalls().dropTwoBalls(new int[]{0, 1, 1}));
        System.out.println(new TwoCrystalBalls().dropTwoBalls(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}));
        System.out.println(new TwoCrystalBalls().dropTwoBalls(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}));
    }

    int dropTwoBalls(int[] breaks) {

        int jump = (int) Math.sqrt(breaks.length);
        int curr = jump;
        while (curr < breaks.length) {
            if (breaks[curr] == 1) { //broke first ball
                for (int i = (curr - jump); i <= curr; i++) {
                    if (breaks[i] == 1) {
                        return i; //broke second ball
                    }
                }
            }
            curr += jump;
        }
        for (int i = (breaks.length - jump); i < breaks.length; i++) {
            if (breaks[i] == 1) {
                return i; //broke second ball
            }
        }

        return -1;
    }

}
