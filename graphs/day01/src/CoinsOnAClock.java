import java.lang.reflect.Array;
import java.util.*;


public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO
        List<char[]> result = new ArrayList<>();

        // could make this a hashmap, to reduce run time - can run conditions once instead of repeating solutions
        HashMap<Character,Integer> unused= new HashMap<>();

        unused.put('p',pennies);
        unused.put('n',nickels);
        unused.put('d',dimes);
        unused.put('t',0); // total coins placed

        namePermutations(0, unused, new char[hoursInDay], hoursInDay, result);
//        System.out.println(result);
        return result;
    }

    public static void namePermutations(int curr, HashMap<Character,Integer> unused, char[] occupied, int hrs, List<char[]> result) {

        // could use char[] array, because we know how many elements there will be
        //  base case: If all coin counters = 0, solution found
        if(unused.get('t') == hrs) {
            // copy and convert current to a char[], so it can be added to results list
            char[] temp = new char[hrs];
            System.arraycopy(occupied,0,temp,0,hrs);
            result.add(temp);
//            System.out.println(temp);
            occupied = new char[hrs];
        }

//        System.out.println(unused.entrySet());

        if(occupied[curr] == '\0'){
            for (Map.Entry i : unused.entrySet()) {
                if(unused.get(i.getKey()) > 0){
                    unused.replace((char)i.getKey(),(int)i.getValue()-1);

                    // add the key to the list
                    occupied[curr] = (char)i.getKey();

                    // increment number of things in list
                    unused.replace('t',unused.get('t')+1);

                    if((char) i.getKey() == 'p'){
                        namePermutations((curr + 1)%hrs, unused, occupied, hrs,result);
                    }
                    if((char) i.getKey() == 'n'){
                        namePermutations((curr + 5)%hrs, unused, occupied, hrs,result);
                    }
                    if((char) i.getKey() == 'd'){
                        namePermutations((curr + 10)%hrs, unused, occupied, hrs,result);
                    }
                    // Backtracking
                    occupied[curr] = '\0';
                    unused.replace('t',unused.get('t')-1);
                    unused.replace((char)i.getKey(),(int)i.getValue()+1);
                }

            }
        }
    }
}
