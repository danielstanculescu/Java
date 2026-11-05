package utils.Graphs;

import java.util.*;

// For connected graph
public class BreadthFirstSearch {
    private Map<String, List<String>> graph = new HashMap<>();

    public BreadthFirstSearch() {
        // A fully connected graph (every node is reachable from 'A')
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D", "E"));
        graph.put("C", List.of("F"));
        graph.put("D", new ArrayList<>());
        graph.put("E", new ArrayList<>());
        graph.put("F", new ArrayList<>());
    }

    public void runConnectedBFS(String startNode) {
        System.out.print("Connected BFS Traversal: ");

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        visited.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            String currentNode = queue.poll();
            System.out.print(currentNode + " ");

            for (String neighbor : graph.getOrDefault(currentNode, new ArrayList<>())) {
                if(!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }
}
