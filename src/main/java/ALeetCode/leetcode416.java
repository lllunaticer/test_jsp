package ALeetCode;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-04-16
 */
public class leetcode416 {
    @Test
    public void testGivegem() {
        int[] gem = new int[] {100, 0, 50, 100};
        int[][] op = new int[][] {{0, 2}, {0, 1}, {3, 0}, {3, 0}};
        giveGem(gem, op);

    }


    public int giveGem(int[] gem, int[][] operations) {
        if (operations.length == 0) {
            return 0;
        }
        long min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;
        for (int[] op : operations) {
            int half = gem[op[0]] / 2;
            gem[op[0]] = gem[op[0]] - half;
            gem[op[1]] = gem[op[1]] + half;
            min = Math.min(min, gem[op[0]]);
            max = Math.max(max, gem[op[1]]);
        }
        return (int) (max - min);
    }

    public String digitSum(String s, int k) {
        char[] c = s.toCharArray();
        String res = "";
        int tmp = 0;
        for (int i = 0; i < c.length; i++) {
            tmp += c[i] - '0';
            if ((i + 1) % k == 0) {
                res += String.valueOf(tmp);
                tmp = 0;
            }
        }
        if (c.length % k != 0) {
            res += String.valueOf(tmp);
        }
        return res;
    }

    @Test
    public void testDig() {
        String s = "11111222223";
        int k = 3;
        System.out.println(digitSum(s, k));
    }

    @Test
    public void testMod() {
        System.out.println(2 % 3);
    }
}
