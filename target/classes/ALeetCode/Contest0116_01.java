package ALeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-01-16
 */
public class Contest0116_01 {
    public long maxRunTime(int n, int[] batteries) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int[] tmp = new int[n];
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int b : batteries) {
            min = Math.min(min, b);
            if (b > min && b < secondMin) {
                secondMin = b;
            }
            queue.offer(b);
        }
        long res = 0;
        int diff = secondMin == Integer.MAX_VALUE ? 1 : secondMin - min;
        while (true) {
            for (int i = 0; i < n; i++) {
                Integer t = queue.poll();
                tmp[i] = t - diff;
                if (tmp[i] < 0) {
                    return res;
                }
                min = Math.min(min, tmp[i]);
                secondMin = tmp[i] > min && tmp[i] < secondMin ? tmp[i] : secondMin;
            }
            for (int i = 0; i < n; i++) {
                queue.offer(tmp[i]);
            }
            res += diff;
            diff = secondMin == Integer.MAX_VALUE? 1: secondMin - min;
        }
    }

    @Test
    public void testMaxRunTime() {
        System.out.println(maxRunTime(3, new int[] {10, 10, 3, 5}));
    }
}
