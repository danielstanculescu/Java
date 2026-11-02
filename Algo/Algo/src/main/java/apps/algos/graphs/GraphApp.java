package apps.algos.graphs;

import utils.Graphs.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GraphApp {
    public static void main() {
        WeightedDirectedAdjacencyList<String> graph = new WeightedDirectedAdjacencyList<>();
        graph.addDirectedEdge("Zürich", "London", 150);
        graph.addDirectedEdge("Zürich", "New York", 1000);
        graph.addUndirectedEdge("Geneva", "Rome", 110);
        graph.printGraph();

        // ------ Undirected Graph Adjacency Matrix

        UndirectedGraphAdjacencyMatrix g = new UndirectedGraphAdjacencyMatrix();
        int[][] tab = {{0,1},{0,2}, {1,2}};
        int[][] adjMatrix = g.createGraph(3, tab);
        String result = g.toStringGraph(3, adjMatrix);
        System.out.println(result);
        ArrayList<ArrayList<Integer>> mat =  g.createGraphSolution2(3, tab);
        g.printAdjacencyMatrix(3, mat);

        // ------ Directed Graph Adjacency Matrix

        DirectedGraphAdjacencyMatrix g2 = new DirectedGraphAdjacencyMatrix();
        adjMatrix = g2.createGraph(3, tab);
        result = g2.toStringGraph(3, adjMatrix);
        System.out.println(result);
        mat =  g2.createGraphSolution2(3, tab);
        g2.printAdjacencyMatrix(3, mat);

        // ------ Directed Graph Adjacency List

        DirectedGraphAdjacencyList g3= new DirectedGraphAdjacencyList();
        int[][] edges = {{3,5},{5,7}, {3,7}};
        g3.createGraph(3, edges);
        g3.createGraphSolution2(edges);
        g3.createGraphSolution3(3, edges);
        g3.createGraphSolution4(3, edges);

        // ------ Undirected Graph Adjacency List

        UndirectedGraphAdjacencyList g4 = new UndirectedGraphAdjacencyList();
        g4.createGraph(3, edges);
        g4.createGraphSolution2(edges);
        g4.createGraphSolution3(3, edges);
        g4.createGraphSolution4(3, edges);
    }
}
