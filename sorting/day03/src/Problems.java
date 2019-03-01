import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        // TODO
//        Find max and min
        int max = A[0];
        int min = A[0];
        for(int i = 0; i< A.length; i++){
            if(A[i]>max){
                max = A[i];
            }
            if(A[i]<min){
                min = A[i];
            }
        }
//        Sort elements based on index
        int arr[] = new int[max+Math.abs(min)+1];
        for(int i = 0; i<A.length; i++){
            arr[A[i]-min] = arr[A[i]-min]+1;
        }
//        Put sorted elements back in array
        int i = 0;
        int offset = Math.abs(min);
        for(int j= 0; j<arr.length; j++){
            while(arr[j] > 0){
                A[i] = j-offset;
//                System.out.print(A[i]+",");
                arr[j] -= 1;
                i++;

            }
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        // TODO
        LinkedList<String>[] L = new LinkedList[26];
        for (int i = 0; i < 26; i++)
            L[i] = new LinkedList<>();
        for (String s : A) {
//            System.out.println(getNthCharacter(s,n));
            // TODO: Extract the relevant digit from i, and add i to the corresponding Linked List.
            L[getNthCharacter(s, n)].add(s);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            // TODO: Put all numbers in the linked lists into A
            while(!list.isEmpty()){
                A[j] = list.pop();
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        // TODO
//        int w = 0; //max string length
//        for(int i = 0; i<S.length; i++){
//            if(S[i].length()>w){
//                w = S[i].length();
//            }
//        }
        // TODO: Perform radix sort
        for(int i = 0; i<stringLength; i++){
            countingSortByCharacter(S, i);
        }

    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
