import java.util.*;
public class Pathfromsrctodest {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0, 3));
        graph.put(3, Arrays.asList(1, 2, 4));
        graph.put(4, Arrays.asList(3));

        int src = 0;
        int dest = 4;

        List<Integer> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        System.out.println("Paths from " + src + " to " + dest + ":");
        findPaths(graph, src, dest, visited, path);
    }

    public static void findPaths(Map<Integer, List<Integer>> graph, int current, int dest,
                                 Set<Integer> visited, List<Integer> path) {
        visited.add(current);
        path.add(current);

        if (current == dest) {
            System.out.println(path);
        } else {
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    findPaths(graph, neighbor, dest, visited, path);
                }
            }
        }

        visited.remove(current);
        path.remove(path.size() - 1);
    }
}
