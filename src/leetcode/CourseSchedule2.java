package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class CourseSchedule2 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CourseSchedule2().findOrder(8, new int[][] {
                { 1, 0 }, { 2, 6 }, { 1, 7 }, { 5, 1 }, { 6, 4 }, { 7, 0 }, { 0, 5 }
        })));
        System.out.println(Arrays.toString(new CourseSchedule2().findOrder(3, new int[][] {
                { 0, 2 }, { 1, 2 }, { 2, 0 }
        })));
        System.out.println(Arrays.toString(new CourseSchedule2().findOrder(4, new int[][] {
                { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 }
        })));
        System.out.println(Arrays.toString(new CourseSchedule2().findOrder(6, new int[][] {
                { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 }, { 4, 1 }, { 5, 3 }
        }))); //[0,2,1,3]
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            set.add(i);
        }

        for (int j = 0; j < prerequisites.length; j++) {
            int[] arr = prerequisites[j];
            set.remove(arr[1]);
        } //set contains only roots

        if (set.isEmpty()) {
            return new int[0];
        }

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int j = 0; j < prerequisites.length; j++) {
            int[] arr = prerequisites[j];

            Set<Integer> deps = map.getOrDefault(arr[0], new HashSet<>());
            deps.add(arr[1]);
            map.put(arr[0], deps);
        }

        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, map, new HashSet<>())) {
                return new int[0];
            }
        }

        List<Integer> list = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (Integer course : set) {
            visit(course, map, list, visited);
        }

        int[] out = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            out[i] = list.get(i);
        }
        return out;
    }

    private boolean hasCycle(final Integer course, final Map<Integer, Set<Integer>> courseDeps, final Set<Object> visited) {
        if (visited.contains(course)) {
            return true;
        }
        visited.add(course);
        Set<Integer> deps = courseDeps.getOrDefault(course, Set.of());
        for (Integer dep : deps) {
            return hasCycle(dep, courseDeps, visited);
        }
        return false;
    }

    void visit(Integer course, Map<Integer, Set<Integer>> courseDeps, List<Integer> list, Set<Integer> visited) {
        if (visited.contains(course)) {
            return;
        }
        visited.add(course);
        Set<Integer> deps = courseDeps.getOrDefault(course, Set.of());
        for (Integer dep : deps) {
            visit(dep, courseDeps, list, visited);
        }
        list.add(course);
    }

}
