package apps.TreesAndGraphs;

public class Main {

    public static void main() {

        int[] tab = new int[]{14, 8, 9, 7, 3, 6, 2, 12, 5, 1};
        // descending -> Min Heap
        int[] retHeapSortMinHeap = Algos.HeapSortMinHeap(tab);
        System.out.print("Using MinHeap: " + Utils.toString(retHeapSortMinHeap));
        System.out.println();

        // ascending -> Max Heap
        int[] retHeapSortMaxHeap = Algos.HeapSortMaxHeap(tab);
        System.out.print("Using MaxHeap " + Utils.toString(retHeapSortMaxHeap));
        System.out.println();

        int[] retMinHeap = Utils.createMinHeap(tab);
        if (retMinHeap != null) {
            Node root = Utils.createTreeFromArray(retMinHeap);
            int h = Algos.height(root);
            System.out.print("The height of the tree is: " + h);
            System.out.println();
            boolean isBalanced = Algos.isBalanced2(root);
            System.out.print("The tree " + (isBalanced ?  "is" : "is not") + " balanced");
        }

        System.out.println();
        System.out.print("Build an unbalanced tree : 1,2,3,4,5");
        // build an unbalanced tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.left.left = new Node(5);
        System.out.println();
        boolean isBalanced = Algos.isBalanced2(root);
        System.out.print("This tree " + (isBalanced ?  "is" : "is not") + " balanced");

        tab = new int[] {20, 30, 40};
        Utils.createBst(tab);
        System.out.println();

        // ------

        int[][][] graph = new int[][][] {{{1,4}, {2,8}}, {{0,4},{4,6},{2,3}}, {{0,8},{3,2},{1,3}}, {{2,2},{4,10}}, {{1,6},{3,10}}};
        int[] shortestDistance = Algos.algoDijkstra(0, graph);
        System.out.print(Utils.toString(shortestDistance));
        System.out.println();

        int totalVertices = 5;
        DijkstraGraph graph2 = new DijkstraGraph(totalVertices);
        graph2.addEdge(0,1,9);
        graph2.addEdge(0,2,6);
        graph2.addEdge(1,2,2);
        graph2.addEdge(1,3,3);
        graph2.addEdge(2,3,8);
        graph2.addEdge(3,4,1);

        int sourceNode = 0;
        int[] sp = graph2.computeShortestPath(sourceNode);
        graph2.printShortestDistance(sourceNode, sp);

        // ------

        GraphSearch graph3 = new GraphSearch(7);

        graph3.addEdge(0,1);
        graph3.addEdge(0,2);
        graph3.addEdge(1,3);
        graph3.addEdge(1,4);
        graph3.addEdge(1,5);
        graph3.addEdge(2,6);
        graph3.addEdge(3,6);

        boolean found = graph3.findConnectionBFS(0,6);
        System.out.println("Connection found? -> " + found);

        found = graph3.findConnectionDFS(0,6);
        System.out.println("Connection found? -> " + found);

        // -------
        Node tree = Algos.transformMinHeightBST(new int[]{1,2,3,4,5,6});
        Utils.printTree(tree);



    }
}
