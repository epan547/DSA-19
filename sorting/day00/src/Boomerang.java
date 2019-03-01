import java.util.HashMap;
import java.util.*;

public class Boomerang {

//    For each point in points,
//    Calculate the distance between that point and all the other points
//    Store the distances in hashmap as key, with value = 0 if none, and value += 1 if existing
//    For all values in hashmap that are > 0, there are at least 2 entries, so use n*(n-1) to calculate num of boomerangs
//    Sum up boomerangs in variable
//    Return final total

    public static int numberOfBoomerangs(int[][] points) {
        // TODO
        int boomerangs = 0;
        for(int i=0; i<points.length; i++) {
            HashMap<Double, Integer> H = new HashMap<>();
            for(int j=0; j<points.length; j++){
                if(j != i){
                    double distance = Math.sqrt(Math.pow((points[i][0] - points[j][0]),2) + Math.pow((points[i][1] - points[j][1]),2));
//                    System.out.println(distance);
                    if(!H.containsKey(distance)){
                        H.put(distance,0);
                    }
                    else{
                        int val = H.get(distance) + 1;
                        H.put(distance,val);
                    }
                }
            }
            Object[] C = H.values().toArray();
            for(int x=0; x<C.length; x++){
                int n = (int)C[x] + 1;
                boomerangs += n*(n-1);
            }
//            System.out.println(boomerangs);
//            System.out.println("Loop");
        }
        return boomerangs;
    }
}

