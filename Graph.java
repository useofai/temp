import java.util.*;

public class Graph {
    private int V;
    private List<List<Integer>> adjList;
    public Graph(int V) {
        this.V = V;
        adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u); 
    }


    private void dfsUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited);
            }
        }
    }


    public void dfs(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS starting from node " + start + ": ");
        dfsUtil(start, visited);
        System.out.println();
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        System.out.print("BFS starting from node " + start + ": ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adjList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Graph g = new Graph(6); // 6 nodes: 0 to 5

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 5);

        g.dfs(0);
        g.bfs(0);
    }
}
