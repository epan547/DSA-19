public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // TODO: Runtime: O(1) or O(N), if adding leads to resizing the array
    public void add(Cow c) {
        if (size >= elems.length) {
        // make an array 2x as big and copy over elems
            Cow[] bigger = new Cow[elems.length * 2];
            System.arraycopy(elems, 0, bigger, 0, elems.length);
            elems = bigger; // redefine elems as the new bigger array
        }
        elems[size] = c;
        size ++;
    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elems[index];
    }

    // TODO: Runtime: O(1), unless resizing array, in which case O(N)
    public Cow remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size < (elems.length/4)) {
            Cow[] smaller = new Cow[(int)(elems.length / 4)];
            System.arraycopy(elems, 0,smaller,0,elems.length);
            elems = smaller;
        }
        Cow rem = elems[index]; // saving the removed cow
        for (int i=index; i<size-1; i++) {
            elems[i] = elems[i+1];
        }
        size--;
        return rem;
    }

    // TODO: Runtime: O(N)
    public void add(int index, Cow c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size >= elems.length) {
            // make an array 2x as big and copy over elems
            Cow[] bigger = new Cow[elems.length * 2];
            System.arraycopy(elems, 0, bigger, 0, elems.length);
            elems = bigger; // redefine elems as the new bigger array
        }
        size ++;

        // shift the other elements, moving backwards this time
        for (int i=size-1; i>index; i--) {
            elems[i] = elems[i-1];
        }
        // put the new one in the right place
        elems[index] = c;
    }
}