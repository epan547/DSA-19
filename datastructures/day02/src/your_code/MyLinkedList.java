package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            this.prev = null;
            this.next = null;
        }
    }

    public MyLinkedList() {
        Node head;
        Node tail;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (size == 0) {
            Node n = new Node(c);
            this.head = n;
            this.tail = n;
            size ++;
        }
        else {
            Node n = new Node(c, this.tail, null);
            this.tail.next = n;
            this.tail = n;
            size ++;
        }
    }

    public void addFirst(Chicken c) {
        if (size == 0) {
            Node n = new Node(c);
            this.head = n;
            this.tail = n;
            size ++;
        }
        else {
            Node temp = new Node(c, null, this.head);
            this.head.prev = temp;
            this.head = temp;
            size ++;
        }

    }

    public Chicken get(int index) {
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        else {
            Node n = this.head;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            return n.val;
        }

    }

    public Chicken remove(int index) {
        System.out.println(size);
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            Chicken c = this.removeFirst();
            return c;
        }

        else if (index == size-1){
            Chicken c = this.removeLast();
            return c;
        }
        else{
            Node curr = this.head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            Chicken c = curr.val;
            Node after = curr.next;
            Node before = curr.prev;
            before.next = after;
            after.prev = before;
            size --;
            return c;
        }
    }

    public Chicken removeFirst() {
        if (size == 0) {
            return null;
        }
        else if (size == 1){
            Chicken c = this.head.val;
            this.head = null;
            this.tail = null;
            size --;
            return c;
        }
        else {
            Chicken c = this.head.val;
            this.head = this.head.next;
            this.head.prev = null;
            size --;
            return c;
        }
    }

    public Chicken removeLast() {
        if (size == 0) {
            return null;
        }
        else if (size == 1){
            Chicken c = this.tail.val;
            this.head = null;
            this.tail = null;
            size --;
            return c;
        }
        else {
            Chicken c = this.tail.val;
            this.tail = this.tail.prev;
            this.tail.next = null;
            size --;
            return c;
        }
    }
}