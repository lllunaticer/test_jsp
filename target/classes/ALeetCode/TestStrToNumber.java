package ALeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-08-24
 */
public class TestStrToNumber {
    public int strToInt(String str) {
        if (Objects.equals(str, "") || str == null) {
            return 0;
        }
        int i = 0;
        while (str.charAt(i) == ' ') {
            i++;
            if (i >= str.length()) {
                return 0;
            }
        }
        char firstChar = str.charAt(i);
        if ((firstChar > '9' || firstChar < '0') && (firstChar != '+' && firstChar != '-')) {
            return 0;
        }
        int flag = firstChar == '-' ? -1 : 1;
        long res = 0;
        if (firstChar >= '1' && firstChar <= '9') {
            res += (firstChar - 48);
        }
        i++;
        while (i < str.length()) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                res = res * 10 + str.charAt(i) - 48;
                if ((flag * res > Integer.MAX_VALUE) || (flag * res < Integer.MIN_VALUE)) {
                    return flag < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
            } else {
                break;
            }
            i++;
        }
        res *= flag;
        if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) res;
    }

    @Test
    public void test() {
        System.out.println(strToInt(" "));
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int left = 0, right = prices.length - 1;
        while (left + 1 < prices.length && prices[left + 1] < prices[left]) {
            left++;
        }
        while (right - 1 > left && prices[right - 1] > prices[right]) {
            right--;
        }
        int maxProfit = prices[right] - prices[left];
        while (left < right && left + 1 < prices.length) {
            maxProfit = Math.max(maxProfit, prices[left + 1] - prices[left]);
            left++;
            while (left + 1 < prices.length && prices[left + 1] < prices[left]) {
                left++;
            }
        }
        return maxProfit;
    }

    @Test
    public void testGeneric() {
        List<Map> lst = new ArrayList<>();
        lst.add(ImmutableMap.of());
    }

    @Test
    public void testMaxProfit() {
        maxProfit(new int[] {2, 1, 2, 1, 0, 1, 2});
    }
}
