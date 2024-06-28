package zyx.romros;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsInDict {

    Set<String> dict = new HashSet<>();

    public static void main(String[] args) {
        var obj = new IsInDict();
        obj.setup(List.of("cat", "bat","bar"));
        System.out.println(obj.dict);
        System.out.println(obj.isInDict("cat"));
        System.out.println(obj.isInDict("*at"));
        System.out.println(obj.isInDict("ca*"));
        System.out.println(obj.isInDict("bar"));
        System.out.println(obj.isInDict("b*r"));

        System.out.println(obj.isInDict("bir"));
        System.out.println(obj.isInDict("*ag"));
    }

    void setup(List<String> words) {
        for (String word : words) {
            dict.add(word);
            dict.addAll(generateWildcardWords(word));
        }
    }

    boolean isInDict(String word) {
        return dict.contains(word);
    }

    private Set<String> generateWildcardWords(String word) {
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //chars[0 .. i - 1] + * + chars[i + 1 .. N]
            sb.append(chars, 0, i).append("*").append(chars, i + 1, chars.length - (1 + i));
            set.add(sb.toString());
            sb.setLength(0);
        }

        return set;
    }
}
