import java.lang.reflect.Array;
import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
//        BinarySearchTree<Integer> BST = new BinarySearchTree();
//
//        if(values.isEmpty()){
//            return BST;
//        }
//        if(values.size() == 1){
//            BST.add(values.get(0));
//            return BST;
//        }
//        Object[] sorted = values.toArray();
//        Arrays.sort(sorted);
//
//        addMedian(sorted, BST, sorted.length-1, 0);
//
        return null;
    }

//    private static void addMedian(Object[] sorted, BinarySearchTree<Integer> BST, int hi, int lo){
//        int halflen = (hi-lo)/2;
//        int med = (int) sorted[halflen];
//        BST.add(med);
//        if(halflen > lo && halflen < hi){
//            addMedian(sorted, BST, halflen-1, lo);
//        }
//        if(halflen < hi && halflen > 0){
//            addMedian(sorted, BST, hi, halflen+1);
//        }
//        return;
//
//    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
//        if(n1 == null || n2 == null){
//            return false;
//        }
        if(n1.isLeaf() && n2.isLeaf()){
            if(n1.equals(n2)){
                return true;
            }
            return false;
        }
        if(n1.rightChild.equals(n2.rightChild) && n1.leftChild.equals(n2.leftChild)){
            if(isIsomorphic(n1.rightChild, n2.rightChild) && isIsomorphic(n1.leftChild, n2.leftChild)){
                return true;
            }
        }
        if(n1.rightChild.equals(n2.leftChild) && n1.leftChild.equals(n2.rightChild)){
            if(isIsomorphic(n1.rightChild, n2.leftChild) && isIsomorphic(n1.leftChild, n2.rightChild)){
                return true;
            }
        }

        return false;
    }
}
