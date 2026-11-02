package utils.Graphs;

import java.util.ArrayList;
import java.util.Collections;

public class DirectedGraphAdjacencyMatrix {

    // edges example : [[0,1],[0,2],[1,2]]
    public int[][] createGraph(int V, int[][] edges) {
        int[][] graph = new int[V][V];

        for (int i=0; i<edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            graph[u][v] = 1;
        }

        return graph;
    }

    // edges example : [[0,1],[0,2],[1,2]]
    public ArrayList<ArrayList<Integer>> createGraphSolution2(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();

        for (int i=0; i<V; i++){
            ArrayList<Integer> row = new ArrayList<>(Collections.nCopies(V, 0));
            mat.add(row);
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            mat.get(u).set(v, 1);
        }

        return mat;
    }

    public String toStringGraph(int V, int[][] edges) {

        String ret  = "";
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                ret += edges[i][j] + " ";
            }
            ret += "; ";
        }

        return ret;
    }

    public void printAdjacencyMatrix (int V, ArrayList<ArrayList<Integer>> mat) {
        System.out.println("Adjacency List Representation");
        for (int i = 0; i < V; i++) {
            ArrayList<Integer> row =  mat.get(i);
            for (int j = 0; j < V; j++) {
                int element = row.get(j);
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
