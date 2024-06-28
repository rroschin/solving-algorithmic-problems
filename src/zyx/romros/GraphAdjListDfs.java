package zyx.romros;

import java.util.LinkedList;
import java.util.List;

public class GraphAdjListDfs {

    public static void main(String[] args) {
        List<Connections> graph = List.of(
                new Connections(1, 10),
                new Connections(List.of(2, 4), List.of(10, 10)),
                new Connections(3, 10),
                new Connections(List.of(3, 5), List.of(10, 10)),
                new Connections(3, 10),
                new Connections(-1, 0)
        );
        int target = 5;
        System.out.println(find(graph, target));
    }

    private static List<Integer> find(List<Connections> graph, int target) {
        LinkedList<Integer> path = new LinkedList<>();
        List<Boolean> seen = new LinkedList<>();
        for (int i = 0; i < graph.size(); i++) {
            path.add(-1);
            seen.add(false);
        }

        int curr = 0;
        walk(graph, curr, target, seen, path);

        LinkedList<Integer> properPath = new LinkedList<>();
        int i = path.size() - 1;
        while (i > -1) {
            if (path.get(i) != -1) {
                properPath.add(path.get(i));
            }
            i--;
        }

        return properPath;
    }

    private static boolean walk(List<Connections> graph, int curr, int target, List<Boolean> seen, LinkedList<Integer> path) {
        if (curr == target) {
            path.push(curr);
            return true;
        }
        if (seen.get(curr)) {
            return false;
        }

        seen.set(curr, true);
        path.push(curr);

        Connections connections = graph.get(curr);
        for (int i = 0; i < connections.to.size(); i++) {
            boolean walked = walk(graph, connections.to.get(i), target, seen, path);
            if (walked) {
                return true;
            }
        }

        path.pop();
        return false;
    }


    private static class Connections {

        List<Integer> to;

        List<Integer> weight;

        public Connections(List<Integer> to, List<Integer> weight) {
            this.to = to;
            this.weight = weight;
        }

        public Connections(Integer to, Integer weight) {
            this.to = List.of(to);
            this.weight = List.of(weight);
        }
    }
}
