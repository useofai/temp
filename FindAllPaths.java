import java.util.*;

public class FindAllPaths {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0, 3));
        graph.put(3, Arrays.asList(1, 2, 4));
        graph.put(4, Arrays.asList(3));

        int src = 0;
        int dest = 4;

        List<List<Integer>> allPaths = findAllPaths(graph, src, dest);
        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }
    }

    public static List<List<Integer>> findAllPaths(Map<Integer, List<Integer>> graph, int src, int dest) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfs(graph, src, dest, visited, currentPath, result);
        return result;
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int current, int dest,
                            Set<Integer> visited, List<Integer> currentPath, List<List<Integer>> result) {
        visited.add(current);
        currentPath.add(current);

        if (current == dest) {
            result.add(new ArrayList<>(currentPath));
        } else {
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    dfs(graph, neighbor, dest, visited, currentPath, result);
                }
            }
        }

        visited.remove(current);
        currentPath.remove(currentPath.size() - 1);
    }
}
