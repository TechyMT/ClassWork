#include <bits/stdc++.h>
using namespace std;

class Graph
{
    int **adjMatrix;
    int vertexCount, edgeCount;
    map<string, int> cities;
    map<int, string> inv_cities;

    void add(string city1, string city2, int dist)
    {
        adjMatrix[cities[city1]][cities[city2]] = dist;
        adjMatrix[cities[city2]][cities[city1]] = dist;
    }

    int minimum_key(int keys[], bool mst[])
    {
        int minimum = INT_MAX, min;
        for (int i = 0; i < vertexCount; i++)
        {
            if (mst[i] == 0 && keys[i] < minimum)
            {
                minimum = keys[i];
                min = i;
            }
        }
        return min;
    }

public:
    Graph()
    {
        cout << "Enter number of vertices: ";
        cin >> vertexCount;
        cout << "Enter number of edges: ";
        cin >> edgeCount;

        adjMatrix = new int *[vertexCount];
        for (int i = 0; i < vertexCount; i++)
        {
            adjMatrix[i] = new int[vertexCount];
            for (int j = 0; j < vertexCount; j++)
                adjMatrix[i][j] = 0;
        }

        cout << "Add cities in order..." << endl;
        for (int i = 0; i < vertexCount; i++)
        {
            string city;
            cout << "Enter city name: ";
            cin >> city;
            cities[city] = i;
            inv_cities[i] = city;
        }

        cout << "Add the " << edgeCount << " edges..." << endl;
        for (int i = 0; i < edgeCount; i++)
        {
            string u, v;
            int d;
            cout << "City 1: ";
            cin >> u;
            cout << "City 2: ";
            cin >> v;
            cout << "Distance: ";
            cin >> d;
            add(u, v, d);
        }
    }

    void prim()
    {
        int parents[vertexCount], keys[vertexCount];
        bool mst[vertexCount];

        for (int i = 0; i < vertexCount; i++)
        {
            keys[i] = INT_MAX;
            mst[i] = 0;
        }

        parents[0] = -1;
        keys[0] = -1;

        for (int count = 0; count < vertexCount - 1; count++)
        {
            int min_edge_vertex = minimum_key(keys, mst);
            mst[min_edge_vertex] = 1;

            for (int vertex = 0; vertex < vertexCount; vertex++)
            {
                if (adjMatrix[min_edge_vertex][vertex] && mst[vertex] == 0 && adjMatrix[min_edge_vertex][vertex] < keys[vertex])
                {
                    parents[vertex] = min_edge_vertex;
                    keys[vertex] = adjMatrix[min_edge_vertex][vertex];
                }
            }
        }

        cout << "MST of given graph is: \n";
        cout << "Edge\tWeight" << endl;
        for (int i = 1; i < vertexCount; i++)
        {
            cout << inv_cities[parents[i]] << " <-> " << inv_cities[i] << " \t " << adjMatrix[i][parents[i]] << endl;
        }
    }
};

int main()
{
    Graph g;
    g.prim();
}