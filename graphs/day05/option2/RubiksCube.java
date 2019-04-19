import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


// this is our implementation of a rubiks cube. It is your job to use A* or some other search algorithm to write a
// solve() function
public class RubiksCube {

    private BitSet cube;
    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;
    char[] options = new char[] {'u', 'U', 'r', 'R', 'f', 'F'};

    // initialize a solved rubiks cube
    public RubiksCube() {
        // 24 colors to store, each takes 3 bits
        cube = new BitSet(24 * 3);
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                setColor(side * 4 + i, side);
            }
        }
    }

    // initialize a rubiks cube with the input bitset
    private RubiksCube(BitSet s) {
        cube = (BitSet) s.clone();
    }

    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        cube = (BitSet) r.cube.clone();
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        return other.cube.equals(cube);
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        return cube.hashCode();
    }

    public boolean isSolved() {
        return this.equals(new RubiksCube());
    }


    // takes in 3 bits where bitset.get(0) is the MSB, returns the corresponding int
    private static int bitsetToInt(BitSet s) {
        int i = 0;
        if (s.get(0)) i |= 4;
        if (s.get(1)) i |= 2;
        if (s.get(2)) i |= 1;
        return i;
    }

    // takes in a number 0-5, returns a length-3 bitset, where bitset.get(0) is the MSB
    private static BitSet intToBitset(int i) {
        BitSet s = new BitSet(3);
        if (i % 2 == 1) s.set(2, true);
        i /= 2;
        if (i % 2 == 1) s.set(1, true);
        i /= 2;
        if (i % 2 == 1) s.set(0, true);
        return s;
    }

    // index from 0-23, color from 0-5
    private void setColor(int index, int color) {
        BitSet colorBitset = intToBitset(color);
        for (int i = 0; i < 3; i++)
            cube.set(index * 3 + i, colorBitset.get(i));
    }


    // index from 0-23, returns a number from 0-5
    private int getColor(int index) {
        return bitsetToInt(cube.get(index * 3, (index + 1) * 3));
    }

    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }

    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        int[] faceFrom = null;
        int[] faceTo = null;
        int[] sidesFrom = null;
        int[] sidesTo = null;
        // colors move from the 'from' variable to the 'to' variable
        switch (c) {
            case 'u': // clockwise
            case 'U': // counterclockwise
                faceFrom = new int[]{0, 1, 2, 3};
                faceTo = new int[]{1, 2, 3, 0};
                sidesFrom = new int[]{4, 5, 8, 9, 17, 16, 21, 20};
                sidesTo = new int[]{21, 20, 4, 5, 8, 9, 17, 16};
                break;
            case 'r':
            case 'R':
                faceFrom = new int[]{8, 9, 10, 11};
                faceTo = new int[]{9, 10, 11, 8};
                sidesFrom = new int[]{6, 5, 2, 1, 17, 18, 13, 14};
                sidesTo = new int[]{2, 1, 17, 18, 13, 14, 6, 5};
                break;
            case 'f':
            case 'F':
                faceFrom = new int[]{4, 5, 6, 7};
                faceTo = new int[]{5, 6, 7, 4};
                sidesFrom = new int[]{3, 2, 8, 11, 14, 15, 23, 20};
                sidesTo = new int[]{8, 11, 14, 15, 23, 20, 3, 2};
                break;
            default:
                System.out.println(c);
                assert false;
        }
        // if performing a counter-clockwise rotation, swap from and to
        if (Character.isUpperCase(c)) {
            int[] temp;
            temp = faceFrom;
            faceFrom = faceTo;
            faceTo = temp;
            temp = sidesFrom;
            sidesFrom = sidesTo;
            sidesTo = temp;
        }
        RubiksCube res = new RubiksCube(cube);
        for (int i = 0; i < faceFrom.length; i++) res.setColor(faceTo[i], this.getColor(faceFrom[i]));
        for (int i = 0; i < sidesFrom.length; i++) res.setColor(sidesTo[i], this.getColor(sidesFrom[i]));
        return res;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r= r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }

    // calculate manhattan distance
    public int manhattan(RubiksCube r) {
//        RubiksCube sol = new RubiksCube();
        int mandis = 0;
        for(int i = 0; i < 24; i++) {
            int tar = r.getColor(i);
            int cur = i / 4;
            if(Math.abs(tar-cur) == 3) { mandis += 2; }
            else if(Math.abs(tar-cur) != 0) {mandis++;}
        }
        return (int) (mandis / 8);
    }


    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
        // TODO
        State initState = new State(this,0,new ArrayList<Character>(),null);
        solutionState = new State(this,0,new ArrayList<Character>(),null);
//        PriorityQueue<State> PQ = new PriorityQueue<State>(new Comparator<State>() {
//            public int compare(State lhs, State rhs) {
//                if (lhs.cost < rhs.cost) return +1;
//                if (lhs.cost == rhs.cost) return 0;
//                return -1;
//            }
//        });

        PriorityQueue<State> PQ = new PriorityQueue<>();

        PQ.add(initState);

        HashMap<State, State> open = new HashMap<>();
        HashMap<State, State> closed = new HashMap<>();



        open.put(initState, initState);

        while (!PQ.isEmpty()){
            State curr = PQ.poll();

            if(curr.cube.isSolved()){
//               Base case: return the path if goal is reached
                solutionState = curr;
                minMoves = curr.moves;
                solved = true;
                System.out.println("you got it");
                // want to be able to return a list of previous moves
                return curr.rotations;
            }

//            for(RubiksCube n:curr.cube.getNeighbors()){
//                State next = new State(n,curr.moves+1,curr.rotations.add(), curr);
////                System.out.println(Arrays.deepToString(next.board.tiles));
//                if(open.containsKey(next) && curr.cost > open.get(next).cost){
//                    continue;
//                }
//                if(closed.containsKey(next) && curr.cost > closed.get(next).cost){
//                    continue;
//                }
//
//                PQ.add(next);
//                open.put(next, next);
//            }
            RubiksCube temp = curr.cube;
            System.out.println("starting new loop");

            for(char rot: options){
                temp.rotate(rot);
                List<Character> rotlist = curr.rotations;
                rotlist.add(rot);
                State next = new State(temp,curr.moves+1,rotlist, curr);
                //System.out.println("one possible cost is: "+ next.cost);
                System.out.println(next.cost);
                if(open.containsKey(next) && curr.cost > open.get(next).cost){
                    continue;
                }
                if(closed.containsKey(next) && curr.cost > closed.get(next).cost){
                    continue;
                }

                PQ.add(next);
                open.put(next, next);
            }
            closed.put(curr, curr);
            open.remove(curr);
        }
        return new ArrayList<>();
    }

    public LinkedList<RubiksCube> getNeighbors(RubiksCube r) {
        LinkedList<RubiksCube> n = new LinkedList<>();
        // double check that this is ok
        RubiksCube temp = r;

        for (char i : options) {
            temp.rotate(i);
            n.add(temp);
            temp = r;
        }
        return n;
    }

    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private RubiksCube cube;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        public List<Character> rotations;
        private State prev;

        public State(RubiksCube cube, int moves, List<Character> rotations, State prev) {
            this.cube = cube;
            this.moves = moves;
            this.rotations = rotations;
            this.prev = prev;
            // TODO
            cost = moves + cube.manhattan(cube) ;
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).cube.equals(this.cube);
        }

        public int compareTo(State s) {
            return this.cost - s.cost;
        }

        @Override
        public int hashCode() {
            return this.cube.hashCode();
        }
    }

    public static void main(String[] args){
        RubiksCube cube = new RubiksCube();
//        manhattan(cube);
    }
}