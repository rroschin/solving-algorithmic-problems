package fb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ColorfulNumbers {

    public static void main(String[] args) {
        int n1 = 2345;
        System.out.println(n1 + " is " + new ColorfulNumbers().isColorful(n1));

        int n2 = 326;
        System.out.println(n2 + " is " + new ColorfulNumbers().isColorful(n2));

        int n3 = 221;
        System.out.println(n3 + " is " + new ColorfulNumbers().isColorful(n3));
    }

    private boolean isColorful(int n) {
        String nStr = Integer.toString(n);
        if (nStr.length() == 1) {
            return true;
        }
        Set<Character> set = new HashSet<>();
        for (Character c : nStr.toCharArray()) {
            set.add(c);
        }
        if (set.size() != nStr.length()) {
            return false;
        }

        Set<Integer> products = new HashSet<>();
        for (int i = 0; i < nStr.length(); i++) {
            int product = 1;
            for (int j = i; j < nStr.length(); j++) {
                product *= Integer.parseInt(nStr.charAt(j) + "");
                if (products.contains(product)) {
                    return false;
                }
                products.add(product);
            }
        }
        return true;
    }
}
