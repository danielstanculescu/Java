import apps.LinkedLists.Algos;
import apps.LinkedLists.LinkedListNode;
import apps.LinkedLists.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KthTest {
    @Test
    void testKth_standard(){
        LinkedListNode head = Utils.createLinkedList(new int[] {1, 2, 3, 4, 5, 6});

        LinkedListNode node = Algos.findKthSolution1(3, head);
        assertNotNull(node, "The list is shorter than expected");
        assertEquals(4, node.data);
    }

    @Test
    void testKth_long(){
        LinkedListNode head = Utils.createLinkedList(new int[] {1, 2});

        LinkedListNode node = Algos.findKthSolution1(3, head);
        assertNull(node, "The node should be null");
    }

    @Test
    void testKth_empty(){
        LinkedListNode head = Utils.createLinkedList(new int[] {});

        LinkedListNode node = Algos.findKthSolution1(3, head);
        assertNull(node, "The node should be null");

        // empty list
        assertDoesNotThrow(() -> Algos.findKthSolution1(3, null));
    }

    @Test
    void testKth_exact(){
        LinkedListNode head = Utils.createLinkedList(new int[] {1, 2, 3});

        LinkedListNode node = Algos.findKthSolution1(3, head);
        assertNotNull(node, "The list is shorter than expected");
        assertEquals(1, node.data);
    }

    @Test
    void testKth3_exact(){
        LinkedListNode head = Utils.createLinkedList(new int[] {1, 2, 3});

        Algos.IntWrapper i = new Algos.IntWrapper();
        LinkedListNode node = Algos.findKthSolution3(3, i, head);
        assertNotNull(node, "The list is shorter than expected");
        assertEquals(1, node.data);
    }
}
