package apps.TreesAndGraphs;

public class Utils {

    public static void heapifyMinHeap (int[] tab, int n, int i) {
        int smallestIndex = i;
        int c1Index = 2*i+1;
        int c2Index = 2*i+2;

        if (c1Index < n && c1Index < tab.length && tab[c1Index] < tab[smallestIndex] ) {
            smallestIndex = c1Index;
        }

        if (c2Index < n && c2Index < tab.length && tab[c2Index] < tab[smallestIndex]) {
            smallestIndex = c2Index;
        }

        if (smallestIndex != i) {
            swap(tab, smallestIndex, i);
            heapifyMinHeap(tab, n, smallestIndex);
        }
    }

    public static void heapifyMaxHeap (int[] tab, int n, int i) {
        int smallestIndex = i;
        int c1Index = 2*i+1;
        int c2Index = 2*i+2;

        if (c1Index < n && c1Index < tab.length && tab[c1Index] > tab[smallestIndex] ) {
            smallestIndex = c1Index;
        }

        if (c2Index < n && c2Index < tab.length && tab[c2Index] > tab[smallestIndex]) {
            smallestIndex = c2Index;
        }

        if (smallestIndex != i) {
            swap(tab, smallestIndex, i);
            heapifyMaxHeap(tab, n, smallestIndex);
        }
    }

    public static void swap(int[] tab, int i, int j) {
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    public static String toString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int index=0; index<arr.length; index++) {
            sb.append(arr[index]);
            if(index < arr.length - 1){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}

