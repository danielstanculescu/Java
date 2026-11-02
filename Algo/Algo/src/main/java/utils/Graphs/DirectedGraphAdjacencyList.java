package utils.Graphs;

import java.util.ArrayList;
import java.util.HashMap;

public class DirectedGraphAdjacencyList {

    // edges example : [[3,5],[5,7], [3,7]]
    // output : [[3,5,7,0], [5,7,0,0]]
    // with resize
    public int[][] createGraph(int V, int[][] edges) {

        int[][] graph = null;

        int index = 0;
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            if(graph == null) {
                graph = new int[1][];

                int[] column = null;
                column = resize(column);
                column[0] = u;
                column[1] = v;
                // add some space to the column,
                // for later adding values
                column = resize(column);
                graph[index] = column;
            }
            else {
                boolean found = false;

                for (int j = 0; j < graph.length; j++) {
                    if (graph[j][0] == u) {

                        for (int k = 1; k < graph[j].length; k++) {

                            if(graph[j][k] == 0){
                                graph[j][k] = v;
                                found = true;
                                break;
                            }
                        }
                    }
                }
                if (!found) {
                    // create new line
                    graph = resizeMatrix(graph);

                    int[] column = null;
                    column = resize(column);
                    column[0] = u;
                    column[1] = v;
                    column = resize(column);

                    graph[index] = column;
                }
            }

            index++;
        }

        return graph;
    }

    // edges example : [[3,5],[5,7], [3,7]]
    // output : HashMap<Integer,ArrayList<Integer>>: 3: [5,7]; 5: [7]
    public HashMap<Integer, ArrayList<Integer>> createGraphSolution2(int[][] edges) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            map.putIfAbsent(u, new ArrayList<>());

            map.get(u).add(v);
        }

        return map;
    }

    // edges example : [[3,5],[5,7], [3,7]]
    // output : ArrayList<ArrayList<Integer>>: [[3,5,7], [5,7]
    public ArrayList<ArrayList<Integer>> createGraphSolution3 (int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int[] edge : edges) {
            int uRaw = edge[0];
            int vRaw = edge[1];

            int uMapped = -1;
            for (int i = 0; i < adj.size(); i++) {
                if (adj.get(i).get(0) == uRaw) {
                    uMapped = i;
                    break;
                }
            }

            if (uMapped == -1) {
                uMapped = adj.size();
                adj.add(new ArrayList<>());
                adj.get(uMapped).add(uRaw);
            }

            adj.get(uMapped).add(vRaw);
        }

        return adj;
    }

    // edges example : [[3,5],[5,7], [3,7]]
    // output : [[3,5,7], [5,7]
    public int[][] createGraphSolution4(int V, int[][] edges) {
        int[] uniqueNodes = new int[V];
        int[] degrees = new int[V];
        int uniqueCount = 0;

        for (int[] edge : edges) {
            int uRaw = edge[0];

            int uMapped = -1;
            for (int i = 0; i < uniqueCount; i++) {
                if (uniqueNodes[i] == uRaw) { uMapped = i; break; }
            }
            if (uMapped == -1) {
                uMapped = uniqueCount;
                uniqueNodes[uniqueCount++] = uRaw;
            }
            degrees[uMapped]++;
        }

        int[][] adj = new int[uniqueCount][];
        int[] nextInsertIndex = new int[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            adj[i] = new int[1 + degrees[i]];
            adj[i][0] = uniqueNodes[i];
            nextInsertIndex[i] = 1;
        }

        for (int[] edge : edges) {
            int uRaw = edge[0];
            int vRaw = edge[1];

            int uMapped = -1;
            for (int i = 0; i < uniqueCount; i++) {
                if (adj[i][0] == uRaw) {
                    uMapped = i;
                    break;
                }
            }

            adj[uMapped][nextInsertIndex[uMapped]++] = vRaw;
        }

        return adj;
    }

    private int[] resize(int[] tab) {

        if(tab == null){
            return new int[2];
        }

        int[] newTab = new int[tab.length * 2];
        System.arraycopy(tab, 0, newTab, 0, tab.length);

        return newTab;
    }

    private int[][] resizeMatrix(int[][] graph){

        if(graph == null){
            return new int[1][];
        }

        int[][] newGraph = new int[graph.length * 2][];
        System.arraycopy(graph, 0, newGraph, 0, graph.length);

        return newGraph;
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
}
