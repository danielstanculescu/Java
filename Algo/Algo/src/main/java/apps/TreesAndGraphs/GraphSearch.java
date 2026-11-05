package apps.TreesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public boolean findConnection (int from, int to) {

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
}
