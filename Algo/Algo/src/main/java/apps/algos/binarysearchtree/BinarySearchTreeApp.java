package apps.algos.binarysearchtree;

import utils.BinarySearchTree;

public class BinarySearchTreeApp {

    static void main() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(30);
        bst.add(10);
        bst.add(50);
        bst.add(5);
        bst.add(16);
        bst.add(48);
        bst.add(52);
        bst.add(49);
        bst.add(51);
        bst.add(80);
        bst.add(53);

        boolean containsNode = bst.containsNode(10);
        bst.deleteNode(10);
        containsNode = bst.containsNode(10);
        bst.bfsFromRoot();

        System.out.print("Contains node " + containsNode);
    }
}
