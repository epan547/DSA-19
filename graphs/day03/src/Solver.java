/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO
            cost = moves + board.manhattan();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        // TODO: Your code here
        if(state.prev == null){
            return state;
        }

        return root(state.prev);
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        // TODO: Your code here
        State initState = new State(initial,0,null);
        solutionState = new State(initial,0,null);
        PriorityQueue<State> PQ = new PriorityQueue<>((o1, o2) -> {return o1.cost - o2.cost;});
        PQ.add(initState);

        HashMap<State, State> open = new HashMap<>();
        HashMap<State, State> closed = new HashMap<>();

        open.put(initState, initState);

        if(!isSolvable()){
            solved = false;
            return;
        }
        // PQ is mechanism to find next least cost node
        // The open set and closed set are adding a layer of complexity/checks on top of PQ to make sure you find optimal node
        // Can be thought of as using heuristic of goal
        // In Dijkstra, you only care about heuristic from start
        // A* depends on the open set (set of available nodes you can still look at)
        // closed set is the set of nodes you have fully explored
        // for every neighbor, check if it exists in the two sets, and whether it has a smaller cost than the current existing
        // if it has a smaller cost, put in open set. Otherwise, pass
        // put current node into closed set at the end
        // add to the PQ when you add to the open set



        // key board, value moves in hashmap visited
        // compare # of moves to get there with # of moves to get to the current neighbor
        while (!open.isEmpty()){
            State curr = PQ.poll();
//            System.out.println(Arrays.deepToString(curr.board.tiles));

            if(curr.board.isGoal()){
//               Base case: return the path if goal is reached
                solutionState = curr;
                minMoves = curr.moves;
                solved = true;
//                System.out.println("Moves: " + minMoves);
//                System.out.println("Boards " );
                return;
            }

            for(Board n:curr.board.neighbors()){
                State next = new State(n,curr.moves+1,curr);
//                System.out.println(Arrays.deepToString(next.board.tiles));
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

    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        // TODO: Your code here
        return solutionState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        // TODO: Your code here
        if(solutionState != null){
            LinkedList<Board> list = new LinkedList<>();
            State current = root(solutionState);
            while(current != solutionState){
                list.add(current.board);
//                System.out.println(Arrays.deepToString(current.board.tiles));
            }
            list.add(solutionState.board);
            Iterable<Board> it = list;
            return it;
        }
        else{
            return null;
        }
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);

        System.out.println(solver.isSolvable());
    }


}
