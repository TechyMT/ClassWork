#include <bits/stdc++.h>
using namespace std;
#define v 4
class graph
{
public:
    int par[v + 1];
    int findpar(int x)
    {
        if (par[x] == x)
            return x;
        return par[x] = findpar(par[x]);
    }
    int Union_set(int x, int y)
    {
        // checks for cyclic graphs
        int p = findpar(x);
        int q = findpar(y);
        par[p] = q; // initialise
    }

    void kruskal(int adj[v][v])
    {
        for (int i = 0; i < v + 1; i++)
        {
            par[i] = i;
        }
        int ans = 0;
        int edge = 0;
        while (edge < v - 1)
        {
            int min = 1e9;
            int x = 0, y = 0;
            for (int i = 0; i < v; i++)
            {
                for (int j = 0; j < v; j++)
                {
                    if (findpar(i) != findpar(j) && adj[i][j] != 0 && min > adj[i][j])
                    {
                        x = i;
                        y = j;
                        min = adj[i][j];
                    }
                }
            }
            cout << x << " " << y << endl;
            edge++;
            ans += min;
            Union_set(x, y);
        }
        cout << "The cost is: " << ans << endl;
    }
};

int main()
{
    graph g;
    int graph[v][v];
    for (int i = 0; i < v; i++)
    {
        for (int j = 0; j < v; j++)
        {
            graph[i][j] = 0;
        }
    }
    int edge;
    cout << "Enter total edges: ";
    cin >> edge;
    for (int i = 0; i < edge; i++)
    {
        int v1, v2, cost;
        cout << "Enter adjacent vertex pair: ";
        cin >> v1 >> v2;
        cout << "Enter cost: ";
        cin >> cost;
        graph[v1][v2] = cost;
        graph[v2][v1] = cost;
    }
    for (int i = 0; i < v; i++)
    {
        for (int j = 0; j < v; j++)
        {
            cout << graph[i][j] << " ";
        }
        cout << endl;
    }
    g.kruskal(graph);
    return 0;
}