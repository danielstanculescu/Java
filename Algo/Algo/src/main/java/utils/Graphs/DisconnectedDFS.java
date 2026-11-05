package utils.Graphs;

import java.util.*;

public class DisconnectedDFS {

    private Map<String, List<String>> graph = new HashMap<>();

    public DisconnectedDFS() {
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", new ArrayList<>());
        graph.put("D", new ArrayList<>());

        graph.put("E", List.of("F"));
        graph.put("F", new ArrayList<>());

        graph.put("G", new ArrayList<>());
    }

    public void dfsDisconnected() {
        Set<String> visited = new HashSet<>();
        System.out.print("Disconnected DFS Traversal: ");
        for (String vertex : graph.keySet()) {
            if(!visited.contains(vertex)){
                dfsHelper(vertex, visited);
            }
        }
        System.out.println();
    }

    private void dfsHelper(String currentNode, Set<String> visited) {
        System.out.print(currentNode + " ");
        visited.add(currentNode);

        for(String neighbour : graph.getOrDefault(currentNode, new ArrayList<>())){
            if(!visited.contains(neighbour))
                dfsHelper(neighbour, visited);
        }
    }
}
