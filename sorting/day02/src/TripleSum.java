import java.util.Arrays;
import java.util.HashMap;

public class TripleSum {
//    public int sol[][];
//    public int size= 0;

    public static int tripleSum(int arr[], int sum) {
        // TODO

        int count = 0;

        for(int i = 0; i < arr.length-2; i ++){
            int double_sum = sum - arr[i];
            int new_arr[] = Arrays.copyOfRange(arr, i+1, arr.length);
            HashMap<Integer, Integer> map = new HashMap<>();

            for(int k = 0; k < new_arr.length; k ++){
                if(map.containsKey(double_sum-new_arr[k])){
                    if(map.get(double_sum-new_arr[k])== 1){
                        map.remove(double_sum-new_arr[k]);
                        count ++;
                    }
                    else{
                        int val = map.get(double_sum-new_arr[k]);
                        map.remove(double_sum-new_arr[k]);
                        map.put(double_sum-new_arr[k], val-1);
                        count ++;
                    }
                }
                else{
                    if(map.containsKey(new_arr[k])){
                        int val = map.remove(new_arr[k]);
                        map.put(new_arr[k], val+1);
                    }
                    else{
                        map.put(new_arr[k], 1);
                    }

                }
            }
        }
//        System.out.println(count);
        return count;
    }
}
