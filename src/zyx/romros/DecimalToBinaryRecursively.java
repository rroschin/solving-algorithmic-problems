package zyx.romros;

public class DecimalToBinaryRecursively {
    public static void main(String[] args) {
        System.out.println(new DecimalToBinaryRecursively().convert(233)); //11101001
    }

    private String convert(int num) {

        String res = div(num, "");
        return res;
    }

    private String div(int num, String res) {
        if (num <= 0) {
            return res;
        }

        int div = num / 2;
        int rem = num % 2;

        return div(div, rem + res);
    }
}
