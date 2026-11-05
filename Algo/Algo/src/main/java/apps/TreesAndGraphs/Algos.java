package apps.TreesAndGraphs;

import utils.MinHeapGeneric;

import java.util.Comparator;

public class Algos {

    // using MinHeap, descending sort
    public static int[] HeapSortMinHeap (int[] tab) {
        int[] newTab = tab.clone();
        int n = newTab.length;

        newTab = transformMinHeap(newTab);

        for (int index = n-1; index > 0; index--) {
            Utils.swap(newTab, 0, index);
            Utils.heapifyMinHeap(newTab, index, 0);
        }

        return newTab;
    }

    private static int[] transformMinHeap (int[] tab) {
        int[] newTab = tab.clone();
        int n = newTab.length;

        for (int index = (n / 2) - 1; index >= 0; index --) {
            Utils.heapifyMinHeap(newTab, n, index);
        }
        return newTab;
    }

    // using MaxHeap, ascending sort
    public static int[] HeapSortMaxHeap (int[] tab) {
        int[] newTab = tab.clone();
        int n = newTab.length;

        newTab = transformMaxHeap(newTab);

        for (int index = n-1; index > 0; index--) {
            Utils.swap(newTab, 0, index);
            Utils.heapifyMaxHeap(newTab, index, 0);
        }

        return newTab;
    }

    private static int[] transformMaxHeap (int[] tab) {
        int[] newTab = tab.clone();
        int n = newTab.length;

        for (int index = (n / 2) - 1; index >= 0; index --) {
            Utils.heapifyMaxHeap(newTab, n, index);
        }
        return newTab;
    }

    // Check if a tree is balanced (where the level is not more than one)
    public static int height(Node node){
        if(node == null){
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // O(N log N) - not very efficient
    public static boolean isBalanced (Node node) {
        if (node == null) {
            return true;
        }
        int dif = height(node.left) - height(node.right);
        if (Math.abs(dif) > 1) {
            return false;
        }
        else {
            return isBalanced(node.left) && isBalanced(node.right);
        }
    }

    public static int checkHeight(Node root) {
        if (root == null) {
            return 0;
        }

        // check if left is balanced
        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1){
            return -1; // not balanced
        }

        // check if right is balanced
        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) {
            return -1; // not balanced
        }

        // Check if current node is balanced
        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return -1; // Not balanced
        }
        else {
            // return height
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static boolean isBalanced2 (Node root) {
        if ( checkHeight(root) == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // example of input : int[][][] adj = new int[][][] {{{1,4}, {2,8}}, {{0,4},{4,6},{2,3}}, {{0,8},{3,2},{1,3}}, {{2,2},{4,10}}, {{1,6},{3,10}}};
    public static int[] algoDijkstra(int src, int[][][] adj) {
        int[] dist = new int[adj.length];

        dist[0] = src;

        for (int i = 1; i < adj.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        Comparator<GraphNode> nodeComparator = new Comparator<GraphNode>() {
            @Override
            public int compare(GraphNode a, GraphNode b) {
                return a.distance - b.distance;
            }
        };

        MinHeapGeneric<GraphNode> priorityQueue = new MinHeapGeneric<>(nodeComparator);

        GraphNode newNode = new GraphNode(0,0);

        priorityQueue.add(newNode);

        while (!priorityQueue.isEmpty()) {
            GraphNode node = priorityQueue.pop();
            if (node != null) {
                int nodeValue = node.id;

                if(node.distance > dist[nodeValue]) {
                    continue;
                }

                for (int[] neighborV : adj[nodeValue]) {
                    int nodeV = neighborV[0];
                    int distV = neighborV[1];

                    if(distV + dist[nodeValue] < dist[nodeV]) {
                        dist[nodeV] = dist[nodeValue] + distV;
                        newNode = new GraphNode(dist[nodeV], nodeV);
                        priorityQueue.add(newNode);
                    }
                }
            }
        }

        return dist;
    }
}
