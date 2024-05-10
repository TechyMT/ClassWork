import java.util.*;

class prims {
    Map<String, Integer> vertexMap;
    int V;
    List<List<Pair>> graph;

    public prims(String[] vertices) {
        vertexMap = new HashMap<>();
        V = vertices.length;
        graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            vertexMap.put(vertices[i], i);
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(String u, String v, int w) {
        int uIdx = vertexMap.get(u);
        int vIdx = vertexMap.get(v);
        graph.get(uIdx).add(new Pair(vIdx, w));
        graph.get(vIdx).add(new Pair(uIdx, w));
    }

    public void prim() {
        int cost = 0;
        Set<Integer> unvisited = new HashSet<>();
        for (int i = 0; i < V; i++) {
            unvisited.add(i);
        }
        Set<Integer> mst = new HashSet<>();

        mst.add(0);
        unvisited.remove(0);

        while (!unvisited.isEmpty()) {
            int minWeight = Integer.MAX_VALUE;
            Pair minEdge = null;

            for (int u : mst) {
                for (Pair p : graph.get(u)) {
                    int v = p.destination;
                    int w = p.weight;

                    if (unvisited.contains(v) && w < minWeight) {
                        minWeight = w;
                        minEdge = new Pair(u, v, w);
                    }
                }
            }

            if (minEdge != null) {
                cost += minWeight;
                mst.add(minEdge.destination);
                unvisited.remove(minEdge.destination);
            }
        }

        System.out.println("Total cost: " + cost);
    }

    static class Pair {
        int destination;
        int weight;

        public Pair(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public Pair(int source, int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of routers: ");
        scanner.nextLine(); // Consume newline
        System.out.print("Enter the hostname of the routers separated by space: ");
        String[] vertices = scanner.nextLine().split("\\s+");

        prims g = new prims(vertices);

        System.out.print("Enter the number of cables in the network: ");
        int edges = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter the connections and cost for each cable (source,destination,cost): ");
        for (int i = 0; i < edges; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            g.addEdge(input[0], input[1], Integer.parseInt(input[2]));
        }

        System.out.println("\nMST for routing network: ");
        g.prim();
        scanner.close();
    }
}
