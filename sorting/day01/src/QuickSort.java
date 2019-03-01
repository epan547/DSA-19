import java.util.concurrent.ThreadLocalRandom;
import java.util.*;



public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * TODO
     * Best-case runtime: n logn
     * Worst-case runtime: n^2
     * Average-case runtime: n logn
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        // TODO: Sort the array. Make sure you avoid the O(N^2) runtime worst-case

//        Shuffling the array
        shuffleArray(array);
//        Calling quicksort
        quickSort(array,0, array.length-1);
        return array;

    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if(a.length <= 1){
            return;
        }

        if (lo < hi) {
            int p = partition(a, lo, hi);
            if(p-1-lo > 0){
                quickSort(a, lo, p-1);
            }
            if(hi-(p) > 0) {
                quickSort(a, p + 1, hi);
            }
        }
        else{
            return;
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
        // TODO
        int pivot = lo;
        int last = hi;
        int curr = lo + 1;
        for(int i = lo+1; i <= hi; i++){
            if(array[curr] < array[pivot]){
                int temp = array[curr];
                array[curr] = array[pivot];
                array[curr-1] = temp;
                pivot = curr;
                curr++;
            }
            else{
//              Switch current element with last unknown element
                int temp = array[last];
                array[last] = array[curr];
                array[curr] = temp;
//              Decrement last unknown element
                last --;
                if(last < curr){
                    return pivot;
                }
            }
        }

//      For debugging
//        for(int j = lo; j<= hi; j++){
//            System.out.print(array[j] + ",");
//        }
//        System.out.println("done " + pivot);
        return pivot;
    }

}
