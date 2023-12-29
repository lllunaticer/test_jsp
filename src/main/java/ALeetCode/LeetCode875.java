package ALeetCode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-21
 */
public class LeetCode875 {
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int hi = piles[piles.length - 1];
        int lo = 1;
        int t;
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            t = calTimes(piles, mid);
            if (t > h) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private int calTimes(int[] piles, int k) {
        int sum = 0;
        for (int i : piles) {
            sum += i / k;
            if (i % k != 0) {
                sum += 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        LeetCode875 leetCode875 = new LeetCode875();
        System.out.println(leetCode875.minEatingSpeed(new int[] {30,11,23,4,20}, 5));
    }

}
