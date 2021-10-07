package leetcode.topinterviewquestionseasy.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        if (n == 0) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.merge(1, 13, Integer::sum);

        List<String> answer = new ArrayList<>(n + 1);
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                answer.add("FizzBuzz");
            } else if (i % 3 == 0) {
                answer.add("Fizz");
            } else if (i % 5 == 0) {
                answer.add("Buzz");
            } else {
                answer.add("" + i);
            }
        }
        return answer;
    }
}
