# Step-by-step Implementation of Graph

## 1. Define a Graph node and test its working

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

## 2. Define Graph

Next, we define the Graph class which contains an array of nodes. Given the number of vertices (passed via constructor), it creates and initializes the nodes. It also includes addEdge method that is used to add the edges.

``` java
// GraphNode.java -- remains same
```

``` java
// Aum Amma

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
}
```

``` java
public class GraphDriver {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(1,4);
        g.addEdge(2,1);
        g.addEdge(2,4);
        g.addEdge(3,2);
        g.addEdge(3,4);
    }
}
```
