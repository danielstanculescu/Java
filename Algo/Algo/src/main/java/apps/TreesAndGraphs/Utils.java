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


    public static Node createBst(int[] table){
        if(table == null || table.length == 0){
            return null;
        }

        Node root = new Node(table[0]);

        for( int i=1; i<table.length; i++){
            addNode(root, table[i]);
        }

        return root;
    }

    private static Node addNode(Node node, int value){

        if(node == null){
            return new Node(value);
        }

        if(value < node.data){
            node.left = addNode(node.left, value);
        }
        else if (value > node.data){
            node.right = addNode(node.right, value);
        }
        else{
            return node;
        }

        return node;
    }



    public static int[] createMinHeap(int[] table){

        int[] tab = table.clone();
        if(tab.length == 0)
            return null;

        for (int i=1; i< tab.length; i++) {
            bubbleUp(i, tab);
        }

        return tab;
    }

    private static void bubbleUp(int index, int[] table){
        if (index == 0) {
            return;
        }

        int indexParent = Math.round((float)index/2) - 1;
        if(table[index] < table[indexParent]){
            swap(table, index, indexParent);
            bubbleUp(indexParent, table);
        }
    }

    public static Node createTreeFromArray(int[] table) {
        if (table == null ||table.length == 0) {
            return null;
        }

        Node root = new Node(table[0]);

        recursiveCreateTree(root, table, 0);

        return root;
    }

    private static void recursiveCreateTree(Node node, int[] tab, int index) {

        if (2*index+1 < tab.length) {
            node.left = new Node(tab[2*index+1]);
            recursiveCreateTree(node.left, tab, 2*index+1);
        }

        if (2*index+2 < tab.length) {
            node.right = new Node(tab[2*index+2]);
            recursiveCreateTree(node.right, tab, 2*index+2);
        }
    }

    public static Node createTreeFromArray2 (int[] table){
        if (table == null ||table.length == 0) {
            return null;
        }

        return buildTree(table, 0);
    }

    private static Node buildTree(int[] arr, int index) {
        if (index >= arr.length) {
            return null;
        }

        Node root = new Node(arr[index]);

        root.left = buildTree(arr, 2*index+1);
        root.right = buildTree(arr, 2*index+2);

        return root;
    }
}

