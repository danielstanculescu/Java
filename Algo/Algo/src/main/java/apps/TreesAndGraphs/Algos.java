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
}
