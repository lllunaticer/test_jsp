package StringTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-09-27
 */
public class CastTest {
    public static final String MUSIC_CDN_PATH_PREFIX = "/udata/music/";

    @Test
    public void testCast() {
        Object i = 0;
        //        String iStr = (String) i;
        String iStr2 = String.valueOf(i);
    }

    @Test
    public void testSubstring() {
        String url = "/udata/music/track_4479859c-8b57-4db4-a31a-e67098207592.mp3";
        if (url.startsWith(MUSIC_CDN_PATH_PREFIX)) {
            System.out.println(url.substring(MUSIC_CDN_PATH_PREFIX.length()));
        }
    }

    @Test
    public void testLong() {
        String cntStr = "4";
        boolean res = NumberUtils.isDigits(cntStr) && ((NumberUtils.toLong(cntStr, 0L)) < 1);
        System.out.println(res);
    }

    @Test
    public void testExchange() {
        int[] nums = new int[] {3, 16, 2, 5, 13, 1, 16, 1, 12, 18, 11, 8, 11, 11, 5, 1};
        System.out.println(Arrays.toString(exchange(nums)));
    }

    private int[] numsInternel;

    public int[] exchange(int[] nums) {
        numsInternel = nums;
        int oddIdx = findNext(-1, 1);
        int evenIdx = findNext(-1, 0);
        while (evenIdx >= 0 && oddIdx >= 0) {
            if (oddIdx < evenIdx) {
                oddIdx = findNext(oddIdx, 1);
            } else {
                swap(oddIdx, evenIdx);
                oddIdx = findNext(oddIdx, 1);
                evenIdx = findNext(evenIdx, 0);
            }
        }
        return numsInternel;
    }

    private int findNext(int idx, int mod) {
        while (++idx < numsInternel.length) {
            if (numsInternel[idx] % 2 == mod) {
                return idx;
            }
        }

        return -1;
    }

    @Test
    public void testInt() {
        long sum = IntStream.range(0, 100)
                .mapToLong(i -> 20)
                .count();
        System.out.println(sum);
        List<Long> res = new ArrayList<>();
        long sum1 = res.stream().count();
        System.out.println(sum1);
    }

    private void swap(int lt, int rh) {
        int tmp = numsInternel[lt];
        numsInternel[lt] = numsInternel[rh];
        numsInternel[rh] = tmp;
    }

    @Test
    public void testMath(){
        int progress = 0;
        int afterFlashSaleProductAmount = 99;
        int currentHighestThreshold = 91;
        int couponThreshold = 100;
        double ration = (double) (afterFlashSaleProductAmount - currentHighestThreshold) / (
                couponThreshold - currentHighestThreshold) * 100;
        System.out.println(ration);
        progress = (int) Math.floor(ration);
        System.out.println(progress);
    }
}
