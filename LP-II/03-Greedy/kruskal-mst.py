class Graph:

    def _init_(self) -> None:
        self.adj_list: dict[str, list[(str, int)]] = {}
        self.edges = []

    def add_edge(self, node1, node2, weight):
        if node1 not in self.adj_list:
            self.adj_list[node1] = []
        self.adj_list[node1].append((node2, weight))
        if node2 not in self.adj_list:
            self.adj_list[node2] = []
        self.adj_list[node2].append((node1, weight))
        self.edges.append((node1, node2, weight))

    def check_cycle(self, src: str, dst: str):
        if src not in self.adj_list or dst not in self.adj_list:
            return False
        queue = [src]
        visited = [src]
        while len(queue) != 0:
            front = queue.pop(0)
            if front == dst:
                return True
            neighbours = self.adj_list[front]
            for neighbour in neighbours:
                if neighbour[0] not in visited:
                    visited.append(neighbour[0])
                    queue.append(neighbour[0])

        return False

    def _str_(self) -> str:
        str_repr = ""
        for key, val in self.adj_list.items():
            str_repr += f"{key} {self.adj_list[key]}"
        return str_repr


def min_spanning_tree(graph: Graph):
    ordered_edges = list(sorted(graph.edges, key=lambda edge: edge[2]))
    spanning_tree: Graph = Graph()
    min_cost = 0
    for edge in ordered_edges:
        if spanning_tree.check_cycle(edge[0], edge[1]):
            continue
        spanning_tree.add_edge(*edge)
        min_cost += edge[2]
    print("Minimum cost of spanning tree: ", min_cost)


graph = Graph()

while True:
    print("Add edge to the graph...")
    nodeA = input("Enter Node A: ")
    nodeB = input("Enter Node B: ")
    if nodeA == nodeB == "-":
        break
    weight = int(input("Enter distance b/w nodes A and B: "))
    graph.add_edge(nodeA, nodeB, weight)

print("Graph Adjacency List: ")
print(graph)

min_spanning_tree(graph)
