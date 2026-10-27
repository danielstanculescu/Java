package utils;

public class Stack<T> {
    private Object items[];
    private int size;
    private int capacity;

    public Stack(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.items = new Object[capacity];
    }

    public void push(T item){
        if(this.size == capacity){
            return;
        }
        items[this.size++] = item;
    }

    public T pop() {
        if(this.size == 0){
            return null;
        }
        T item = (T)items[--this.size];
        this.items[this.size] = null;
        return item;
    }

    public T peek() {
        if(this.size == 0){
            return null;
        }
        return (T)items[this.size-1];
    }

    public boolean isEmpty (){
        return this.size == 0;
    }

    public int getSize(){
        return this.size;
    }

    // same code as peek
    public T getFirst() {
        if(this.size == 0){
            return null;
        }
        return (T)items[this.size-1];
    }

    public T getLast() {
        if(this.size == 0){
            return null;
        }
        return (T)this.items[0];
    }

}
