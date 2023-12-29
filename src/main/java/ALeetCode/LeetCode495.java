package ALeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-10
 */
public class LeetCode495 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        Arrays.sort(timeSeries);
        int sum = 0;
        int left = timeSeries[0];
        int right = left + duration - 1;
        for (int i = 1; i < timeSeries.length; i++) {
            if (right < timeSeries[i]) {
                sum += right - left + 1;
                left = timeSeries[i];
                right = left + duration - 1;
            } else {
                right = timeSeries[i] + duration - 1;
            }
        }
        sum += right - left + 1;
        return sum;
    }

    public static void main(String[] args) {
        LeetCode495 leetCode495 = new LeetCode495();
        System.out.println(leetCode495.findPoisonedDuration(new int[] {1, 2}, 2));
    }
}
