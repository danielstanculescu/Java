package apps.algos.linkedlist;

import utils.LinkedList;

public class LinkedListApp {
    public static void main(String[] args){
        LinkedList<String> list = new LinkedList<String>();

        list.add("10");
        list.add("20");
        list.add("30");
        list.add("40");

        list.remove("30");
        String val = list.get(1);
        System.out.println("list: " + list.toString());
        int size = list.getSize();
        System.out.println("size " + size + " val " + val);
    }
}
