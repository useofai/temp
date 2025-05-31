import java.util.*;

public class DirectedCycleDetection {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1));
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(0));  // cycle: 0 -> 1 -> 2 -> 0
        graph.put(3, Arrays.asList(4));
        graph.put(4, new ArrayList<>());

        System.out.println(hasCycle(graph) ? "Cycle detected" : "No cycle detected");
    }

    public static boolean hasCycle(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();

        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (dfs(node, graph, visited, recursionStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(int node, Map<Integer, List<Integer>> graph,
                               Set<Integer> visited, Set<Integer> recStack) {
        visited.add(node);
        recStack.add(node);

        for (int neighbor : graph.getOrDefault(node, List.of())) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, graph, visited, recStack)) return true;
            } else if (recStack.contains(neighbor)) {
                return true;  // cycle found
            }
        }

        recStack.remove(node);
        return false;
    }
}
