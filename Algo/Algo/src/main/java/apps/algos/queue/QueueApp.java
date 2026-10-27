package apps.algos.queue;

import utils.Queue;

public class QueueApp {
    public static void main (String[] args){
        Queue<Integer> q = new Queue<Integer>(3);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        int getFront = q.getFront();
        int getRear = q.getRear();
        System.out.println(getFront + " " + getRear);
    }
}

