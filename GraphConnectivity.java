import java.util.*;

public class GraphConnectivity {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0));
        graph.put(3, Arrays.asList(1));
        // graph.put(4, Arrays.asList()); // Uncomment to test disconnected graph

        System.out.println("Is Connected (BFS)? " + isConnectedBFS(graph));
        System.out.println("Is Connected (DFS)? " + isConnectedDFS(graph));
    }

    // BFS approach
    public static boolean isConnectedBFS(Map<Integer, List<Integer>> graph) {
        if (graph.isEmpty()) return true;

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        int start = graph.keySet().iterator().next();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.getOrDefault(node, List.of())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visited.size() == graph.size();
    }

    // DFS approach
    public static boolean isConnectedDFS(Map<Integer, List<Integer>> graph) {
        if (graph.isEmpty()) return true;

        Set<Integer> visited = new HashSet<>();
        int start = graph.keySet().iterator().next();

        dfs(start, graph, visited);

        return visited.size() == graph.size();
    }

    private static void dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        visited.add(node);
        for (int neighbor : graph.getOrDefault(node, List.of())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited);
            }
        }
    }
}
