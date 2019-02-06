package your_code;

import java.util.LinkedList;


/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private LinkedList<Integer> ll;
    private int size;

    public MyPriorityQueue() {
        ll = new LinkedList<>();
        size = 0;
    }

    public void enqueue(int item) {
        if (ll.isEmpty()) {
            ll.add(item);
            size ++;
        }
        else{
            for(int i = 0; i < size; i++){
                if (item > ll.get(i)) {
                    ll.add(i, item);
                    size ++;
                    return;
                }
            }
            ll.add(size-1, item);
            size ++;
        }
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        int val = ll.getFirst();
        ll.removeFirst();
        size --;
        return val;
    }

}