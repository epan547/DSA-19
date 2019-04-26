public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        // TODO
        long sol = helper(0, n, isBadVersion);

        return sol;
    }

    public static long helper(long l, long r, IsFailingVersion isBadVersion){
        // binary search function

        if(r >= l){
            long m = l + (r-l) /2;

            if(isBadVersion.isFailingVersion(m) && !isBadVersion.isFailingVersion(m-1)){
                return m;
            }

            if(isBadVersion.isFailingVersion(m)){
                return helper(l, m-1, isBadVersion);
            }
            else{
                return helper( m+1, r, isBadVersion);
            }
        }
        // nothing is broken
        return -1;
    }
}
