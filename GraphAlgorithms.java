import java.util.*;

public class GraphAlgorithms {

    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
        public int compareTo(Edge e) { return w - e.w; }
    }

    // --- Prim's MST ---
    static int prim(List<List<Edge>> graph, int n) {
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0, 0)); // start from node 0
        int cost = 0;
        int edges = 0;
        while (!pq.isEmpty() && edges < n) {
            Edge e = pq.poll();
            if (visited[e.v]) continue;
            visited[e.v] = true;
            cost += e.w;
            edges++;
            for (Edge ne : graph.get(e.v)) {
                if (!visited[ne.v]) pq.add(ne);
            }
        }
        return edges == n ? cost : -1; // -1 if MST not possible
    }

    // --- Kruskal's MST ---
    static int kruskal(List<Edge> edges, int n) {
        Collections.sort(edges);
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int cost = 0, count = 0;

        for (Edge e : edges) {
            int pu = find(parent, e.u), pv = find(parent, e.v);
            if (pu != pv) {
                parent[pu] = pv;
                cost += e.w;
                count++;
                if (count == n - 1) break;
            }
        }
        return count == n - 1 ? cost : -1;
    }

    static int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    // --- Dijkstra's shortest path ---
    static int[] dijkstra(List<List<Edge>> graph, int src, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> a.w - b.w);
        pq.add(new Edge(-1, src, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int u = e.v;
            if (e.w > dist[u]) continue;
            for (Edge ne : graph.get(u)) {
                if (dist[u] + ne.w < dist[ne.v]) {
                    dist[ne.v] = dist[u] + ne.w;
                    pq.add(new Edge(u, ne.v, dist[ne.v]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Add undirected edges (u, v, w)
        int[][] edgesArr = {
            {0, 1, 2},
            {0, 3, 6},
            {1, 2, 3},
            {1, 3, 8},
            {1, 4, 5},
            {2, 4, 7},
            {3, 4, 9}
        };

        List<Edge> allEdges = new ArrayList<>();
        for (int[] e : edgesArr) {
            graph.get(e[0]).add(new Edge(e[0], e[1], e[2]));
            graph.get(e[1]).add(new Edge(e[1], e[0], e[2]));
            allEdges.add(new Edge(e[0], e[1], e[2]));
        }

        System.out.println("Prim's MST cost: " + prim(graph, n));
        System.out.println("Kruskal's MST cost: " + kruskal(allEdges, n));

        int[] dist = dijkstra(graph, 0, n);
        System.out.println("Dijkstra distances from node 0:");
        for (int i = 0; i < n; i++) System.out.println("Node " + i + ": " + dist[i]);
    }
}
