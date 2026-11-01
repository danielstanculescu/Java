package utils.Graphs;

import java.util.*;

public class WeightedDirectedAdjacencyList<T> {

    public static class Edge<T> {
        T target;
        int weight;

        public Edge(T target, int weight) {
            this.target = target;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return " -> " + target + " (w:" + weight + ")";
        }
    }


    private final Map<T, List<Edge<T>>> adjacencyList;

    public WeightedDirectedAdjacencyList() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addDirectedEdge(T source, T target, int weight) {
        addVertex(source);
        addVertex(target);

        adjacencyList.get(source).add(new Edge<>(target, weight));
    }

    public void addUndirectedEdge(T source, T target, int weight) {
        addDirectedEdge(source, target, weight);
        addDirectedEdge(target, source, weight);
    }

    public List<Edge<T>> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    public Set<T> getVertices() {
        return adjacencyList.keySet();
    }

    public void printGraph() {
        for (Map.Entry<T, List<Edge<T>>> entry : adjacencyList.entrySet()) {
            System.out.print("Vertex " + entry.getKey() + ":");
            for (Edge<T> edge : entry.getValue()) {
                System.out.print(edge);
            }
            System.out.println();
        }
    }
}

