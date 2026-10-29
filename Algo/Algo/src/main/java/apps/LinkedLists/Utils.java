package apps.LinkedLists;

import java.util.ArrayList;

public class Utils {

    public static LinkedListNode createLinkedList(int[] table){
        if(table == null || table.length == 0)
            return null;

        LinkedListNode head = new LinkedListNode(table[0]);
        LinkedListNode current = head;

        for (int i=1; i<table.length ; i++) {
            current.next = new LinkedListNode(table[i]);
            current = current.next;
        }

        return head;
    }

    private static int[] toArray (LinkedListNode head) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        LinkedListNode current = head;

        while(current != null){
            arr.add(current.data);
            current = current.next;
        }

        return arr.stream().mapToInt(Integer::intValue).toArray();
    }

    private static String toStringFromArray(int[] arr){
        StringBuilder s = new StringBuilder();

        for (int i=0; i<arr.length; i++) {
            s.append(arr[i]).append(" ");
        }

        return s.toString();
    }

    public static String deleteDupsWithMultipleDuplicates(){
        int[] arr = {1, 3, 1, 5, 3, 2};
        LinkedListNode head = createLinkedList(arr);
        Algos.deleteDupsWithBuffer(head);
        int[] result = toArray(head);

        return toStringFromArray(result);
    }

    public static String findKth(){
        int kTh = 3;
        int[] test = new int[]{1,2,3,4,5,6,7,8,9,10};
        LinkedListNode head = createLinkedList(test);
        LinkedListNode node  = Algos.findKth(kTh, head);
        if(node != null)
            return "The " + kTh + " element in " + toStringFromArray(test) + " is " + node.data;
        else
            return "There is no " + kTh + " element in " + toStringFromArray(test);
    }
}
