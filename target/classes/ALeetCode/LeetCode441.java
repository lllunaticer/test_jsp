package ALeetCode;

import com.google.common.collect.ImmutableMap;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-10
 */
public class LeetCode441 {
    public int arrangeCoins(int n) {
        double sqrt = Math.sqrt(2 * n + 0.25);
        sqrt = sqrt - 0.5;
        return (int) sqrt;
    }

    public static void main(String[] args) {
        LeetCode441 leetCode441 = new LeetCode441();
        leetCode441.arrangeCoins(1804289383);
    }
}
