import apps.TreesAndGraphs.Algos;
import apps.TreesAndGraphs.Node;
import apps.TreesAndGraphs.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BalancedTreeTest {
    @Test
    void test_isBalanced() {
        int[] tab = new int[]{14, 8, 9, 7, 3, 6, 2, 12, 5, 1};
        int[] retMinHeap = Utils.createMinHeap(tab);
        if(retMinHeap != null) {
            Node root = Utils.createTreeFromArray(retMinHeap);
            boolean isBalanced = Algos.isBalanced2(root);
            assertTrue(isBalanced);
        }
    }

    @Test
    void test_isNotBalanced() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.left.left = new Node(5);
        boolean isBalanced = Algos.isBalanced2(root);
        assertFalse(isBalanced);
    }
}
