package google;

import static java.util.Arrays.asList;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FlattenIterators {

    public static void main(String[] args) {
        List<Integer> arr1 = asList(1, 2, 3);
        List<Integer> arr2 = asList(4, 5);
        List<Integer> arr3 = asList(6, 7, 8, 9);
        Iterator<Integer> a = arr1.iterator();
        Iterator<Integer> b = arr2.iterator();
        Iterator<Integer> c = arr3.iterator();

        Iterator<Integer>[] iterlist = new Iterator[] { a, b, c };

        IF itfl = new IF(iterlist);
        while (itfl.hasNext()) {
            System.out.println(itfl.next());
        }
        // 1 4 6 2 5 7 3 8 9
    }

    static class IF {
        Iterator<Integer>[] iterlist;
        PriorityQueue<Map.Entry<Integer, Integer>> q;

        public IF(Iterator<Integer>[] iterlist) {
            this.iterlist = iterlist;
            this.q = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getKey));

            for (int j = 0; j < iterlist.length; j++) {
                Iterator<Integer> it = iterlist[j];
                if (it.hasNext()) {
                    this.q.offer(new AbstractMap.SimpleEntry<>(it.next(), j));
                }
            }
        }

        public Integer next() {
            final Map.Entry<Integer, Integer> it = this.q.poll();
            if (iterlist[it.getValue()].hasNext()) {
                this.q.offer(new AbstractMap.SimpleEntry<>(iterlist[it.getValue()].next(), it.getValue()));
            }

            return it.getKey();
        }

        public boolean hasNext() {
            return !q.isEmpty();
        }
    }

}
