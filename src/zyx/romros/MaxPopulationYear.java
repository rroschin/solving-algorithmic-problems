package zyx.romros;

public class MaxPopulationYear {

    public static void main(String[] args) {

        System.out.println(new MaxPopulationYear().getMaxPopulationYear(new int[][] {
                { 1100, 1200 },
                { 1150, 1160 },
                { 2100, 2200 },
                { 1155, 1255 },
                { 1201, 1210 },
                { 1202, 1205 },
                { 1199, 1234 },
                { 1170, 1202 },
                { 1202, 1300 }
        }));
    }

    int getMaxPopulationYear(int[][] years) {
        if (years.length == 0) {
            return -1;
        }

        int minBirthYear = minBirthYear(years);
        int maxDeathYear = maxDeathYear(years);

        int[] population = new int[maxDeathYear - minBirthYear + 1];

        for (int i = 0; i < years.length; i++) {
            int birthYear = years[i][0];
            int deathYear = years[i][1];

            population[birthYear - minBirthYear] += 1;
            population[deathYear - minBirthYear] -= 1;
        }

        int max = 0;
        int maxi = -1;
        int sum = 0;

        for (int i = 0; i < population.length; i++) {
            sum += population[i];
            if (sum > max) {
                max = sum;
                maxi = i;
            }
        }

        return maxi + minBirthYear;
    }

    private int minBirthYear(final int[][] years) {
        int min = Integer.MAX_VALUE;
        for (final int[] year : years) {
            min = Math.min(min, year[0]);
        }
        return min;
    }

    private int maxDeathYear(final int[][] years) {
        int max = Integer.MIN_VALUE;
        for (final int[] year : years) {
            max = Math.max(max, year[1]);
        }
        return max;
    }
}
