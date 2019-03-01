import java.lang.reflect.Array;
import java.util.HashMap;


public class LargestSubArray {


    static int[] largestSubarray(int[] nums) {
        // TODO
//        POssible way to approach this is to create array of sums
//        Then, iterate thru the sums, and store indices as long as next index is >= the sum you started with
//        When you find one less than,
//        It should never reach n^2

        HashMap<Integer, Integer[]> hist;

        int sum = 0;
        Integer[] arr = new Integer[nums.length];
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] == 1){
                sum += 1;
            }
            else{
                sum -= 1;
            }
            arr[i] = sum;
            if(sum < arr[i-1]){

            }
//            if(!HashMap.containsKey(sum)){
//                Integer[] arr = new Integer[]{i, i};
//                HashMap.put(sum, arr);
//            }
//            else{
//                int[] indices = HashMap.get(sum);
//                Integer[] arr = new Integer[]{indices[0], i};
//            }
        }
        return new int[]{0, 0};
    }
}
