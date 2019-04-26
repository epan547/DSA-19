import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class BarnRepair {

    public static int solve(int M, int[] occupied) {

        PriorityQueue<Integer> PQ =  new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        // make sure occupied is in ascending order
        Arrays.sort(occupied);

        int last = occupied[0];
        int space_count = 0;
        int sum = occupied.length;
        int consecutive_count = 0;

        // Find where the gaps are, put in PQ from smallest to greatest
        for(int i:occupied){
            if(i==last || i == last+1){
                //pass
            }
            else{
                consecutive_count++;
                PQ.add(i-last-1);
            }
            last = i;
        }
        consecutive_count++;

//        increment sum based on how many gaps there are
        while(consecutive_count > M){
            sum += PQ.poll();
            consecutive_count--;
        }

        return sum;
    }
}

// No longer needed
//       For debugging:
//        String s = "";
//        for(int i: occupied){
//            s += i + ",";
//        }
//        System.out.println(s);

class entryComparator implements Comparator<entry> {

    // Overriding compare()method of Comparator
    // for descending order of cgpa
    public int compare(entry s1, entry s2) {
        if (s1.len < s2.len)
            return 1;
        else if (s1.len > s2.len)
            return -1;
        return 0;
    }
}

class entry {
    public int[] span;
    public int len;

    // A parameterized student constructor
    public entry(int[] span, int len) {

        this.span = span;
        this.len = len;
    }
}