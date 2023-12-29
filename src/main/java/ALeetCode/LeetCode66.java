package ALeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-21
 */
public class LeetCode66 {
    public int[] plusOne(int[] digits) {
        long sum = 0;
        for (int i : digits) {
            sum = sum * 10 + i;
        }
        sum += 1;
        List<Integer> res = new ArrayList<>();
        while (sum > 0) {
            res.add((int)(sum % 10));
            sum /= 10;
        }
        int[] resArray = new int[res.size()];
        int i = res.size() - 1;
        for (int num : res) {
            resArray[i--] = num;
        }
        return resArray;
    }

    public static void main(String[] args) {
        LeetCode66 leetCode66 = new LeetCode66();
        leetCode66.plusOne(new int[]{9,8,7,6,5,4,3,2,1,0});
    }
}
