package ALeetCode;

import java.util.Arrays;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-28
 */
public class Contest1128_02 {
    public int[] getAverages(int[] nums, int k) {
        int len = nums.length;

        long[] prefixSum = new long[len + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i-1];
        }

        int[] avg = new int[len];
        for (int i = 0; i < len; i++) {
            int left = i - k;
            int right = i + k;
            if (left < 0 || right >= len) {
                avg[i] = -1;
            } else {
                avg[i] = (int) ((prefixSum[right+1] - prefixSum[left]) / (2 * k + 1));
            }
        }
        return avg;
    }

    public static void main(String[] args) {
        Contest1128_02 contest1128_02 = new Contest1128_02();
        Arrays.stream(contest1128_02.getAverages(new int[] {7, 4, 3, 9, 1, 8, 5, 2, 6}, 3)).forEach(i-> System.out.println(i));
    }
}
