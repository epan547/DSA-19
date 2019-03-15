package divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Skyline {

    public static class Point {
        public int x;
        public int y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Building {
        private int l, r, h;
        public Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        //
//        HashMap<Integer, Integer> hist = new HashMap<Integer, Integer>();
//        ArrayList<Point> sol = new ArrayList<>(B.length);
//        int min = 10000;
//
//        for(int i=0;i<B.length;i++){
//            for(int k=B[i].l; k<=B[i].r; k++){
//                if(hist.containsKey(k)){
//                    if(hist.get(k) < B[i].h){
//                        hist.remove(k);
//                        hist.put(k, B[i].h);
//                    }
//                }
//                hist.put(k, B[i].h);
//            }
//            if(B[i].l < min){
//                min = B[i].l;
//            }
//        }
//
//        for(int i = 0; i < hist.size(); i++){
//            Point
//        }

//

        List<Point> sol = sort(B, 0, B.length);
        System.out.println(sol.size());
        return sol;

    }

    public static List<Point> sort(Building[] B, int lo, int hi) {
// base case
        if(hi == lo){
            ArrayList<Point> ans = new ArrayList<>();
            return ans;
        }
        if(hi-lo == 1){
            ArrayList<Point> ans = new ArrayList<>(2);
//            for(int i = B[lo].l; i<=B[lo].r; i++){
                Point P = new Point(B[lo].l, B[lo].h);
                Point R = new Point(B[lo].r, 0);
                ans.add(P);
                ans.add(R);
//            }
            return ans;
        }
//        if(B.length == 2){
//            ArrayList<Point> ans = new ArrayList<>(2);
//            Point A = new Point(B[lo].l, B[lo].h);
//            Point C = new Point(B[lo+1].l, B[lo+1].h);
//            ans.add(A);
//            ans.add(C);
//            return ans;
//        }

        int i = (lo+hi) / 2;


//      Sorted arrays
        List<Point> left = sort(B, lo, i);
        List<Point> right = sort(B, i, hi);


        return merge(left, right);
    }


    public static List<Point> merge(List<Point> a, List<Point> b) {
        // TODO
        ArrayList<Point> c = new ArrayList<Point>(a.size() + b.size()); //= new Point[a.size() + b.size()]
        int i = 0;
        int j = 0;
        int x = 0;

        while(i<a.size() || j<b.size()) {
            System.out.println(x);
            if (i < a.size() && j < b.size()) {
                if (a.get(i).x == b.get(j).x) {
                    Point temp = new Point(x, Math.max(a.get(i).y, b.get(j).y));
                    c.add(x, temp);
//                    if (a.get(i).y > b.get(j).y) {
//                        c.add(x, a.get(i));
//                        System.out.println(a.get(i).x + "+" +a.get(i).y);
//                    } else {
//                        c.add(x, b.get(j));
//                        System.out.println(b.get(j).x + "+" + b.get(j).y);
//                    }
                    x++;
                    i++;
                    j++;
                }
                else if (a.get(i).x < b.get(j).x) {
                    c.add(x, a.get(i));
                    System.out.println(a.get(i).x + "+" +a.get(i).y );
                    i++;
                    x++;
                } else if (a.get(i).x > b.get(j).x){
                    c.add(x, b.get(j));
                    System.out.println(b.get(j).x + "+" + b.get(j).y );
                    j++;
                    x++;
                }
//            if last two points have same value, remove one
            }
            else{
                if(i<a.size() || j >= b.size()){
                    c.add(x, a.get(i));
                    System.out.println(a.get(i).x + "+" + a.get(i).y );
                    i++;
                    x++;
                }
                if(j<b.size() || i>=a.size()){
                    c.add(x, b.get(j));
                    System.out.println(b.get(j).x + "+" + b.get(j).y );
                    j++;
                    x++;
                }
            }

//            If there are elements left in a or b, add them to c
            if(c.size() > 2){
                if(c.get(c.size() -1) == c.get(c.size() -2)){
                    c.remove(a.size() + b.size() -1);
                }
            }
        }


        return c;
    }
}
