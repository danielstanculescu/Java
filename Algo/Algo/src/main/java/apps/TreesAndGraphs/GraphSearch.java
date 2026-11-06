package apps.TreesAndGraphs;

import java.util.*;

// find whether there is a connexion between two nodes
public class GraphSearch {
    private final List<List<Integer>> aL;

    public GraphSearch (int vertices) {
        aL = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            aL.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int target) {
        aL.get(source).add(target);
    }

    public boolean findConnectionBFS (int from, int to) {

        Queue<Integer> queue = new LinkedList<>();

        queue.add(from);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int v : aL.get(current)) {
                if (v == to) {
                    return true;
                }

                queue.add(v);
            }
        }

        return false;
    }

    public boolean findConnectionDFS (int from, int to) {

        if(from == to){
            return true;
        }

        Set<Integer> visited = new HashSet<>();

        return dfsRecursive(from, to, visited);
    }

    private boolean dfsRecursive(int node, int to, Set<Integer> visited){

        boolean found = false;
        visited.add(node);

        for (int v: aL.get(node)) {
            if(!visited.contains(v)){
                if(v == to) {
                    return true;
                }

                found = dfsRecursive(v, to, visited);
                if(found){
                    return true;
                }
            }
        }

        return false;
    }
}
