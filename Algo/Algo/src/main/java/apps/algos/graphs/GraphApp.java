package apps.algos.graphs;

import utils.Graphs.DirectedGraphAdjacencyList;
import utils.Graphs.UndirectedGraphAdjacencyList;
import utils.Graphs.UndirectedGraphAdjacencyMatrix;
import utils.Graphs.WeightedDirectedAdjacencyList;

public class GraphApp {
    public static void main() {
        WeightedDirectedAdjacencyList<String> graph = new WeightedDirectedAdjacencyList<>();
        graph.addDirectedEdge("Zürich", "London", 150);
        graph.addDirectedEdge("Zürich", "New York", 1000);
        graph.addUndirectedEdge("Geneva", "Rome", 110);
        graph.printGraph();

        // ------

        UndirectedGraphAdjacencyMatrix g = new UndirectedGraphAdjacencyMatrix();
        int[][] tab = {{0,1},{0,2}, {1,2}};
        int[][] adjMatrix = g.createGraph(3, tab);
        String result = g.toStringGraph(3, adjMatrix);
        System.out.println(result);


        // ------

        DirectedGraphAdjacencyList g2 = new DirectedGraphAdjacencyList();
        int[][] edges = {{3,5},{5,7}, {3,7}};
        g2.createGraph(3, edges);

        // ------

        UndirectedGraphAdjacencyList g3 = new UndirectedGraphAdjacencyList();
        g3.createGraph(3, edges);
    }




}
