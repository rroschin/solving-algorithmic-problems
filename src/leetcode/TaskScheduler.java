package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class TaskScheduler {

    public static void main(String[] args) {
        System.out.println(new TaskScheduler().leastInterval(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 2));
        System.out.println(new TaskScheduler().leastInterval(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 0));
    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : tasks) {
            freq.merge(c, 1, Integer::sum);
        }

        PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>(
            (a, b) -> {
                int res = Integer.compare(b.getValue(), a.getValue());
                if (res == 0) {
                    return Character.compare(a.getKey(), b.getKey());
                } else {
                    return res;
                }
            }
        );
        q.addAll(freq.entrySet());

        int count = 0;
        while (!q.isEmpty()) {
            int k = n + 1;
            List<Map.Entry<Character, Integer>> todo = new ArrayList<>();
            while (k > 0 && !q.isEmpty()) {
                Map.Entry<Character, Integer> task = q.poll();
                count++;
                task.setValue(task.getValue() - 1);
                if (task.getValue() > 0) {
                    todo.add(task);
                }
                k--;
            }
            q.addAll(todo);
            if (q.isEmpty()) {
                break;
            }
            count += k;
        }
        return count;
    }
}
