#include <iostream>
#include <vector>
using namespace std;
#define min 999
#define n 4

class prims
{
public:
    int minVertex(vector<bool> mst, vector<int> v)
    {
        int vertex;
        int m = min;
        for (int k = 0; k < v.size(); k++)
        {
            if (mst[k] == false && v[k] < min)
            {
                vertex = k;
                m = v[k];
            }
        }
        return vertex;
        cout << "source vertex is: " << vertex << endl;
    }

    void minST(int graph[n][n])
    {
        int parent[n];
        vector<bool> mst(n, false);
        vector<int> value(n, min);

        parent[0] = -1;
        value[0] = 0;

        for (int i = 0; i < n - 1; i++) // first loop runs for n-1 times
        {
            int u = minVertex(mst, value);
            mst[u] = true;

            for (int j = 0; j < n; j++)
            {
                if (graph[u][j] != 0 && mst[j] == false && value[j] > graph[u][j])
                {
                    value[j] = graph[u][j];
                    parent[j] = u;
                }
            }
        }
        int total_cost = 0;

        for (int i = 1; i < n; i++) // loop starts from 1, parent[0] = -1
        {
            cout << parent[i] << " -> " << i << " : " << graph[parent[i]][i] << endl;
            total_cost += graph[parent[i]][i];
        }
        cout << "\ntotal cost for mst is: " << total_cost;
    }
};

int main()
{

    prims p;
    cout << "enter edges: ";
    int edge;
    cin >> edge;
    int graph[n][n];
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            graph[i][j] = 0;
        }
    }
    for (int i = 0; i < edge; i++)
    {
        int v1, v2, cost;
        cout << "enter adjacent vertex pair: ";
        cin >> v1 >> v2;
        cout << "enter cost: ";
        cin >> cost;
        graph[v1][v2] = cost; // assigning cost at that vertex pair in the matrix
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cout << graph[i][j] << " ";
        }
        cout << endl;
    }
    p.minST(graph);
    return 0;
}