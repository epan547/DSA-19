import java.sql.SQLOutput;
import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        // TODO
        String[] arr = s.split(" ");
        HashMap<String, Integer> hist = new HashMap<String, Integer>();


//        Creating histogram of words in string: O(N)
        for(int i = 0; i < arr.length; i ++){
            if(!hist.containsKey(arr[i])){
                hist.put(arr[i], 0);
            }
            else{
                int val = hist.get(arr[i]);
                hist.put(arr[i], val+1);
            }
        }

        List list = new LinkedList(hist.entrySet());

        // Define comparator
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        String ans = "";
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            for(int k = 0; k <= (int) entry.getValue(); k++){
                ans += entry.getKey() + " "; //stringbuilder.append() for concatenating strings without making a copy
            }
//            System.out.println(entry.getKey());
        }

        System.out.println(ans);
        return ans.strip();
    }

}
