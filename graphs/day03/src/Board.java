import java.util.*;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    // Board size
    private int n;
    public int[][] tiles;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        // TODO: Your code here
        n = (b.length == b[0].length) ? b.length : 0;
        tiles = b;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        // TODO: Your code here
        return n;
    }

    public HashMap<Integer,Integer[]> make_map(){
        HashMap<Integer,Integer[]> map = new HashMap<>();
        int count = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Integer[] coords = new Integer[]{i,j};
                map.put(count,coords);
                count += 1;
            }
        }
        return map;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        // TODO: Your code here
        HashMap<Integer,Integer[]> map = make_map();
        int sum = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(tiles[i][j]!=0){
//                    System.out.println(tiles[i][j]);
                    Integer[] expected = map.get(tiles[i][j]);
                    sum += Math.abs(expected[0]-i) + Math.abs(expected[1]-j);
                }
            }
        }
        return sum;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        // TODO: Your code here
        if(this.manhattan() > 0){
            return false;
        }
        return true;
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        // TODO: Your code here
        int count = 0;
        int[] linear = new int[9];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                linear[i*3 + j] = tiles[i][j];
            }
        }
        System.out.println(linear);
        for(int i=0;i<(n*n)-1;i++){
            for(int j=i+1;j<(n*n);j++){
                if(linear[i] > 0 && linear[j] > 0 && linear[i] > linear[j]){
                    count++;
                }
            }
        }
        System.out.println(count);
        if(count % 2 == 0){
            return true;
        }
        return false;
    }

    public int[][] copy_2d(int[][] tiles){
        int[][] copy = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            copy[i] = Arrays.copyOf(tiles[i], tiles[i].length);
        }
        return copy;
    }

    /*
     * Return all neighboring boards in the state tree
     */
//    public Iterable<Board> neighbors() {
//        // TODO: Your code here
//        ArrayList<Board> neighbors = new ArrayList<>();
//
//        // Locate where the empty space (0) is
//        Integer[] center = new Integer[2];
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                if(tiles[i][j]==0){
//                    center[0]=i; // row
//                    center[1]=j; //column
//                }
//            }
//        }
//
//        // there are always 2*(n-1) neighboring states
//        // iterate through the 4 directions the empty space could move in- left, right, up down
//
//        int direction = 0;
//        Integer[] curr = Arrays.copyOf(center,2);
//        Integer[] prev = Arrays.copyOf(center,2);
//
////        System.out.println(Arrays.deepToString(center));
//        int[][] copy = copy_2d(tiles);
//
//        while(neighbors.size() < 2*(n-1)){
//            if(direction == 0){
//                curr[0]--;
//            }
//            if(direction == 1){
//                curr[0]++;
//            }
//            if(direction == 2){
//                curr[1]++;
//            }
//            if(direction == 3){
//                curr[1]--;
//            }
//            if(curr[0] < 0 || curr[1] < 0 || curr[0]>n-1 || curr[1]>n-1){
//                curr = Arrays.copyOf(center,2);
//                prev = Arrays.copyOf(center,2);
//                direction ++;
//
//                // set copy back to the original board
//                copy = copy_2d(tiles);
//            }
//            else{
////                System.out.println(Arrays.deepToString(curr));
//
//                int temp = copy[curr[0]][curr[1]];
//
//                copy[curr[0]][curr[1]] = 0;
//                copy[prev[0]][prev[1]] = temp;
//                prev = Arrays.copyOf(curr,2);
////                System.out.println(Arrays.deepToString(copy));
//
//                int[][] new_arr = copy_2d(copy);
//
//                neighbors.add(new Board(new_arr));
//            }
//        }
//        return neighbors;
//    }

    public Iterable<Board> neighbors() {
        // TODO: Your code here
        ArrayList<Board> neighbors = new ArrayList<>();

        // Locate where the empty space (0) is
        Integer[] center = new Integer[2];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(tiles[i][j]==0){
                    center[0]=i; // row
                    center[1]=j; //column
                }
            }
        }

        // there are always 2*(n-1) neighboring states
        // iterate through the 4 directions the empty space could move in- left, right, up down

        int direction = 0;
        Integer[] curr = Arrays.copyOf(center,2);
        Integer[] anchor = Arrays.copyOf(center,2);

//        System.out.println(Arrays.deepToString(center));
        int[][] copy = copy_2d(tiles);

        while(direction < 4){
            if(direction == 0){
                curr[0]--;
            }
            if(direction == 1){
                curr[0]++;
            }
            if(direction == 2){
                curr[1]++;
            }
            if(direction == 3){
                curr[1]--;
            }
            if(curr[0] >= 0 && curr[1] >= 0 && curr[0]<=n-1 && curr[1]<=n-1){
                int temp = copy[curr[0]][curr[1]];

                copy[curr[0]][curr[1]] = 0;
                copy[anchor[0]][anchor[1]] = temp;
//                System.out.println(Arrays.deepToString(copy));

                int[][] new_arr = copy_2d(copy);

                neighbors.add(new Board(new_arr));
            }

            curr = Arrays.copyOf(center,2);
            anchor = Arrays.copyOf(center,2);
            direction ++;

            // set copy back to the original board
            copy = copy_2d(tiles);
        }
        return neighbors;
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 0, 3}, {4, 2, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
        for(Board i:it){
            System.out.println(Arrays.deepToString(i.tiles));
        }
    }
}
