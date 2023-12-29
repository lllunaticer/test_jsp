package ALeetCode;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-03-27
 */
public class Contest0328_2 {
    @Test
    public void tesFind() {
        System.out.println(Arrays.toString(
                kthPalindrome(new int[] {492605370, 206710368, 19, 985427531, 55, 13, 979243001, 831564215, 83}, 15)));
    }

    public long[] kthPalindrome(int[] queries, int intLength) {
        int[] cnts = new int[] {
                9, 9, 90, 90, 900, 900, 9000, 9000, 90000, 90000, 900000, 900000, 9000000, 9000000, 9000000
        };
        long[] res = new long[queries.length];
        long count = 0;
        for (int i = 0; i < intLength - 1; i++) {
            count += cnts[i];
        }
        long max = 1;
        for (int i = intLength; i > 0; i--) {
            max *= 10;
        }
        for (int i = 0; i < queries.length; i++) {
            long temRes = find(count + queries[i] - 1);
            res[i] = temRes > max ? -1 : temRes;
        }
        return res;
    }

    public long find(long index) {
        int count = 0;
        int number = 9;                        //记录数位上的回文数，如个位回文数为9
        int w = 0;                            //记录数位

        long half;                            //保存回文数的左半边的结果
        long h = 1;                            //回文数的左半边的起始基数
        long res;                            //结果

        while (true) {
            if (w > 0 && w % 2 == 0) {            //每进两个数位，回文数乘以10
                number *= 10;
            }
            w++;                            //数位加一
            if (count + number > index)        //回文数大于查找的回数,跳出
            {
                break;
            }

            count += number;                //回文数加上当前数位上的回文数
        }

        index -= count;                        //在当前数位上的位置。如w=5,index=50,则万位上的第50个回文数是我们所求

        for (int i = 0; i < (w - 1) / 2; i++) {    //求回文数的左半边的基数，如回文数在万位上，则为100
            h *= 10;
        }

        half = h + index;                        //回文数的左半边，如100 + 50 = 150

        res = half;

        if (w % 2 != 0)                            //如果为奇数，则中间那个数不必算入右半边了！
        {
            half /= 10;
        }

        while (half != 0) {                        //拼接回文数
            res = res * 10 + half % 10;
            half /= 10;
        }

        return res;
    }
}
