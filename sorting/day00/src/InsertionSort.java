
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
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
        // TODO
        if(array==null || array.length == 0 || array.length == 1){
            return array;
        }
        int[] sol = new int[array.length];
        sol[0] = array[0];

        for(int i=1; i<array.length; i++){
            sol[i] = array[i];

            if(array[i] < sol[i-1]){
//          Go backwards and swap if previous value in sol is greater than current value
                int curr = i;

                while(curr > 0){
                    if(sol[curr-1] > sol[curr]){
                        int temp = sol[curr-1];
                        sol[curr-1] = sol[curr];
                        sol[curr] = temp;
                    }
                    curr--;
                }

            }
        }
        System.out.println("done");
        return sol;
    }
}

//For debugging:
//            for(int x=0; x<sol.length; x++){
//                System.out.print(sol[x]);
//                System.out.print(' ');
//            }
//            System.out.println(" ");
