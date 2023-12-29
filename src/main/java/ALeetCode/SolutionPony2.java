package ALeetCode;

import java.util.Arrays;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-07
 */
public class SolutionPony2 {
    public int minimizedMaximum(int n, int[] quantities) {
        Arrays.sort(quantities);
        int max = quantities[quantities.length - 1];

        int lo = 0;
        int hi = max;
        int mid;
        while (hi > lo) {
            mid = (lo + hi) / 2;
            if (!check(quantities, mid, n)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private boolean check(int[] quantities, int max, int n) {
        return cntAmount(quantities, max) <= n;
    }

    private int cntAmount(int[] quantities, int max) {
        int cnt = 0;
        for (int i : quantities) {
            cnt += (i + max - 1) / max;
        }
        return cnt;
    }

    public static void main(String[] args) {
        SolutionPony2 solutionPony2 = new SolutionPony2();
        System.out.println(solutionPony2.minimizedMaximum(1, new int[] {100000}));
    }
}
