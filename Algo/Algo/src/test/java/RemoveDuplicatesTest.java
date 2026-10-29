import apps.LinkedLists.LinkedListNode;
import apps.LinkedLists.Algos;

import apps.LinkedLists.Utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RemoveDuplicatesTest {

    private void assertListEquals(int[] expected, LinkedListNode head){
        LinkedListNode current = head;
        for(int val: expected){
            assertNotNull(current, "List is shorter than expected");
            assertEquals(val, current.data);
            current = current.next;
        }

        assertNull(current, "List is longer than expected");
    }

    @Test
    void testDeleteDupsWithBuffer_noDuplicates(){
        LinkedListNode head = Utils.createLinkedList(new int[] {1, 2, 3, 4});

        Algos.deleteDupsWithBuffer(head);

        assertListEquals(new int[] {1, 2, 3, 4}, head);
    }

    @Test
    void testDeleteDupsWithBuffer_withDuplicates(){
        LinkedListNode head = Utils.createLinkedList(new int[] {1, 3, 1, 5, 3, 2});

        Algos.deleteDupsWithBuffer(head);

        assertListEquals(new int[] {1, 3, 5, 2}, head);
    }

    @Test
    void testDeleteDupsWithBuffer_consecutiveDuplicates(){
        LinkedListNode head = Utils.createLinkedList(new int[] {2, 2, 2, 3, 3});

        Algos.deleteDupsWithBuffer(head);

        assertListEquals(new int[] {2, 3}, head);
    }

    @Test
    void testDeleteDupsWithBuffer_allIdentical(){
        LinkedListNode head = Utils.createLinkedList(new int[] {5, 5, 5, 5});

        Algos.deleteDupsWithBuffer(head);

        assertListEquals(new int[] {5}, head);
    }

    @Test
    void testDeleteDupsWithBuffer_emptyAndSingleElement(){
        // empty list
        assertDoesNotThrow(() -> Algos.deleteDupsWithBuffer(null));

        // single element
        LinkedListNode head = Utils.createLinkedList(new int[] {55});

        Algos.deleteDupsWithBuffer(head);

        assertListEquals(new int[] {55}, head);
    }

    @Test
    void testDeleteDupsWithoutBuffer_noDuplicates(){
        LinkedListNode head = Utils.createLinkedList(new int[] {1, 2, 3, 4});

        Algos.deleteDupsWithoutBuffer(head);

        assertListEquals(new int[] {1, 2, 3, 4}, head);
    }

    @Test
    void testDeleteDupsWithoutBuffer_withDuplicates(){
        LinkedListNode head = Utils.createLinkedList(new int[] {1, 3, 1, 5, 3, 2});

        Algos.deleteDupsWithoutBuffer(head);

        assertListEquals(new int[] {1, 3, 5, 2}, head);
    }

    @Test
    void testDeleteDupsWithoutBuffer_consecutiveDuplicates(){
        LinkedListNode head = Utils.createLinkedList(new int[] {2, 2, 2, 3, 3});

        Algos.deleteDupsWithoutBuffer(head);

        assertListEquals(new int[] {2, 3}, head);
    }

    @Test
    void testDeleteDupsWithoutBuffer_allIdentical(){
        LinkedListNode head = Utils.createLinkedList(new int[] {5, 5, 5, 5});

        Algos.deleteDupsWithoutBuffer(head);

        assertListEquals(new int[] {5}, head);
    }

    @Test
    void testDeleteDupsWithoutBuffer_emptyAndSingleElement(){
        // empty list
        assertDoesNotThrow(() -> Algos.deleteDupsWithoutBuffer(null));

        // single element
        LinkedListNode head = Utils.createLinkedList(new int[] {55});

        Algos.deleteDupsWithoutBuffer(head);

        assertListEquals(new int[] {55}, head);
    }
}
