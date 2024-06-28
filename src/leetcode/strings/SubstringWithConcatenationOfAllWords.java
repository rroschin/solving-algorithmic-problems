package leetcode.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        System.out.println(new SubstringWithConcatenationOfAllWords().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"})); //0,9
    }
    //check simple edge cases: s.length < words.length * word[i].length
    //
    //build a map from words: {word[i]: occurencies}
    //
    //start iterating over char c in s
    //cut word[i].length substring, check if it is in the map
    // no -> i++
    // yes -> copy map, remove/decrement c, cut next word[i].length chars and check again
    //guard1 - start + words.length * word[i].length < s.length

    public List<Integer> findSubstring(String s, String[] words) {
        if (s.length() < words.length * words[0].length()) {
            return List.of();
        }
        if (words.length == 1 && words[0].equals(s)) {
            return List.of(0);
        }
        List<Integer> res = new ArrayList<>();

        Map<String, Integer> mainDict = buildMainDictionary(words);

        int wordLength = words[0].length();
        int permLength = words.length * words[0].length();

        int candidate = -1;
        for (int i = 0; i <= s.length() - permLength; i++) {
            String first = s.substring(i, i + wordLength); //+1?
            if (!mainDict.containsKey(first)) {
                continue;
            } else {
                Map<String, Integer> localDict = new HashMap<>(mainDict);
                decrementInMap(first, localDict);
                candidate = checkFromHere(s, i, wordLength, permLength - wordLength, localDict);
                if (candidate != -1) {
                    res.add(candidate);
                }
            }
        }

        return res;
    }

    Map<String, Integer> buildMainDictionary(String[] words) {
        Map<String, Integer> dict = new HashMap<>();
        for (String word : words) {
            dict.merge(word, 1, Integer::sum);
        }
        return dict;
    }

    int checkFromHere(String s, int pos, int wordLength, int maxLength, Map<String, Integer> localDict) {
        for (int i = pos + wordLength; i <= pos + maxLength; i += wordLength) {
            String next = s.substring(i, i + wordLength);
            if (!localDict.containsKey(next)) {
                return -1;
            } else {
                decrementInMap(next, localDict);
            }
        }
        return pos;
    }

    void decrementInMap(String first, Map<String, Integer> localDict) {
        Integer val = localDict.get(first);
        if (val > 1) {
            val--;
            localDict.put(first, val);
        } else {
            localDict.remove(first, val);
        }
    }
}
