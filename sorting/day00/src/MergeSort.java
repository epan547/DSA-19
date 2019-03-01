import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        // base case
        if(array.length == 1){
            return array;
        }

        if(array.length == 0 || array.length == 1){
            return array;
        }
        int i = array.length / 2;
        int[] a = Arrays.copyOfRange(array, 0, i);
        int[] b = Arrays.copyOfRange(array, i, array.length);

//      Sorted arrays
        int[] left = sort(a);
        int[] right = sort(b);


        return merge(left, right);
    }


    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        // TODO
        int[] c = new int[a.length + b.length];
        int i = 0;
        int j = 0;

        for(int x = 0; x < a.length + b.length; x++){
            if(i==a.length){
                int[] add = Arrays.copyOfRange(b, j, b.length);
                for(int y=0; y<add.length; y++){
                    c[x+y] = add[y];
                }
                return c;
            }
            if(j==b.length){
                int len = a.length;
                int[] add = Arrays.copyOfRange(a, i, len);
                for(int y=0; y<add.length; y++){
                    c[x+y] = add[y];
                }
                return c;
            }
            if(a[i] < b[j]){
                c[x] = a[i];
                i++;
            }
            else{
                c[x] = b[j];
                j++;
            }
        }
        return c;
    }

}
