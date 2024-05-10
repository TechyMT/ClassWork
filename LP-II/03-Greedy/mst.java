import java.util.*;

class GraphMST {
    private int vertices;
    private int[][] graph;

    public GraphMST(int vertices) {
        this.vertices = vertices;
        this.graph = new int[vertices][vertices];
    }

    public void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        graph[v][u] = weight;
    }

    private int minKey(int[] keys, boolean[] mstSet) {
        int minVal = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (keys[v] < minVal && !mstSet[v]) {
                minVal = keys[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public void primMST() {
        int[] keys = new int[vertices];
        int[] parents = new int[vertices];
        Arrays.fill(keys, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);
        keys[0] = 0;
        boolean[] mstSet = new boolean[vertices];

        for (int count = 0; count < vertices - 1; count++) {
            int u = minKey(keys, mstSet);

            mstSet[u] = true;
            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] > 0 && !mstSet[v] && keys[v] > graph[u][v]) {
                    keys[v] = graph[u][v];
                    parents[v] = u;
                }
            }
        }

        System.out.println("Edge \tWeight");
        for (int i = 1; i < vertices; i++) {
            System.out.println(parents[i] + " - " + i + "\t" + graph[i][parents[i]]);
        }
    }
}

public class mst {
    public static void main(String[] args) {
        GraphMST g = new GraphMST(5);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        g.primMST();
    }
}
