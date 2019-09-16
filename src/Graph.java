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

    public void print() {
        for (int i=0; i<size; i++) {
            System.out.print(i + ": ");
            node[i].print(); 
            System.out.println();
        }
    }

    public void dfs() {
       for (int i=0; i<size; i++) {
           if ( !node[i].visited )
               node[i].dfs();
       }
    }
}

