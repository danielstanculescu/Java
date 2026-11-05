package utils;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinHeapGeneric<T> {
    private T[] table;
    private int size;
    private Comparator<T> comparator;       // otherwise, use easier T extends Comparable<T>

    public MinHeapGeneric(Comparator<T> comparator) {
        if (comparator == null){
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        this.size = 0;
        this.table = (T[]) new Object[0];
        this.comparator = comparator;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(T value) {
        T[] tab = this.table.clone();
        int N = tab.length;

        if(this.size == N) {
            tab = resize();
        }
        tab[this.size] = value;

        bubbleUp(tab, this.size);

        ++ this.size;

        this.table = tab.clone();
    }

    public T pop() {
        T[] tab = table.clone();

        if (table.length == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        T ret = tab[0];

        swap(tab, 0, this.size - 1);

        tab[this.size - 1] = null;

        --this.size;

        heapify(tab, this.size, 0);

        if (size >0 && ((double) this.size / tab.length) <= 0.5){
            tab = resizeDown(tab);
        }

        this.table = tab.clone();

        return ret;
    }

    public T peek() {
        return this.table[0];
    }

    private void heapify (T[] tab, int n, int i) {
        int smallestIndex = i;
        int c1Index = 2*i+1;
        int c2Index = 2*i+2;

        if (c1Index < n && c1Index < tab.length && comparator.compare(tab[c1Index], tab[smallestIndex]) < 0 ) {
            smallestIndex = c1Index;
        }

        if (c2Index < n && c2Index < tab.length && comparator.compare(tab[c2Index], tab[smallestIndex]) < 0) {
            smallestIndex = c2Index;
        }

        if (smallestIndex != i) {
            swap(tab, smallestIndex, i);
            heapify(tab, n, smallestIndex);
        }
    }

    private T[] resizeDown(T[] tab) {
        T[] newTab;

        newTab = (T[]) new Object[this.size];

        for( int i = 0; i< newTab.length; i++) {
            newTab[i] = tab[i];
        }

        return newTab;
    }

    private T[] resize() {
        T[] newTab;
        T[] tab = this.table.clone();

        if (this.size == 0){
            newTab = (T[]) new Object[1];
        }
        else {
            newTab = (T[]) new Object[this.size * 2];
            System.arraycopy(tab, 0, newTab, 0, tab.length);
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

    private void bubbleUp(T[] tab, int index) {
        if (index == 0) {
            return;
        }

        int indexParent = this.findParent(index);
        T valueIndex = tab[index];
        T valueIndexParent = tab[indexParent];

        if (comparator.compare(valueIndex, valueIndexParent) < 0) {
            swap(tab, index, indexParent);
            bubbleUp(tab, indexParent);
        }
    }

    private void swap(T[] tab, int i, int j) {
        T tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }



}
