import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    private static void printboard(char[][] A){
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A.length;j++){
                System.out.print(A[i][j]);
            }
            System.out.println(' ');
        }
        System.out.println(' ');
    }


    public static List<char[][]> nQueensSolutions(int n) {
        // Start at the top row of the board, and try to assign a queen to each column.
        // Pass along which row you are currently assigning as a variable in your recursive function.
        // in helper function, use Hash Set to store which columns contain queens
        List<char[][]> answers = new ArrayList<>();
        char[][] current = new char[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                current[i][j] = '.';
            }
        }
        HashSet<Integer> columns = new HashSet<>();
        QueensHelper(n,0,current,columns,answers);
        return answers;
    }

    public static void QueensHelper(int n, int row, char[][] current, HashSet<Integer> columns, List<char[][]> answers){

        if(row == n){
            // Making a copy of the original lists, to send to the next function call
            // copy board, add to answers.
            char[][] temp = copyOf(current);
            answers.add(temp);
        }
//        printboard(current);

        //iterate through columns in the board
        for(int i=0;i<n;i++){
//            System.out.println(checkDiagonal(current,row,i));
//            System.out.println(current);
            if(!checkDiagonal(current,row,i) && !columns.contains(i)){ // also check if this column is in columns
                //place queen, add to columns and board
                columns.add(i);
                current[row][i] = 'Q';

                QueensHelper(n,row+1,current,columns,answers);

                //remove queen, add to unused
                current[row][i] = '.';
                columns.remove(i);
            }
        }
    }

}
