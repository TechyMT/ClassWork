#include<bits/stdc++.h>
using namespace std;

int heuristicFunc(vector<vector<int>>&state, vector<vector<int>>&grid, int level)
{
    int count = 0;
    for (int i = 0; i < 3;i++)
    {
        for (int j = 0; j < 3;j++)
        {
            if(state[i][j] != -1)
            {
                if(state[i][j] != grid[i][j])
                {
                    count++;
                }
            }
        }
    }
    return count + level;
}

int main()
{
    vector<vector<int>> start(3, vector<int>(3));
    vector<vector<int>> end(3, vector<int>(3));
    cout << "Start state" << endl;
    for(auto& it:start)
    {
        for(auto& i:it)
        {
            cin >> i;
        }
    }
    cout << "End State" << endl;
    for(auto& it:end)
    {
        for(auto& i:it)
        {
            cin >> i;
        }
    }

    priority_queue <pair<int,pair<int,vector<vector<int>>>>, vector<pair<int,pair<int,vector<vector<int>>>>>, greater<pair<int,pair<int,vector<vector<int>>>>> > pq;
    pq.push({heuristicFunc(start, end, 0), {0, start}});

    while(!pq.empty())
    {
        int val = pq.top().first;
        int level = pq.top().second.first;
        vector<vector<int>> state = pq.top().second.second;
        pq.pop();
        cout << "Selected state at level: " << level << endl;

        for(auto it:state)
        {
            for(auto i:it)
            {
                cout << i << " ";
            }
            cout << endl;
        }

        if(state == end)
        {
            cout << "Final state is reached" << endl;
            break;
        }

        int delrow[4] = {-1, 0, 1, 0};
        int delcol[4] = {0, 1, 0, -1};
        //For each possible move (up, down, left, right)
        cout << "Possible moves" << endl;
        for (int i = 0; i < 3;i++)
        {
            for (int j = 0; j < 3;j++)
            {
                if(state[i][j] == -1)
                {
                    for (int a = 0; a < 4;a++)
                    {
                        int ni = i + delrow[a];
                        int nj = j + delcol[a];

                        if(ni>=0 && ni<3 && nj>=0 && nj<3)
                        {
                            vector<vector<int>> temp = state;
                            swap(temp[i][j], temp[ni][nj]);
                            pq.push({heuristicFunc(temp, end, level + 1), {level + 1, temp}});
                            for(auto it:temp)
                            {
                                for(auto i : it)
                                {
                                    cout << i << " ";
                                }
                                cout << endl;
                            }
                            cout << "g(n): " << level + 1 << endl;
                            cout << "h(n): " << heuristicFunc(temp, end, level + 1) - level - 1 << endl;
                            cout << "f(n): " << heuristicFunc(temp, end, level + 1) << endl;
                        }
                    }
                }
            }
        }
    }
}

//testcases
//initial   final
// 1 2 3    1 2 3
// -1 4 6   4 5 6
// 7 5 8    7 8 -1