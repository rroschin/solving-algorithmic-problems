package zyx.romros;

import java.util.LinkedList;
import java.util.List;

public class MazeSolver {

    int[][] dirs = new int[][]{
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    public static void main(String[] args) {
        String[] maze = new String[]{
          "########## #",
          "#        # #",
          "#        # #",
          "# ######## #",
          "#          #",
          "# ##########"
        };
        Point start = new Point(10, 0);
        Point end = new Point(1, 5);
        List<Point> path = new MazeSolver().solve(maze, start, end);
        System.out.println(path);

        for (Point point : path) {
            StringBuilder sb = new StringBuilder(maze[point.y]);
            sb.setCharAt(point.x, '.');
            maze[point.y] = sb.toString();
        }
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length(); j++) {
                System.out.print(maze[i].charAt(j));
            }
            System.out.println();
        }
    }

    private List<Point> solve(String[] maze, Point start, Point end) {
        LinkedList<Point> path = new LinkedList<>();
        boolean[][] seen = new boolean[maze.length][maze[0].length()];

        walk(maze, end, start, seen, path);
        return path;
    }

    private boolean walk(String[] maze, Point end, Point curr, boolean[][] seen, LinkedList<Point> path) {
        if ((curr.x < 0 || curr.x >= maze[0].length()) ||
            (curr.y < 0 || curr.y >= maze.length)) {
            return false;
        }
        if (maze[curr.y].charAt(curr.x) == '#') {
            return false;
        }
        if (curr.x == end.x && curr.y == end.y) {
            path.push(curr);
            return true;
        }
        if (seen[curr.y][curr.x]) {
            return false;
        }

        path.push(curr);
        seen[curr.y][curr.x] = true;
        for (int[] dir : dirs) {
            int x = curr.x + dir[0];
            int y = curr.y + dir[1];

            boolean found = walk(maze, end, new Point(x, y), seen, path);
            if (found) {
                return true;
            }
        }
        path.pop();
        return false;
    }

    public record Point(int x, int y) {
    }
}
