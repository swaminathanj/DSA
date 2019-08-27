// Aum Amma

import java.util.ArrayList;

public class GraphNode {
    int label;
    ArrayList<GraphNode> adjList;

    GraphNode(int l) {
        label = l;
        adjList = new ArrayList<GraphNode>();
    }

    public void print() {
        for (int j=0; j<adjList.size(); j++)
            System.out.print(adjList.get(j).label + "->");
    }

}