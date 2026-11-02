package utils.Graphs;

import java.util.ArrayList;
import java.util.HashMap;

public class UndirectedGraphAdjacencyList {

    // edges example : [[3,5],[5,7], [3,7]]
    // output : [[3,5,7,0], [5,3,7,0], [7,5,3,0]]
    // algo with resize
    public int[][] createGraph(int V, int[][] edges) {

        int[][] graph = null;

        int index = 0;
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            // find u

            if(graph == null) {
                graph = new int[2][];

                int[] column = null;
                column = resize(column);
                column[0] = u;
                column[1] = v;
                // add some space to the column,
                // for later adding values
                column = resize(column);
                graph[index] = column;

                int[] column2 = null;
                column2 = resize(column2);
                column2[0] = v;
                column2[1] = u;
                // add some space to the column,
                // for later adding values
                column2 = resize(column2);
                graph[++index] = column2;


            }
            else {
                boolean found = false;

                for (int j = 0; j < graph.length; j++) {
                    if (graph[j][0] == u) {

                        for (int k = 1; k < graph[j].length; k++) {

                            if(graph[j][k] == 0){
                                graph[j][k] = v;
                                found = true;

                                // check if v exists in the graph
                                boolean exists = false;
                                //boolean found2 = false;
                                for (int l = 0; l < graph.length; l++) {
                                    if (graph[l][0] == v) {
                                        for (int m = 1; m < graph[l].length; m++) {
                                            if(graph[l][m] == 0){
                                                graph[l][m] = u;
                                  //              found2 = true;
                                                break;
                                            }
                                        }
                                        exists = true;
                                        break;
                                    }
                                }

                                if(!exists){
                                    // create new line
                                    graph = resizeMatrix(graph);

                                    int[] column = null;
                                    column = resize(column);
                                    column[0] = v;
                                    column[1] = u;
                                    column = resize(column);

                                    graph[index] = column;
                                }

                                break;
                            }
                        }

                        if(found)
                            break;
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
    // output : HashMap<Integer,ArrayList<Integer>>: 3: [5,7]; 5: [3,7]; 7: [5,3]
    public HashMap<Integer, ArrayList<Integer>> createGraphSolution2(int[][] edges) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());

            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }

    // edges example : [[3,5],[5,7], [3,7]]
    // output : ArrayList<ArrayList<Integer>>: [[3,5,7], [5,3,7], [7,5,3]]
    public ArrayList<ArrayList<Integer>> createGraphSolution3 (int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        int uniqueCount = 0;

        for (int[] edge : edges) {
            int uRaw = edge[0];
            int vRaw = edge[1];

            int uMapped = -1;
            for (int i = 0; i < uniqueCount; i++) {
                if (adj.get(i).get(0) == uRaw) {
                    uMapped = i;
                    break;
                }
            }
            if (uMapped == -1) {
                uMapped = uniqueCount++;
                adj.get(uMapped).add(uRaw);
            }

            int vMapped = -1;
            for (int i = 0; i < uniqueCount; i++) {
                if (adj.get(i).get(0) == vRaw) {
                    vMapped = i;
                    break;
                }
            }
            if (vMapped == -1) {
                vMapped = uniqueCount++;
                adj.get(vMapped).add(vRaw);
            }

            adj.get(uMapped).add(vRaw);
            adj.get(vMapped).add(uRaw);
        }

        return adj;
    }

    public int[][] createGraphSolution4(int V, int[][] edges) {
        int[] uniqueNodes = new int[V];
        int[] degrees = new int[V];
        int uniqueCount = 0;

        for (int[] edge : edges) {
            int uRaw = edge[0];
            int vRaw = edge[1];

            int uMapped = -1;
            for (int i = 0; i < uniqueCount; i++) {
                if (uniqueNodes[i] == uRaw) { uMapped = i; break; }
            }
            if (uMapped == -1) {
                uMapped = uniqueCount;
                uniqueNodes[uniqueCount++] = uRaw;
            }
            degrees[uMapped]++;

            int vMapped = -1;
            for (int i = 0; i < uniqueCount; i++) {
                if (uniqueNodes[i] == vRaw) { vMapped = i; break; }
            }
            if (vMapped == -1) {
                vMapped = uniqueCount;
                uniqueNodes[uniqueCount++] = vRaw;
            }
            degrees[vMapped]++;
        }

        int[][] adj = new int[V][];
        for (int i = 0; i < V; i++) {
            adj[i] = new int[1 + degrees[i]];
            adj[i][0] = uniqueNodes[i];
        }

        int[] nextInsertIndex = new int[V];
        for (int i = 0; i < V; i++) {
            nextInsertIndex[i] = 1;
        }

        for (int[] edge : edges) {
            int uRaw = edge[0];
            int vRaw = edge[1];

            int uMapped = -1, vMapped = -1;
            for (int i = 0; i < V; i++) {
                if (adj[i][0] == uRaw) uMapped = i;
                if (adj[i][0] == vRaw) vMapped = i;
            }

            adj[uMapped][nextInsertIndex[uMapped]++] = vRaw;
            adj[vMapped][nextInsertIndex[vMapped]++] = uRaw;
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

