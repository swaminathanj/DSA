// Aum Amma

import java.util.ArrayList;

public class GraphNode {
    int label;
    ArrayList<GraphNode> adjList;
    boolean visited;

    GraphNode(int l) {
        label = l;
        adjList = new ArrayList<GraphNode>();
        visited = false;
    }

    public void print() {
        for (int j=0; j<adjList.size(); j++)
            System.out.print(adjList.get(j).label + "->");
    }

    public void dfs() {

        System.out.print(label + " ");
        visited = true;

        for (int j=0; j<adjList.size(); j++) {
            if ( !adjList.get(j).visited )
                adjList.get(j).dfs();
        }
    }
}

