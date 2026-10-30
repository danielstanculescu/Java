package apps.algos.heap;

import utils.MinHeap;

public class MinHeapApp {

    public static void main (){
        MinHeap heap = new MinHeap();
        heap.add(4);
        heap.add(5);
        heap.add(9);
        heap.add(6);
        heap.add(7);
        heap.add(8);

        heap.pop();

        heap.peek();
    }
}
