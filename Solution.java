import java.util.*;

class Node implements Comparable<Node> {
    int vertex;
    int cost;

    Node(int v, int c) {
        vertex = v;
        cost = c;
    }

    public int compareTo(Node n) {
        return cost - n.cost;
    }
}
class Graph {
    int vrx;
    int node;
    List<List<Node>> adj;
    int[] dist;
    boolean[] visited;
    String[] name;
    PriorityQueue<Node> pq;


    Graph(int v, int node) {
        vrx = v;
        this.node = node;
        adj = new ArrayList<>(node);
        for (int i = 0; i < node; ++i) {
            adj.add(new ArrayList<>());
        }
        name = new String[node];
        dist = new int[node];
        visited = new boolean[node];
        pq = new PriorityQueue<>();
    }

    public void addNode(String name){
        for (int i = 0; i < node; i++) {
            if (this.name[i] == null){
                this.name[i] = name;
                return;
            }

        }
    }
    public int getIndex(String name){
        for (int i = 0; i < node; i++) {
            if (name.equals(this.name[i])){
                return i;
            }
        }
        return node;
    }

    public void addEdge(String a, String b, int c) {
        int u = getIndex(a);
        int v = getIndex(b);

        adj.get(u).add(new Node(v, c));
        adj.get(v).add(new Node(u, c));
    }

    public int shortestPath(String source, String destination) {
        int src = getIndex(source);
        int dest = getIndex(destination);
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        dist[src] = 0;
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;
            visited[u] = true;

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.cost;

                if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        return dist[dest];
    }
}


public class Solution {
    public static void main(String []args) {
        Scanner scan = new Scanner(System.in);
        String[] jumlah = scan.nextLine().split(" ");
        int n = Integer.parseInt(jumlah[0]);
        int v = Integer.parseInt(jumlah[1]);

        Graph graph = new Graph(v, n);

        String[] node = scan.nextLine().split(" ", n);

        for (String in: node) {
            graph.addNode(in);
        }

        for (int i = 0; i < v; i++) {
            String[] input = scan.nextLine().split(" ");
            graph.addEdge(input[0], input[1], Integer.parseInt(input[2]));
        }

        System.out.println(graph.shortestPath(scan.next(), scan.next()));
    }
}
