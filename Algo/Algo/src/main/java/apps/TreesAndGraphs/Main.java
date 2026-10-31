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

    }
}
