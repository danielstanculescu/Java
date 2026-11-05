package utils.Graphs;

import java.util.*;
// For connected graph
public class DepthFirstSearch {

    private Map<String, List<String>> graph = new HashMap<>();

    public DepthFirstSearch() {
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D", "E"));
        graph.put("C", List.of("F"));
        graph.put("D", new ArrayList<>());
        graph.put("E", new ArrayList<>());
        graph.put("F", new ArrayList<>());
    }

    public void runDFS(String startNode) {
        // for unique elements
        Set<String> visited = new HashSet<>();
        System.out.print("DFS Traversal Order: ");
        dfsHelper(startNode, visited);
        System.out.println();
    }

    private void dfsHelper(String currentNode, Set<String> visited) {
        System.out.print(currentNode + " ");
        visited.add(currentNode);

        for (String neighbour : graph.getOrDefault(currentNode, new ArrayList<>())){
            if(!visited.contains(neighbour)){
                dfsHelper(neighbour, visited);
            }
        }
    }
}
