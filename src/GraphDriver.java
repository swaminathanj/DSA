// Aum Amma

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
        g.dfs();
    }
}
