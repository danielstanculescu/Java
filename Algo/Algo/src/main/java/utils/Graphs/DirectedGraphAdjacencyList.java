package utils.Graphs;

public class DirectedGraphAdjacencyList {

    // edges example : [[3,5],[5,7], [3,7]]
    // output : [[3,5,7], [5,7]]
    public int[][] createGraph(int V, int[][] edges) {

        int[][] graph = null;

        int index = 0;
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            // find u

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
