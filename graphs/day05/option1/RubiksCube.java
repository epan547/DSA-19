import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Character.isUpperCase;

// use this class if you are designing your own Rubik's cube implementation
public class RubiksCube {
    public HashMap<String, int[]> faces;

//    public RubiksCube current = new RubiksCube();
    public List<Character> moves = new ArrayList<Character>();
    char[] options = new char[] {'u', 'U', 'r', 'R', 'f', 'F'};

    // initialize a solved rubiks cube
    public RubiksCube() {
        // TODO
        HashMap<String, int[]> faces = new HashMap<>();
        String[] names = new String []{"front", "back", "left", "right", "top", "bottom"};
        for(int i=0; i< 6; i++){
            int[] temp = new int[]{i,i,i,i};
            faces.put(names[i], temp);
        }

        this.faces = faces;
    }

    public static void printCube(HashMap<String, int[]> faces){
        String[] names = new String []{"front", "back", "left", "right", "top", "bottom"};

        for(Map.Entry i: faces.entrySet()){
            int[] temp = (int[]) i.getValue();
            String s = new String();
            for(int j:temp){
                s = s + j + ",";
            }
            System.out.println(i.getKey() + ":" + s);
        }

    }
    // creates a copy of the rubiks cube
    public RubiksCube(RubiksCube r) {
        // TODO
        this.faces = r.faces;
        this.moves = r.moves;
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;

        // TODO
        for(Map.Entry i: this.faces.entrySet()){
           if(!i.getValue().equals(other.faces.get(i.getKey()))){
               System.out.println("False");
               return false;
           }
        }
        return true;
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
        // TODO
        return faces.hashCode();
    }

    public boolean isSolved() {
        // TODO
        for(HashMap.Entry i: this.faces.entrySet()){
            int n = ((int[])i.getValue())[0];
            for(int x:(int[])i.getValue()){
                if(x != n){
                    return false;
                }
            }
        }
        return true;
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
        // TODO

        return this;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r = r.rotate(listTurns[i]);
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

    public void rotate_side(RubiksCube r, char side){
        int[] temp = new int[4];
        String face = "";
        if(side == 'u' || side == 'U'){
            face = "top";
            temp = r.faces.get("top");
        } else if (side == 'r' || side == 'R') {
            face = "right";
            temp = r.faces.get("right");
        } else if (side == 'f' || side == 'F'){
            face = "front";
            temp = r.faces.get("front");
        }

        if(isUpperCase(side)){
            r.faces.put(face, rotate_left(temp));
        }
        else{
            r.faces.put(face, rotate_right(temp));
        }
    }

    public int[] rotate_right(int[] face){
        int[] new_face = new int[4];
        new_face[0] = face[2];
        new_face[1] = face[0];
        new_face[2] = face[3];
        new_face[3] = face[1];
        return new_face;
    }

    public int[] rotate_left(int[] face){
        int[] new_face = new int[4];
        new_face[0] = face[1];
        new_face[1] = face[3];
        new_face[2] = face[0];
        new_face[3] = face[2];
        return new_face;
    }

    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
        // TODO
        if(this.isSolved()){
            return this.moves;
        }

        Queue<RubiksCube> queue = new LinkedList<>();
        queue.add(this);

        while(!queue.isEmpty()){
            RubiksCube curr = queue.remove();

            // base case
            if(curr.isSolved()){
                return curr.moves;
            }

           queue.addAll(this.getNeighbors(this));
        }

        return null;
    }

    public LinkedList<RubiksCube> getNeighbors(RubiksCube r){
        LinkedList<RubiksCube> n = new LinkedList<>();
        // double check that this is ok
        RubiksCube temp = r;

        for(char i: options){
            temp.rotate(i);
            n.add(temp);
            temp = r;
        }
        return n;

    }

    public static void main(String[] args){
        RubiksCube cube = new RubiksCube();

        printCube(cube.faces);
    }

}



