import java.util.*;

public class BipartiteCheck {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 3));
        graph.put(1, Arrays.asList(0, 2));
        graph.put(2, Arrays.asList(1, 3));
        graph.put(3, Arrays.asList(0, 2));

        System.out.println(isBipartite(graph) ? "Graph is Bipartite" : "Graph is NOT Bipartite");
    }

    public static boolean isBipartite(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> color = new HashMap<>();

        for (int node : graph.keySet()) {
            if (!color.containsKey(node)) {
                if (!bfsCheck(node, graph, color)) return false;
            }
        }
        return true;
    }

    private static boolean bfsCheck(int start, Map<Integer, List<Integer>> graph, Map<Integer, Integer> color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color.put(start, 0);  // color with 0 or 1

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.getOrDefault(node, List.of())) {
                if (!color.containsKey(neighbor)) {
                    color.put(neighbor, 1 - color.get(node));
                    queue.add(neighbor);
                } else if (color.get(neighbor).equals(color.get(node))) {
                    return false; // same color on both ends of an edge => not bipartite
                }
            }
        }
        return true;
    }
}
