package apps.TreesAndGraphs;

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
}
