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
            Point P = new Point(B[lo].l, B[lo].h);
            Point R = new Point(B[lo].r, 0);
            ans.add(P);
            ans.add(R);
            return ans;
        }

        int i = (lo+hi) / 2;

//      Sorted arrays
        List<Point> left = sort(B, lo, i);
        List<Point> right = sort(B, i, hi);


        return merge(left, right);
    }


    public static List<Point> merge(List<Point> a, List<Point> b) {
        // TODO
        ArrayList<Point> c = new ArrayList<Point>(); //= new Point[a.size() + b.size()]
        int i = 0; //counter for a
        int j = 0; //counter for b



        while(i<a.size() || j<b.size()) {
            if (i < a.size() && j < b.size()) {

                //case 1: both points are equal, take biggest, increment both
                if (a.get(i).x == b.get(j).x) {
                    int y = Math.max(a.get(i).y, b.get(j).y);
                    if(y == a.get(i).y){
                        Point temp = new Point(a.get(i).x, y);
                        c.add(temp);
                    }
                    else{
                        Point temp = new Point(b.get(j).x, y);
                        c.add(temp);
                    }
                    i++;
                    j++;
                }

                //case 2: a is to the left, if it's greater than the previous point, add to list, increment i
                //if it's less than the previous point, increment i
                else if (a.get(i).x < b.get(j).x) {
                    if(j > 0){
                        if(a.get(i).y > b.get(j-1).y){
                            Point temp = new Point(a.get(i).x, a.get(i).y);
                            c.add(temp);
                        }
                        else{
                            Point temp = new Point(a.get(i).x, b.get(j-1).y);
                            c.add(temp);
                        }
                    }
                    else{
                        Point temp = new Point(a.get(i).x, a.get(i).y);
                        c.add(temp);
                    }

                    i++;
                }

                //case 3: b is to the left, do the same, but increment j
                else if (a.get(i).x > b.get(j).x){
                    if(i > 0){
                        if(b.get(j).y > a.get(i-1).y){
                            Point temp = new Point(b.get(j).x, b.get(j).y);
                            c.add(temp);
                        }
                        else{
                            Point temp = new Point(b.get(j).x, a.get(i-1).y);
                            c.add(temp);
                        }
                    }
                    else{
                        Point temp = new Point(b.get(j).x, b.get(j).y);
                        c.add(temp);
                    }
                    j++;
                }
            }
            else{
                // If there are elements left in a or b, add them to c
                if(i<a.size()){
                    if(j > 0){
                        if(a.get(i).y > b.get(j-1).y){
                            Point temp = new Point(a.get(i).x, a.get(i).y);
                            c.add(temp);
                        }
                        else{
                            Point temp = new Point(a.get(i).x, b.get(j-1).y);
                            c.add(temp);
                        }
                    } else{
                        Point temp = new Point(a.get(i).x, a.get(i).y);
                        c.add(temp);
                    }
                    i++;
                }
                if(j<b.size()){
                    if(i > 0){
                        if(b.get(j).y > a.get(i-1).y){
                            Point temp = new Point(b.get(j).x, b.get(j).y);
                            c.add(temp);
                        }
                        else{
                            Point temp = new Point(b.get(j).x, a.get(i-1).y);
                            c.add(temp);
                        }
                    }else{
                        Point temp = new Point(b.get(j).x, b.get(j).y);
                        c.add(temp);
                    }

                    j++;
                }
            }

            if(c.size() >= 2){
                if(c.get(c.size() -1).y == c.get(c.size() -2).y){
                    c.remove(c.size()-1);
                }
            }

            for(int n = 0; n < c.size(); n++){
                System.out.print(c.get(n).x + "+" +c.get(n).y + ",");
            }
            System.out.println(" ");
        }


        return c;
    }
}
