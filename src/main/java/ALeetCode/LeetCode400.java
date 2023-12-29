package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-30
 */
public class LeetCode400 {
    public int findNthDigit(int n) {
        if (n <= 9) {
            return n;
        }
        long init = 9;
        long cnt = n;
        long times = 0;
        while (cnt > init) {
            cnt -= init;
            times++;
            init = 9 * ((long) Math.pow(10, times) * (times + 1));
        }


        long idx = cnt / (times + 1);
        long remains = cnt % (times + 1);

        long start = (long)Math.pow(10, times) - 1;

        start = start + idx;

        if (remains == 0) {
            return (int) (start % 10);
        } else {
            start += 1;
            return Integer.parseInt(String.valueOf(start).charAt((int) remains - 1) + "");
        }
    }

    public static void main(String[] args) {
        LeetCode400 leetCode400 = new LeetCode400();
        System.out.println(leetCode400.findNthDigit(10000));

    }
}
