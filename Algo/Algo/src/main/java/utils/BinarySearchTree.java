package utils;

// Recall of the definition
// A binary tree is a recursive data structure where each node can have 2 children at most.
// A common type of binary tree is a binary search tree, in which every node has a value that is greater than or equal
// to the node values in the left sub-tree, and less than or equal to the node values in the right sub-tree.

public class BinarySearchTree {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public void add(int value) {
        this.root = addRecursive(value, this.root);
    }

    private Node addRecursive(int value, Node current) {
        if (current == null) {
            return new Node(value, null, null);
        }

        if (value < current.value) {
            current.left = addRecursive(value, current.left);
        }
        else if (value > current.value) {
            current.right = addRecursive(value, current.right);
        }
        else {
            return current;
        }

        return current;
    }

    public boolean containsNode(int value) {
        return containsRecursive(value, this.root);
    }

    private boolean containsRecursive(int value, Node current) {
        if (current == null) {
            return false;
        }

        if (value < current.value){
            return this.containsRecursive(value, current.left);
        }
        else if(value > current.value){
            return this.containsRecursive(value, current.right);
        }
        else {
            return true;
        }
    }

    public void deleteNode(int value){
        this.root = deleteRecursive(value, this.root);
    }

    private Node deleteRecursive(int value, Node current) {
        if (current == null){
            return null;
        }

        if (current.value == value){
            // delete node (3 options):

            // no children
            if (current.left == null && current.right == null) {
                return null;
            }
            // 2 children
            else if (current.left != null && current.right != null) {
                // 1- (do recursive to) find the smallest children in the right block
                // 2- update the current value with the smallest
                // 3- (do recursive to) remove the smallest node

                    int smallestValue = findSmallestValue(current.right);
                    current.value = smallestValue;
                    current.right = this.deleteRecursive(smallestValue, current.right);
                    return current;
            }
            // 1 child
            else {
                if (current.left != null){
                    return current.left;
                }
                return current.right;
            }
        }

        else if (value < current.value) {
            current.left = deleteRecursive(value, current.left);
            return current; // always the root
        }

        else {
            current.right = deleteRecursive(value, current.right);
            return current; // always the root
        }
    }

    private int findSmallestValue(Node root){
        if (root.left == null){
            return root.value;
        }

        return findSmallestValue(root.left);
    }

    public void dfsLRootRFromRoot() {
        this.dfsLRootR(this.root);
    }

    private void dfsLRootR(Node current) {
        if (current == null){
            return;
        }
        dfsLRootR(current.left);
        System.out.print("root is " + current.value);
        dfsLRootR(current.right);
    }

    public void dfsRootLRFromRoot() {
        this.dfsRootLR(this.root);
    }
    // DFS root left right
    private void dfsRootLR (Node current) {
        if (current == null){
            return;
        }
        System.out.print("root is " + current.value);
        dfsRootLR(current.left);
        dfsRootLR(current.right);
    }

    public void dfsLRRootFromRoot() {
        dfsLRRoot(this.root);
    }
    // DFS left right root
    private void dfsLRRoot(Node current) {
        if (current == null){
            return;
        }
        dfsLRRoot(current.left);
        dfsLRRoot(current.right);
        System.out.print("root is " + current.value);
    }

    public void bfsFromRoot() {
        bfs(this.root);
    }
    private void bfs(Node current) {
        if (current == null) {
            return;
        }

        Queue<Node> q = new Queue<Node>(10);
        q.enqueue(current);

        while (!q.isEmpty()) {
            Node n = q.dequeue();
            if (n != null) {
                if (n.left != null) {
                    q.enqueue(n.left);
                }

                if (n.right != null) {
                    q.enqueue(n.right);
                }
            }
        }
    }
}
