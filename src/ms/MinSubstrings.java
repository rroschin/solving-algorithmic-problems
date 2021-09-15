package ms;

import java.util.HashSet;
import java.util.Set;

class MinSubstrings {

    public static int solution(String S) {
        int minSubs = 1;
        final char[] chars = S.toCharArray();

        Set<Character> set = new HashSet<>();
        for (final char c : chars) {
            if (set.contains(c)) {
                minSubs++;
                set.clear();
                set.add(c);
            } else {
                set.add(c);
            }
        }

        return minSubs;
    }

    public static void main(String[] args) {
        System.out.println(solution("abacdec")); //3
        System.out.println(solution("worlddd")); //3
        System.out.println(solution("world")); //1
        System.out.println(solution("dddd")); //4
        System.out.println(solution("cycle")); //2
        System.out.println(solution("abba")); //2
    }
}
