package zyx.romros;

import java.util.ArrayList;
import java.util.List;

class MergeIntervals {

    public static void main(String[] args) {
        System.out.println(new MergeIntervals().mergeIntervals(List.of(
                new Pair(1, 5),
                new Pair(3, 7),
                new Pair(4, 6),
                new Pair(6, 8),
                new Pair(10, 12),
                new Pair(10, 15)
        )));
    }

    List<Pair> mergeIntervals(List<Pair> v) {
        List<Pair> result = new ArrayList<>();

        Pair current = v.get(0);
        result.add(current);
        for (int i = 1; i < v.size(); i++) {
            Pair next = v.get(i);

            if (next.first > current.second) {
                current = next;
                result.add(current);
            } else {
                current.second = Math.max(current.second, next.second);
            }
        }

        return result;
    }

    static class Pair {
        public int first;
        public int second;

        public Pair(int x, int y) {
            this.first = x;
            this.second = y;
        }

        @Override
        public String toString() {
            return "[" + first + ", " + second + "]";
        }
    }

}
