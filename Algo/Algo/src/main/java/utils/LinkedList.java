package utils;

public class LinkedList<T> {

    static class Node<T>{
        T data;
        Node<T> next;

        Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    public LinkedList(){
        this.head = null;
        this.size = 0;
    }

    public void add(T data){
        Node<T> newNode = new Node<T>(data);
        if(this.size == 0){
            this.head = newNode;
        }
        else{
            Node<T> current = this.head;
            while(current != null && current.next != null){
                current = current.next;
            }
            if(current != null){
                current.next = newNode;
            }
        }

        this.size ++;
    }

    public boolean remove(T data){
        if(this.size == 0){
            return false;
        }

        if(this.head.data == data){
            this.head = this.head.next;
            this.size --;
            return true;
        }

        Node<T> current = this.head;
        Node<T> savePrevious = null;
        while(current != null && current.data != data){
            savePrevious = current;
            current = current.next;
        }

        if(current == null){
            return false;
        }

        savePrevious.next = current.next;
        this.size --;
        return true;
    }

    public T get(int index){
        if(this.size == 0){
            return null;
        }

        Node<T> current = this.head;
        int i = 0;

        if(current == null){
            return null;
        }

        while(current != null){
            if(index == i){
                return current.data;
            }
            current = current.next;
            i++;
        }

        return null;
    }

    public int getSize() {
        return this.size;
    }

    public String toString(){
        String ret = "";
        Node<T> current = this.head;
        while(current != null){
            ret += current.data + "-->";
            current = current.next;
        }
        return ret;
    }

    public boolean contains (T data){
        Node<T> current = this.head;
        while(current != null){
            if(current.data == data){
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
