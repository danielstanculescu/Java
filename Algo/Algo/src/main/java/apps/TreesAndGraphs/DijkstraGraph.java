package apps.TreesAndGraphs;

import java.util.*;

public class DijkstraGraph {

    public static class Edge {
        int target;
        int weight;

        public Edge (int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static class NodeDistance {
        int node;
        int distance;

        public NodeDistance (int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private final int vertices;
    private final List<List<Edge>> adjList;

    public DijkstraGraph(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>(vertices);

        for (int i=0; i<vertices; i++) {
            this.adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int target, int weight) {
        adjList.get(source).add(new Edge(target, weight));
    }

    public int[] computeShortestPath(int startVertex) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        Queue<NodeDistance> minHeap = new PriorityQueue<>(Comparator.comparingInt(nd -> nd.distance));
        minHeap.add(new NodeDistance(startVertex, 0));

        while (!minHeap.isEmpty()) {
            NodeDistance current = minHeap.poll();
            int u = current.node;

            if(current.distance > distances[u]){
                continue;
            }

            for (Edge edge : adjList.get(u)) {
                int v = edge.target;
                int weight = edge.weight;

                int newDistance = weight + distances[u];
                if(newDistance < distances[v]) {
                    distances[v] = newDistance;
                    minHeap.add(new NodeDistance(v, distances[v]));
                }
            }
        }

        return distances;
    }

    public void printShortestDistance(int sourceNode, int[] graph) {
        System.out.println("Shortest distances from source node " + sourceNode + ":");
        for (int i=0; i< graph.length; i++) {
            String distStr = (graph[i] == Integer.MAX_VALUE) ? "INF" : String.valueOf(graph[i]);
            System.out.println("To node " + i + " -> Minimum cost: " + distStr);
        }
    }
}
