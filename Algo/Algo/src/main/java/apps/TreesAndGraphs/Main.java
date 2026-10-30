package apps.TreesAndGraphs;

public class Main {

    public static void main() {

        int[] tab = new int[] {14,8,9,7,3,6,2,12,5,1};
        // descending -> Min Heap
        int[] retHeapSortMinHeap = Algos.HeapSortMinHeap(tab);
        System.out.print("Using MinHeap: " + Utils.toString(retHeapSortMinHeap));
        System.out.println();

        // ascending -> Max Heap
        int[] retHeapSortMaxHeap = Algos.HeapSortMaxHeap(tab);
        System.out.print("Using MaxHeap " + Utils.toString(retHeapSortMaxHeap));
    }
}
