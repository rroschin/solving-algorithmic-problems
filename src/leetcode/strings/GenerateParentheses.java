package leetcode.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class GenerateParentheses {

    static String L = "(";
    static String R = ")";

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            result.add("");
        } else {
            for (int i = n - 1; i >= 0; i--) {
                List<String> insertSub = generateParenthesis(i);
                List<String> tailSub = generateParenthesis(n - 1 - i);
                for (String insert : insertSub) {
                    for (String tail : tailSub) {
                        result.add("(" + insert + ")" + tail);
                    }
                }

            }
        }
        return result;
    }

    public static List<String> generateParenthesisRec(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public static void backtrack(List<String> list, String str, int open, int close, int max) {

        if (str.length() == max * 2) {
            list.add(str);
            return;
        }

        if (open < max)
            backtrack(list, str + "(", open + 1, close, max);
        if (close < open)
            backtrack(list, str + ")", open, close + 1, max);
    }

    public static List<String> generateParenthesisNoLuck(int n) {
        if (n == 1) {
            return List.of("()");
        }

        Map<Integer, List<String>> levels = new HashMap<>();
        Set<String> res = generate(n, levels);

        return new ArrayList<>(res);
    }

    private static Set<String> generate(final int n, final Map<Integer, List<String>> levels) {
        if (n == 1) {
            levels.put(1, List.of(L + R));
            return Set.of(L + R);
        }
        final List<String> generated = new ArrayList<>(generate(n - 1, levels));
        levels.put(n - 1, generated);
        final List<String> updated = new ArrayList<>();

        for (final String g : generated) {
            //- add to each from left and right: ()()(), ()()(), (())(), ()(())
            updated.add(L + R + g);
            updated.add(g + L + R);
            //- wrap each: (()()), ((()))
            updated.add(L + g + R);
        }
        if (n % 2 == 0) {
            updated.add((L + L + R + R).repeat(Math.max(0, n / 2)));
        }
        if (n > 2) {
            final List<String> l2 = levels.get(2);
            final List<String> ln = levels.get(n - 2);
            for (final String lns : ln) {
                for (final String l2s : l2) {
                    updated.add(lns + l2s);
                    updated.add(l2s + lns);
                }
            }
        }

        return new HashSet<>(updated);
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(1));
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesis(4));
        System.out.println(generateParenthesis(5));
        System.out.println(generateParenthesis(5).size());
        System.out.println(generateParenthesis(8));
    }
}
