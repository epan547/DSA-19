public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1]) {
            return peakOneD(i - 1, nums);
        } else if (i < nums.length - 1 && nums[i] < nums[i + 1]) {

            return peakOneD(i + 1, nums);
        } else {
            return i;
        }
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        // TODO
        int n = nums.length / 2;
        int peak = peakOneD(n, nums);
        return peak;
    }

    public static int[] findTwoDPeak(int[][] nums) {
        // TODO

        int xlow = 0;
        int xhigh = nums.length-1;
        int ylow = 0;
        int yhigh = nums.length-1;
        int xmid = (xlow + xhigh) / 2;
        int ymid = xmid;

        boolean up = false;

//        mid must be > 0, < length
        while (xhigh < nums.length && xlow >= 0 && yhigh < nums.length && ylow >= 0 && xhigh > xlow && yhigh > ylow) {
            if ((peakY(xmid, ymid, nums) == 0) && (peakX(xmid, ymid, nums) == 0)) {
                int[] ans = new int[2];
                ans[0] = xmid;
                ans[1] = ymid;
                return ans;
            }

            if(!up){
                if (peakX(xmid, ymid, nums) == -1) {
                    xhigh = xmid -1;
                    xmid = (xlow + xhigh +1)/2;
                    System.out.println("currX = " + xmid);
                } else if (peakX(xmid, ymid, nums) == 1) {
                    xlow = xmid +1;
                    xmid = (xlow + xhigh +1)/2;
                    System.out.println("currX = " + xmid);
                }
                up = true;
            }
            else{
                if (peakY(xmid, ymid, nums) == -1) {
                    yhigh = ymid -1;
                    ymid = (ylow + yhigh +1)/2;
                    System.out.println("currY = " + ymid);
                } else if (peakY(xmid, ymid, nums) == 1) {
                    ylow = ymid +1;
                    ymid = (ylow + yhigh +1)/2;
                    System.out.println("currY = " + ymid);
                }
                up = false;

            }

//            System.out.println(nums[xmid][ymid-1] + " " +  nums[xmid][ymid] + " " + nums[xmid][ymid+1]);

        }

        if ((peakY(xmid, ymid, nums) == 0) && (peakX(xmid, ymid, nums) == 0)) {
            int[] ans = new int[2];
            ans[0] = xmid;
            ans[1] = ymid;
            return ans;
        }

        System.out.println("help");

        return null;
//        int[] ans = new int[2];
//        ans[0] = currX;
//        ans[1] = currY;
//        return ans;
    }

}
