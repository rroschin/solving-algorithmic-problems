package ms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ShortestBalancedFragmentOfString {
    public static int solution(String S) {
        final char[] chars = S.trim().toCharArray();
        Set<Character> set = new HashSet<>();
        for (final char c : chars) {
            set.add(c);
        }

        StringBuilder sb = new StringBuilder();
        List<String> fragments = new ArrayList<>();
        for (final char c : chars) {
            final char opposite = Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c);
            if (set.contains(opposite)) { //has a pair for this letter, candidate
                sb.append(c);
            } else { //has no pair
                if (sb.length() > 1) {
                    fragments.add(sb.toString());
                }
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 1) {
            fragments.add(sb.toString());
        }

        final String minStr = fragments.stream().min(Comparator.comparingInt(String::length)).orElse("");
        return minStr.isEmpty() ? -1 : minStr.length();
    }

    public static void main(String[] args) {
        System.out.println(solution("AcZCbaBz")); //8, AcZCbaBz
        System.out.println(solution("azABaabza")); //5, ABaab
        System.out.println(solution("TacoCat")); //-1
        System.out.println(solution("abcdefghijklmnopqrst")); //-1
    }
}
