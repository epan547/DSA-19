import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {
//    public static List<List<Integer>> permutations = new LinkedList<>();

    public static List<List<Integer>> permutations(List<Integer> A) {
        // TODO
        List<Integer> unused = new LinkedList<>();
        List<Integer> current = new LinkedList<>();
        List<List<Integer>> permutations = new LinkedList<>();


        unused.addAll(A);

        namePermutations(current, unused, permutations);
        System.out.println(permutations);
        System.out.println(permutations.size());
        return permutations;
    }


    public static void namePermutations(List<Integer> current, List<Integer> unused, List<List<Integer>> permutations){
        //  base case: If all characters are added, then add the current word to permutations

        if(unused.isEmpty()){
            // Making a copy of the original lists, to send to the next function call
            List<Integer> temp_current = new LinkedList<>();
            temp_current.addAll(current);
            permutations.add(temp_current);
//            System.out.println(permutations);
        }

        for(Integer i:unused.toArray(new Integer[unused.size()])){
            current.add(i);
            unused.remove(i);

            namePermutations(current, unused, permutations);

            current.remove(i);
            unused.add(i);
        }
//        System.out.println(permutations);
    }


}
