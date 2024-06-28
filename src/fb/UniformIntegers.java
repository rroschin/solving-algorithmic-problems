package fb;

public class UniformIntegers {

    public static void main(String[] args) {
        System.out.print(new UniformIntegers().solution1(1L, 9L) + " vs ");
        System.out.println(new UniformIntegers().getUniformIntegerCountInInterval(1L, 9L));
        System.out.print(new UniformIntegers().solution1(75L, 300L) + " vs ");
        System.out.println(new UniformIntegers().getUniformIntegerCountInInterval(75L, 300L));
        System.out.print(new UniformIntegers().solution1(1L, 500L) + " vs ");
        System.out.println(new UniformIntegers().getUniformIntegerCountInInterval(1L, 500L));
        System.out.print(new UniformIntegers().solution1(11L, 999999998L) + " vs ");
        System.out.println(new UniformIntegers().getUniformIntegerCountInInterval(11L, 999999998L));
    }

    public int solution1(long A, long B) {
        int counter = 0;
        for (long i = A; i <= B; i++) {
            if (isUniform(i)) {
                counter++;
            }
        }
        return counter;
    }

    public int getUniformIntegerCountInInterval(long A, long B) {
        if (A == B) {
            return 1;
        }
        if (B < 10) {
            return (int)(B - A) + 1;
        }
        int possA = calcDigits(A); // max 9
        int possB = calcDigits(B); // max 9

        int counter = 0;
        int it = possA;
        while (it <= possB) {
            counter += 9;
            it++;
        }

        int capA = (int) Math.pow(10, possA);
        int capB = (int) Math.pow(10, possB);
        int floorA = (int) Math.floor((1.0 / 9.0) * capA);
        int floorB = (int) Math.floor((1.0 / 9.0) * capB);
        int uniA = (int) Math.floor((double) A / floorA);
        if (A == 1L) {
            uniA--;
        }
        int uniB = (int) Math.floor((double) B / floorB);

        counter -= uniA;
        counter -= 9 - uniB;
        return counter;
    }

    int calcDigits(long num) {
        return Long.toString(num).length();
    }

    boolean isUniform(long num) {
        if (num < 10) {
            return true;
        }

        long expected = num % 10;
        num = num / 10;
        while (num > 0) {
            long actual = num % 10;
            if (expected != actual) {
                return false;
            }
            num = num / 10;
        }

        return true;
    }
}
