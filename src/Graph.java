// Aum Amma

import java.util.ArrayDeque;

public class Graph {
    GraphNode[] node;
    int size;

    public Graph(int n) {
        size = n;
        node = new GraphNode[size];
        for (int i=0; i<size; i++)
            node[i] = new GraphNode(i);
    }

    public void addEdge(int from, int to) {
        node[from].adjList.add(node[to]);
    }

    public void print() {
        for (int i=0; i<size; i++) {
            System.out.print(i + ": ");
            node[i].print(); 
            System.out.println();
        }
    }

    public void dfs() {
        reset(); // Set visited to false before starting dfs
        System.out.print("DFS traversal: ");
        int visitCount = 0;
        for (int i=0; i<size; i++) {
            if ( !node[i].visited )
               visitCount = node[i].dfs(++visitCount);
       }
       System.out.println();
    }

    public void printOrder() {
        for (int i=0; i<size; i++)
            System.out.print(node[i].label + " ("
                            + node[i].preorder + ","
                            + node[i].postorder + ") ");
        System.out.println();
    }

    public void reset() {
        for (int i=0; i<size; i++) {
            node[i].visited = false;
            node[i].preorder = 0;
            node[i].postorder = 0;
        }
    }

    public void bfs() {
        reset(); // Set visited to false before starting bfs
        System.out.print("BFS traversal: ");
        ArrayDeque<GraphNode> q = new ArrayDeque<GraphNode>();
        for (int i=0; i<size; i++) {
            if ( !node[i].visited ){
                q.add(node[i]);
                node[i].visited = true;
                node[i].bfs(q);
            }
        }
    }
}

