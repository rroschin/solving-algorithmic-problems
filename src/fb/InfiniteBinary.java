package fb;

import java.util.LinkedList;
import java.util.Queue;

public class InfiniteBinary {

    public static void main(String[] args) {
        printBinary(); //0 1 00 01 10 11 000 001

    }

    private static void printBinary() {
        int k = 16;

        Queue<String> numbers = new LinkedList<>();
        numbers.add("0");
        numbers.add("1");
        while (k > 0) {
            String number = numbers.poll();
            System.out.println(number);
            numbers.add(number + "0");
            numbers.add(number + "1");
            k--;
        }
    }
}
