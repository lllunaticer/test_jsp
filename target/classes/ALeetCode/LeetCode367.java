package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-04
 */
public class LeetCode367 {
    public boolean isPerfectSquare(int num) {
        long lo = 0;
        long hi = num;
        long mid;

        while (hi > lo) {
            mid = lo + hi + 1 >> 1;
            long result = mid * mid;
            if (result == num) {
                return true;
            } else if (mid * mid < num) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode367 leetCode367 = new LeetCode367();
    }
}
