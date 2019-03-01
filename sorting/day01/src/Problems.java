import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     * if element is > median, add to minpq, if < median, add to maxpq
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        // TODO
        PriorityQueue<Integer> maxPQ = maxPQ();
        PriorityQueue<Integer> minPQ = minPQ();
//        Two possible median vars, depending on whether array is even or odd at moment
        if(inputStream.length < 1){
            return runningMedian;
        }
        if(inputStream.length == 1){
            runningMedian[0] = inputStream[0];
            return runningMedian;
        }
        runningMedian[0] = inputStream[0];
        maxPQ.add(inputStream[0]);

        for(int i = 1; i<inputStream.length; i++){
            int curr = inputStream[i];
            if(maxPQ.size() == minPQ.size()){
                if(curr > runningMedian[i-1]){
                    minPQ.offer(curr);
                }
                else{
                    maxPQ.offer(curr);
                }

                if(i == 1){
                    runningMedian[i] = (double) (inputStream[0] + inputStream[1])/2;
                }
                else if(maxPQ.size() > minPQ.size()){
                    runningMedian[i] = (double) maxPQ.peek();
                }
                else {
                    runningMedian[i] = (double) minPQ.peek();
                }
            }
            else {
//                pop one from max and put it in min
                if(curr > runningMedian[i-1]){
                    minPQ.add(curr);
                }
                else{
                    maxPQ.add(curr);
                }
                while(maxPQ.size() != minPQ.size()){
                    if(maxPQ.size() > minPQ.size()){
                        minPQ.add(maxPQ.poll());
                    }
                    else{
                        maxPQ.add(minPQ.poll());
                    }

                }
                runningMedian[i] = (double) (minPQ.peek() + maxPQ.peek())/2;
            }
            }

        for(int j = 0; j < runningMedian.length; j++){
            System.out.print(runningMedian[j] + ",");
        }
        System.out.println(" ");
        return runningMedian;
    }

}
