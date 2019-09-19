// Aum Amma

import java.util.ArrayDeque;
import java.util.ArrayList;

public class GraphNode {
    int label;
    ArrayList<GraphNode> adjList;
    boolean visited;
    int preorder;
    int postorder;

    GraphNode(int l) {
        label = l;
        adjList = new ArrayList<GraphNode>();
        visited = false;
    }

    public void print() {
        for (int j=0; j<adjList.size(); j++)
            System.out.print(adjList.get(j).label + "->");
    }

    public int dfs(int visitCount) {

        System.out.print(label + " ");
        visited = true;
        preorder = visitCount;

        for (int j=0; j<adjList.size(); j++) {
            if ( !adjList.get(j).visited )
                visitCount = adjList.get(j).dfs(++visitCount);
        }
        postorder = ++visitCount;
        return visitCount;
    }

    void bfs(ArrayDeque<GraphNode> q) {
        while ( !q.isEmpty() ) {
            GraphNode x = q.remove();
            System.out.print(x.label + " ");
            for (int j=0; j<x.adjList.size(); j++) {
                GraphNode y = x.adjList.get(j);
                if ( !y.visited ) {
                    q.add(y);
                    y.visited = true;
                }
            }
        }
    }
}

