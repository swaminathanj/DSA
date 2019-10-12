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
        g.addEdge(1,4);
        g.addEdge(2,1);
        g.addEdge(2,4);
        g.addEdge(3,0);
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
        g.addEdge(1,4);
        g.addEdge(2,1);
        g.addEdge(2,4);
        g.addEdge(3,0);
        g.addEdge(3,2);
        g.addEdge(3,4);

        g.print();
    }
}
```

The expected output

```
0: 1->2->
1: 4->
2: 1->4->
3: 0->2->4->
4: 
```

## 4. Implement Depth First Traversal (DFS)

  - In a depth first traversal, the nodes are visited in depth-first manner. DFS traversal can be likened to preorder/inorder/postorder traversals on a binary tree.
  - Unlike trees, graphs do not have a specific starting point. Typically node[0] is treated as the starting point. And if we are not done with visiting all the nodes from node[0], we will restart from any of the remaining unvisited node.
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
        g.addEdge(1,4);
        g.addEdge(2,1);
        g.addEdge(2,4);
        g.addEdge(3,0);
        g.addEdge(3,2);
        g.addEdge(3,4);

        g.print();
        g.dfs(); // prints 0 1 4 2 3
    }
}
```

## 5. Include pre- and post-ordering information in DFS implementation

The pre- and post-ordering information pertains to recording the first and last visit to a node respectively. 
  - Whenever we visit a node for the first time, it is recorded in preorder. 
  - And whenever we are finished processing the adjacency list of a node, it is recorded in postorder.

This information is often useful to distinguish between two traversals that produce the same DFS visit order. It is possible to get the same DFS visit order if the graphs are slightly different or the adjacency lists are rearranged. In such circumstances, preorder and postorder information will be helpful.

Implementation changes include
 - dfs() method of GraphNode takes a parameter 'visitCount' that is incremented whenever a dfs() is invoked.
 - dfs() method of GraphNode returns the changed 'visitCount' as the traversal progresses.
 - Whenever a node is first visited, its preorder is set.
 - Whenever a node is exited, its postorder is set.
 - A new method printOrder() to print the pre- and post-orders of every node is added.
 
 ``` java
 // GraphNode.java

import java.util.ArrayList;

public class GraphNode {
    int label;
    ArrayList<GraphNode> adjList;
    boolean visited;
    int preorder;  // Records first visit to this node
    int postorder; // Records final exit from this node

    GraphNode(int l) { ... }
    public void print() { ... }

    /* Modified to include updates of preorder and postorder */
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
}
 ```
 
 ``` java
 // GraphDriver.java

public class GraphDriver {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,4);
        g.addEdge(2,1);
        g.addEdge(2,4);
        g.addEdge(3,0);
        g.addEdge(3,2);
        g.addEdge(3,4);

        g.print();
        g.dfs(); // prints 0 1 4 2 3
        g.printOrder(); // prints 0 (1,8) 1 (2,5) 2 (6,7) 3 (9,10) 4 (3,4)
    }
}
 ```

## 6. Implement Breadth First Traversal (BFS)

  - In a breadth first traversal, the nodes are visited in breadth-first manner. BFS traversal can be likened to level-order traversal on a binary tree.
  - As in the case of Binary (Search) Tree, a queue is used for BFS.
  - An utility method reset() is included to reset the 'visited' to false before initiating BFS.
  
  ``` java
// GraphNode.java

import java.util.ArrayDeque;
import java.util.ArrayList;

public class GraphNode {
    int label;
    ArrayList<GraphNode> adjList;
    boolean visited;
    int preorder;
    int postorder;

    GraphNode(int l) { ... }
    public void print() { ... }
    public int dfs(int visitCount) { ... }
    
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
```

``` java
// Graph.java

import java.util.ArrayDeque;

public class Graph {
    GraphNode[] node;
    int size;

    public Graph(int n) { ... }
    public void addEdge(int from, int to) { ... }
    public void print() { ... }
    public void dfs() { ... }
    public void printOrder() { ... }

    public void reset() {
        for (int i=0; i<size; i++)
            node[i].visited = false;
            node[i].preorder = 0;
            node[i].postorder = 0;
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
```

``` java
// GraphDriver.java

public class GraphDriver {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,4);
        g.addEdge(2,1);
        g.addEdge(2,4);
        g.addEdge(3,0);
        g.addEdge(3,2);
        g.addEdge(3,4);

        g.print();
        g.dfs(); // prints 0 1 4 2 3
        g.printOrder();

        g.bfs(); // prints 0 1 2 4 3
    }
}
```
