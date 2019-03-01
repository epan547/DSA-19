import java.util.Arrays;

import static java.lang.System.arraycopy;

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        // TODO

        if(rightChild(i) <= this.size-1){
            if(heap[leftChild(i)] > heap[i] || heap[rightChild(i)] > heap[i]){
                if(heap[leftChild(i)] > heap[rightChild(i)]){
                    int temp = heap[leftChild(i)];
                    heap[leftChild(i)] = heap[i];
                    heap[i] = temp;
                    sink(leftChild(i));
                }
                else {
                    int temp = heap[rightChild(i)];
                    heap[rightChild(i)] = heap[i];
                    heap[i] = temp;
                    sink(rightChild(i));
                }
            }
            else{
                return;
            }
        }
        else if (leftChild(i) <= this.size-1){
            if(heap[leftChild(i)] > heap[i]){
                int temp = heap[leftChild(i)];
                heap[leftChild(i)] = heap[i];
                heap[i] = temp;
                sink(leftChild(i));
            }
            else{
                return;
            }
        }
        return;

    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i= (this.size / 2) -1; i>=0; i--) {
            // TODO
            this.sink(i);

        }
//        for (int j = 0; j < this.size; j++){
//            System.out.print(array[j] + ",");
//        }
//        System.out.println(" ");
    }

    /**
     * Best-case runtime: n logn
     * Worst-case runtime: n logn
     * Average-case runtime: n logn
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            // TODO
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;

//            array = Arrays.copyOf(array, array.length-1);
            size --;
            sink(0);

        }

        return heap;
    }
}
