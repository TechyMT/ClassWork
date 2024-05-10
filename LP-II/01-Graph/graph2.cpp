#include <bits/stdc++.h>
using namespace std;

class Node
{
public:
    string name;
    Node *next;
    Node *down;

    Node(string n = "")
    {
        name = n;
        next = nullptr;
        down = nullptr;
    }
};

class Graph
{
    Node *head = nullptr;

    void add(string NodeA, string NodeB)
    {
        if (head == nullptr)
        {
            Node *node1 = new Node(NodeA);
            Node *node2 = new Node(NodeB);
            head = node1;
            node1->next = node2;
        }
        else
        {
            Node *curr = head;
            Node *prev = nullptr;
            bool found = false;

            while (curr != nullptr)
            {
                if (curr->name == NodeA)
                {
                    found = true;
                    Node *hListNode = curr->next;
                    while (hListNode->next != nullptr)
                        hListNode = hListNode->next;
                    Node *newNode = new Node(NodeB);
                    hListNode->next = newNode;
                    break;
                }
                prev = curr;
                curr = curr->down;
            }

            if (!found)
            {
                Node *node1 = new Node(NodeA);
                Node *node2 = new Node(NodeB);
                prev->down = node1;
                node1->next = node2;
            }
        }
    }

public:
    void addNode(string NodeA, string NodeB)
    {
        add(NodeA, NodeB);
        add(NodeB, NodeA);
    }

    void print()
    {
        Node *currentArrayNode = head;
        while (currentArrayNode != nullptr)
        {
            Node *currentListNode = currentArrayNode;
            while (currentListNode != nullptr)
            {
                cout << currentListNode->name << " -> ";
                currentListNode = currentListNode->next;
            }
            cout << endl;
            currentArrayNode = currentArrayNode->down;
        }
    }

    void BFS(string node)
    {
        queue<string> Queue;
        vector<string> visited;
        Queue.push(node);
        visited.push_back(node);
        while (true)
        {

            if (!Queue.empty())
            {
                node = Queue.front();
                Queue.pop();
                cout << node << " ";
            }
            else
            {
                cout << endl;
                break;
            }

            Node *currentNode = head;
            bool found = false;
            while (currentNode != nullptr)
            {
                if (currentNode->name == node)
                {
                    found = true;
                    Node *currentListNode = currentNode->next;
                    while (currentListNode != nullptr)
                    {
                        if (find(visited.begin(), visited.end(), currentListNode->name) == visited.end())
                        {
                            visited.push_back(currentListNode->name);
                            Queue.push(currentListNode->name);
                        }
                        currentListNode = currentListNode->next;
                    }
                    break;
                }
                currentNode = currentNode->down;
            }
            if (!found)
            {
                cout << "Given node not in the graph" << endl;
                return;
            }
        }
    }

    void DFS(string node)
    {
        stack<string> Stack;
        vector<string> visited;
        Stack.push(node);
        visited.push_back(node);
        while (true)
        {
            if (!Stack.empty())
            {
                node = Stack.top();
                Stack.pop();
                cout << node << " ";
            }
            else
            {
                cout << endl;
                break;
            }

            Node *currentNode = head;
            bool found = false;
            while (currentNode != nullptr)
            {
                if (currentNode->name == node)
                {
                    found = true;
                    Node *currentListNode = currentNode->next;
                    while (currentListNode != nullptr)
                    {
                        if (find(visited.begin(), visited.end(), currentListNode->name) == visited.end())
                        {
                            Stack.push(currentListNode->name);
                            visited.push_back(currentListNode->name);
                        }
                        currentListNode = currentListNode->next;
                    }
                    break;
                }
                currentNode = currentNode->down;
            }
            if (!found)
            {
                cout << "Given node doesn't exist in the graph" << endl;
                return;
            }
        }
    }
};

int main()
{
    Graph g;
    g.addNode("Katraj", "PICT");
    g.addNode("Bharti", "Katraj");
    g.addNode("Bharti", "PICT");
    g.addNode("Katraj", "SKNCOE");
    g.addNode("PICT", "SKNCOE");
    g.addNode("SKNCOE", "Temple");
    g.addNode("Temple", "Katraj");
    g.addNode("Temple", "Kondhwa");
    g.print();

    g.BFS("Katraj");
    g.DFS("Katraj");
    return 0;
}