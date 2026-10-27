package utils;

public class Queue<T> {
    private Object[] items;
    private int capacity;
    private int size;

    public Queue (int capacity){
        this.capacity = capacity;
        this.items = new Object[capacity];
        this.size = 0;
    }

    public void enqueue (T item) {
        if(this.size == this.capacity){
            return;
        }
        this.items[this.size ++] = item;
    }

    public T dequeue() {
        if(this.size == 0){
            return null;
        }
        T item = (T)this.items[0];
        for (int i = 0; i < this.size; i++) {
            this.items[i-1] = this.items[i];
        }
        this.size--;
        return item;
    }

    // peek
    public T getFront() {
        if(this.size == 0){
            return null;
        }
        return (T) this.items[0];
    }

    public T getRear() {
        if(this.size == 0){
            return null;
        }
        return (T)this.items[this.size - 1];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }
}
