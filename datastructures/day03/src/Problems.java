import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        // TODO: DO WITH A STACK
        // For now, return a List that's correct size, but contains only 0s
        LinkedList<Integer> sol = new LinkedList<>();
        int blanks = A.length - k;
        int index = 0;
        while(blanks > 0) {
            int min = 10;
            int end = A.length;
            if (A.length == blanks) {
                for (int i = 0; i < A.length; i++) {
                    sol.add(A[i]);
                }
                return sol;
            }

            for (int i = index; i < (end-blanks+1); i ++) {
                if (A[i] < min) {
                    min = A[i];
                    index = i;
                }
            }
            sol.add(min);
            blanks --;
            index ++;
        }

        return sol;
    }

    //O(2N) solution rounds down to O(N)
    public static boolean isPalindrome(Node n) {

        // Edge case you're given an empty list
        if(n == null) {
            return true;
        }

        // O(N) operation to find tail
        int count = 0;
        int mid;
        Node head = n;

        // Find tail and add up length of linked list
        while(n.next != null) {
            n = n.next;
            count ++;
        }

        // Edge case when list is one element long
        if (count == 0) {
            return true;
        }

        // Calculate midpoint
        Node tail = n;
        mid = count/2;

        // Case when count = 2
        if (count == 2) {
            if (head.val == tail.val) {
                return true;
            }
        }

        // Get to one before the midpoint
        n = head;
        for(int i=0; i<mid; i++) {
            n = n.next;
        }

        // Reverse all nodes after the midpoint O(N/2)
        Node prev = n;
        Node curr = n.next;
        Node after = n.next.next;
        while(curr != tail) {
            curr.next = prev;
            prev = curr;
            curr = after;
            System.out.println(curr.val);
            if (curr.next==null) {
                curr.next = prev;
                break;
            }
            after = curr.next;
        }

        // Check front vs. back moving inwards to see if palindrome
        Node front = head;
        Node back = tail;
        for (int i=0; i<mid; i++) {
            System.out.println(front.val + " ");
            if (front.val != back.val) {
                return false;
            }
            front = front.next;
            back = back.next;
        }

        return true;
    }

    public static String infixToPostfix(String s) {
        // TODO: DO WITH A STACK
        Stack<Character> stack = new Stack<>();
        String sol = "";
        int index = 0;

        while (index < s.length()) {
            char c = s.charAt(index);
            if (c == ')'){
                sol += stack.pop();
                sol += " ";
            }
            if (c >= 48 && c <= 57) {
                sol += c + " ";
            }

            if (c == '*' || c == '+' || c == '-' || c == '/') {
                stack.add(c);
            }
            index ++;

        }
        return sol.trim();
    }

}
