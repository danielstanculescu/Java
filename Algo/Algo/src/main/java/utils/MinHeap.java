package utils;

/*-----
Quick recall
A heap is a binary tree-based data structure that adheres to a heap property.
In a heap, every parent node has a specific relationship with its children: in a max-heap,
each parent is greater than or equal to its children, while in a min-heap, each parent is less than or equal to its children.

------*/

public class MinHeap {

    private int[] table;
    private int size = 0;

    public MinHeap() {
        this.table = new int[0];
    }

    /*
    Quick recall
    The insertion operation in a min-heap involves the following steps:

    Add the new element to the end of the heap, in the next available position in the last level of the tree.
    Compare the new element with its parent. If the parent is greater than the new element, swap them.
    Repeat step 2 until the parent is smaller than or equal to the new element, or until the new element reaches the root of the tree.
    */
    public void add(int value) {

        int[] tab  = this.table.clone();
        int N = tab.length;

        if (this.size == N){
            tab = this.resize();
        }

        tab[this.size] = value;

        bubbleUp(tab, this.size);

        ++this.size;

        this.table = tab.clone();
    }

    /* Quick recall:
    Removing the smallest element (the root) from a Min-Heap involves the following steps:

    Replace the root (or the element to be deleted) with the last element in the heap.
    Remove the last element from the heap, since it has been moved to the root.
    Heapify-down: The element now at the root may violate the Min-Heap property,
        so perform heapify starting from the root to restore the heap property.
    */

    public int pop() {
        int []tab = table.clone();

        if (table.length == 0) {
            return -1;
        }

        int ret = tab[0];

        swap(tab, 0, this.size - 1);

        tab[this.size - 1] = -1;

        --this.size;

        heapify(tab, this.size, 0);

        if (this.size == 0 || (((double) this.size / tab.length) <= 0.5)){
            tab = resizeDown();
        }

        this.table = tab.clone();

        return ret;
    }

    public int peek() {
        return this.table[0];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void heapify (int[] tab, int n, int i) {
        int smallestIndex = i;
        int c1Index = 2*i+1;
        int c2Index = 2*i+2;

        int indexValue = tab[smallestIndex];

        if (c1Index < n && c1Index < tab.length && tab[c1Index] < indexValue ) {
            smallestIndex = c1Index;
        }

        if (c2Index < n && c2Index < tab.length && tab[c2Index] < smallestIndex) {
            smallestIndex = c2Index;
        }

        if (smallestIndex != i) {
            swap(tab, smallestIndex, i);
            heapify(tab, n, smallestIndex);
        }
    }

    private int[] resizeDown() {
        int[] newTab;
        int[] tab = this.table.clone();

        newTab = new int[this.size];

        System.arraycopy(tab, 0, newTab, 0, tab.length);

        this.table = newTab.clone();

        return newTab;
    }

    private int[] resize() {
        int[] newTab;
        int[] tab = this.table.clone();

        if (this.size == 0){
            newTab = new int[1];
        }
        else {
            newTab = new int[this.size * 2];
            System.arraycopy(tab, 0, newTab, 0, tab.length);
            /*
            for (int i = 0; i < tab.length; i++) {
                newTab[i] = tab[i];
            }
             */
        }

        this.table = newTab.clone();

        return newTab;
    }

    private int findParent (int index) {
        if (index % 2 == 1) {
            return (index - 1) / 2;
        }
        else
            return (index - 2) / 2;
    }
    private void bubbleUp(int[] tab, int index) {
        if (index == 0) {
            return;
        }

        int indexParent = this.findParent(index);
        int valueIndex = tab[index];
        int valueIndexParent = tab[indexParent];

        if (valueIndex < valueIndexParent) {
            swap(tab, index, indexParent);
            bubbleUp(tab, indexParent);
        }
    }

    private void swap(int[] tab, int i, int j) {
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }


}
