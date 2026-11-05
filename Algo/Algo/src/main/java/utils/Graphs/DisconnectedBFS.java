package utils.Graphs;

import java.util.*;

public class DisconnectedBFS {
    private Map<String, List<String>> graph = new HashMap<>();

    public DisconnectedBFS() {
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", new ArrayList<>());
        graph.put("D", new ArrayList<>());

        graph.put("E", List.of("F"));
        graph.put("F", new ArrayList<>());

        graph.put("G", new ArrayList<>());
    }

    public void bfsDisconnected() {
        System.out.print("Connected BFS Traversal: ");

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        for (String vertex : graph.keySet()){
            if(!visited.contains(vertex)){
                bfs(vertex, visited, queue);
            }
        }
    }
    private void bfs(String startNode, Set<String> visited, Queue<String> queue) {
        /*
        System.out.print("Connected BFS Traversal: ");

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

         */
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
