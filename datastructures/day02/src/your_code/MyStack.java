package your_code;
import ADTs.StackADT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private ArrayList<Integer> A;
    private Integer size;

    public MyStack() {
        ll = new LinkedList<>();
        A = new ArrayList<>();
        size = 0;
    }

    @Override
    public void push(Integer e) {
        A.add(e);
        size ++;
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        A.remove(size-1);
        size --;
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        Integer val = Collections.max(A);
        return val;
    }
}
