package utils.Graphs;

// edges example : [[0,1],[0,2],[1,2]]
public class UndirectedGraphAdjacencyMatrix {

    public int[][] createGraph(int V, int[][] edges){
        int[][] graph = new int[V][V];

        for (int i=0; i<V; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        return graph;
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
