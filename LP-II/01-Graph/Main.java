import java.util.*;

class Graph {
    int vertices;
    List<List<Integer>> adjlist;
    Map<String, Integer> city_to_index;
    List<String> index_to_city;

    public Graph(int v) {
        vertices = v;
        adjlist = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjlist.add(new ArrayList<>());
        }
        city_to_index = new HashMap<>();
        index_to_city = new ArrayList<>();
    }

    public void addEdge(String a, String b) {
        if (!city_to_index.containsKey(a)) {
            city_to_index.put(a, city_to_index.size());
            index_to_city.add(a);
        }
        if (!city_to_index.containsKey(b)) {
            city_to_index.put(b, city_to_index.size());
            index_to_city.add(b);
        }
        int u = city_to_index.get(a);
        int v = city_to_index.get(b);
        adjlist.get(u).add(v);
        adjlist.get(v).add(u);
        System.out.println(adjlist);
    }

    public void printProgress(List<Integer> progress) {
        for (int i : progress) {
            System.out.print(index_to_city.get(i) + " ");
        }
        System.out.println();
    }

    public void bfs(boolean[] visited, Queue<Integer> q, List<Integer> progress) {
        if (q.isEmpty()) {
            return;
        }

        int u = q.poll();
        progress.add(u);

        for (int v : adjlist.get(u)) {
            if (!visited[v]) {
                visited[v] = true;
                q.add(v);
            }
        }
        printProgress(progress);
        bfs(visited, q, progress);
    }

    public void dfs(int u, boolean[] visited, List<Integer> progress) {
        visited[u] = true;
        progress.add(u);
        printProgress(progress);

        for (int v : adjlist.get(u)) {
            if (!visited[v]) {
                dfs(v, visited, progress);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter no of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        Graph g = new Graph(vertices);

        String city1, city2;
        for (int i = 0; i < edges; i++) {
            System.out.print("Enter edge " + (i + 1) + " (City A City B): ");
            city1 = scanner.next();
            city2 = scanner.next();
            g.addEdge(city1, city2);
        }

        char ans;
        do {
            System.out.println("\n1. BFS\n2. DFS\nEnter choice: ");
            int choice = scanner.nextInt();

            int start_vertex = 0; // Assuming start from vertex 0
            boolean[] visited = new boolean[g.vertices];
            List<Integer> progress = new ArrayList<>();
            Queue<Integer> q = new LinkedList<>();
            switch (choice) {
                case 1:
                    visited[start_vertex] = true;
                    q.add(start_vertex);
                    g.bfs(visited, q, progress);
                    break;

                case 2:
                    g.dfs(start_vertex, visited, progress);
                    break;

                default:
                    System.out.println("\nInvalid!");
                    break;
            }

            System.out.print("Final Traversal: ");
            g.printProgress(progress);

            System.out.print("\nContinue? (y/n): ");
            ans = scanner.next().charAt(0);
        } while (ans == 'y' || ans == 'Y');

        scanner.close();
    }
}
