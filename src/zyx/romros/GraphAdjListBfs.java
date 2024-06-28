package zyx.romros;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphAdjListBfs {

    public static void main(String[] args) {
        List<Connections> graph = List.of(
                new Connections(0, 1, 10),
                new Connections(1, List.of(2, 4), List.of(10, 10)),
                new Connections(2, 3, 10),
                new Connections(3, List.of(3, 5), List.of(10, 10)),
                new Connections(4, 3, 10),
                new Connections(5, -1, 0)
        );
        int target = 5;
        System.out.println(find(graph, target));
    }

    private static List<Integer> find(List<Connections> graph, int target) {
        LinkedList<Integer> path = new LinkedList<>();
        List<Boolean> seen = new LinkedList<>();
        for (int i = 0; i < graph.size(); i++) {
            seen.add(false);
        }

        int curr = 0;
        System.out.println(walk(graph, curr, target, seen, path));
        return path;
    }

    private static boolean walk(List<Connections> graph, int curr, int target, List<Boolean> seen, LinkedList<Integer> path) {
        if (curr == target) {
            path.add(curr);
            return true;
        }

        Queue<Connections> queue = new LinkedList<>();
        queue.add(graph.get(curr));

        while (!queue.isEmpty()) {
            Connections connections = queue.poll();
            curr = connections.el;
            if (curr == target) {
                path.add(curr);
                return true;
            }
            if (seen.get(curr)) {
                continue;
            }
            seen.set(curr, true);
            path.add(curr);
            for (int i = 0; i < connections.to.size(); i++) {
                Integer idx = connections.to.get(i);
                if (idx > -1) {
                    queue.add(graph.get(idx));
                }
            }
        }

        return false;
    }


    private static class Connections {
        int el;
        List<Integer> to;
        List<Integer> weight;

        public Connections(int el, List<Integer> to, List<Integer> weight) {
            this.el = el;
            this.to = to;
            this.weight = weight;
        }

        public Connections(int el, Integer to, Integer weight) {
            this.el = el;
            this.to = List.of(to);
            this.weight = List.of(weight);
        }
    }
}
