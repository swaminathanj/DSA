# Step-by-step Implementation of Graph

## Define a Graph node and test its working

A graph node contains a label and its adjacency list. We use ArrayList for representing adjacency list.

``` java
// GraphNode.java

import java.util.ArrayList;

public class GraphNode {
    int label;
    ArrayList<GraphNode> adjList;

    GraphNode(int l) {
        label = l;
        adjList = new ArrayList<GraphNode>();
    }
}
```

``` java
// GraphNodeDriver.java

public class GraphNodeDriver {
    public static void main(String[] args) {
        GraphNode n0 = new GraphNode(0);
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3);
        n0.adjList.add(n1); 
        n0.adjList.add(n3);
        n1.adjList.add(n2);
        n2.adjList.add(n1);
        n2.adjList.add(n3);
    }
}
```
