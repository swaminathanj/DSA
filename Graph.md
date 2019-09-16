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

Next, we define the Graph class which contains an array of nodes. Given the number of vertices (passed via constructor), it creates and initializes the nodes. It also includes addEdge method that is used to add the edges. The driver program uses this method to populate the adjacency lists of the nodes.

``` java
// GraphNode.java -- remains same
```

``` java
// Graph.java

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
// GraphDriver.java

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

## 3. Print the adjacency list of each node of the graph

Let's check if the adjacency list was rightly created by adding a print method to print the adjacency list of each node. Note that the call to print method of Graph delegates the printing of the adjacency list to the respective nodes. The driver invokes the print method of Graph to print the 

``` java
// GraphNode.java

import java.util.ArrayList;

public class GraphNode {
    int label;
    ArrayList<GraphNode> adjList;

    GraphNode(int l) { ... }

    public void print() {
        for (int j=0; j<adjList.size(); j++)
            System.out.print(adjList.get(j).label + "->");
    }
}
```

``` java
// Graph.java

public class Graph {
    GraphNode[] node;
    int size;

    public Graph(int n) { ... }
    public void addEdge(int from, int to) { ... }

    public void print() {
        for (int i=0; i<size; i++) {
            System.out.print(i + ": "); // print the node label
            node[i].print();   // print its adjacency list (delegated to the node)
            System.out.println();  // print newline
        }
    }
}
```

``` java
// GraphDriver.java

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

        g.print();
    }
}
```

The expected output

```
0: 1->2->3->
1: 4->
2: 1->4->
3: 2->4->
4: 
```

## 4. Implement Depth First Traversal (DFS)

  - In a depth first traversal, the nodes are visited in depth-first manner. DFS traversal can be likened to preorder/inorder/postorder traversals on a binary tree.
  - Unlike trees, graphs do not have a specific starting point. Typically node[0] is treated as the starting point.
  - In order to know if a node is already visited, we mark the node as visited. To this end, a boolean attribute 'visited' is added to the GraphNode class. And whenever we encounter a node during traversal, we set 'visited' to true.
  - Depending on the graph, one may or may not be able to visit all the nodes from a given start node. In order to ensure DFS completes, we iterate through the nodes from 0 to n-1 and try to initiate a dfs traversal provided the nodes are not marked. 

``` java
// GraphNode.java

import java.util.ArrayList;

public class GraphNode {
    int label;
    ArrayList<GraphNode> adjList;
    boolean visited;

    GraphNode(int l) { ... }
    public void print() { ... }

    public void dfs() {

        System.out.print(label + " ");
        visited = true;

        for (int j=0; j<adjList.size(); j++) {
            if ( !adjList.get(j).visited )
                adjList.get(j).dfs();
        }
    }
}
```

``` java
// Graph.java

public class Graph {
    GraphNode[] node;
    int size;

    public Graph(int n) { ... }
    public void addEdge(int from, int to) { ... }
    public void print() { ... }

    public void dfs() {
       for (int i=0; i<size; i++) {
           if ( !node[i].visited )
               node[i].dfs();
       }
    }
}
```

``` java
// GraphDriver.java


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

        g.print();
        g.dfs(); // prints 0 1 4 2 3
    }
}
```

## 5. Implement Breadth First Traversal (BFS)

  - In a breadth first traversal, the nodes are visited in breadth-first manner. BFS traversal can be likened to level-order traversal on a binary tree.
  - As in the case of Binary (Search) Tree, a queue is used for BFS.
  - An utility method reset() is included to reset the 'visited' to false before initiating BFS.
