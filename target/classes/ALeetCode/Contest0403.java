package ALeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-04-03
 */
public class Contest0403 {
    @Test
    public void testContest() {
        System.out.println(convertTime("02:30", "04:35"));
    }

    public int convertTime(String current, String correct) {
        String[] currentStr = current.split(":");
        int currentH = Integer.parseInt(currentStr[0]);
        int currentM = Integer.parseInt(currentStr[1]);

        String[] correctStr = correct.split(":");
        int correctH = Integer.parseInt(correctStr[0]);
        int correctM = Integer.parseInt(correctStr[1]);

        if (currentH > correctH) {
            correctH += 24;
        }

        int currentMT = currentH * 60 + currentM;
        int correctMT = correctH * 60 + correctM;

        int diff = correctMT - currentMT;
        int cnt = 0;
        cnt += diff / 60;
        diff %= 60;
        cnt += diff / 15;
        diff %= 15;
        cnt += diff / 5;
        diff %= 5;
        cnt += diff;

        return cnt;
    }

    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> win = new HashSet<>();
        Set<Integer> loseOne = new HashSet<>();
        Set<Integer> loser = new HashSet<>();

        List<List<Integer>> res = new ArrayList<>();

        for (int[] tmp : matches) {
            if (!loseOne.contains(tmp[0]) && !loser.contains(tmp[0])) {
                win.add(tmp[0]);
            }
            win.remove(tmp[1]);
            if (loser.contains(tmp[1])) {
            } else if (loseOne.contains(tmp[1])) {
                loseOne.remove(tmp[1]);
                loser.add(tmp[1]);
            } else {
                loseOne.add(tmp[1]);
            }
        }

        res.add(win.stream().sorted().collect(Collectors.toList()));
        res.add(loseOne.stream().sorted().collect(Collectors.toList()));
        return res;
    }

    @Test
    public void testFindWinners() {
        int[][] ints = {{2, 8}, {3, 8}, {6, 8}};
        System.out.println(findWinners(ints));
    }

    private int n;

    public int maximumCandies(int[] candies, long k) {
        this.n = candies.length;
        long sum = Arrays.stream(candies).max().getAsInt();
        int l = 0, r = (int) (sum / k);
        while (l < r) {
            int mid = 1 + l + (r - l) / 2;
            if (check(candies, mid, k)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int[] candies, long mid, long k) {
        long res = 0;
        for (int candy : candies) {
            res += candy / mid;
        }
        return res >= k;
    }
}
