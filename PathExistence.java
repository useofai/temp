import java.util.*;

public class PathExistence {

    static boolean bfs(Map<Integer, List<Integer>> graph, int src, int dest) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(src); visited.add(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == dest) return true;
            for (int v : graph.getOrDefault(u, List.of())) {
                if (visited.add(v)) q.add(v);
            }
        }
        return false;
    }

    static boolean dfs(Map<Integer, List<Integer>> graph, int src, int dest, Set<Integer> visited) {
        if (src == dest) return true;
        visited.add(src);
        for (int v : graph.getOrDefault(src, List.of())) {
            if (!visited.contains(v) && dfs(graph, v, dest, visited)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = Map.of(
            0, List.of(1, 2),
            1, List.of(2),
            2, List.of(3),
            3, List.of()
        );

        int src = 0, dest = 3;

        System.out.println("BFS: Path exists? " + bfs(graph, src, dest));
        System.out.println("DFS: Path exists? " + dfs(graph, src, dest, new HashSet<>()));
    }
}
