public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        // TODO
//        Finding the max
        int max = A[0];
        for(int i = 0; i< A.length; i++){
            if(A[i]>max){
                max = A[i];
            }
        }
//        putting things into the array
        int arr[] = new int[max+1];
        for(int i = 0; i<A.length; i++){
            arr[A[i]] = arr[A[i]]+1;
        }

        int i = 0;
        for(int j= 0; j<arr.length; j++){
            while(arr[j] > 0){
                A[i] = j;
                arr[j] -= 1;
                i++;
            }
        }

    }

}
