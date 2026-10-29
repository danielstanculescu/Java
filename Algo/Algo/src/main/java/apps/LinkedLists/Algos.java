package apps.LinkedLists;
// Write code to remove duplicates from an unsorted linked list
// Follow up: what if a temporary buffer not available?

import java.util.HashSet;

public class Algos {

    public static void deleteDupsWithBuffer(LinkedListNode node){
        // Just for testing my implementation of LinkedList
        // Switch to HashSet for Complexity reasons: from  O(N) - LinkedList to O(1) - HashSet
        //LinkedList<Integer> list = new LinkedList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        LinkedListNode previous = null;

        while (node != null){
            if(set.contains(node.data)){
                if(previous != null){
                    previous.next = node.next;
                }
            }
            else {
                set.add(node.data);
                previous = node;
            }
            node = node.next;
        }
    }

    public static void deleteDupsWithoutBuffer(LinkedListNode node){

        while (node != null){
            LinkedListNode current = node.next;
            LinkedListNode prev = node;

            while (current != null){
                if(current.data == node.data){
                    prev.next = current.next;
                }
                else{
                    prev = current;
                }
                current = current.next;
            }
            node = node.next;
        }
    }

    // Find the k-th to last element in a single linked list
    public static LinkedListNode findKth (int k, LinkedListNode node){

        LinkedListNode p1 = node;
        LinkedListNode p2 = node;

        for (int i = 0; i<k-1; i++) {
            if (p2 == null) {
                return null;
            }
                p2 = p2.next;
        }

        if(p2 == null){
            return null;
        }

        while (p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }
}
