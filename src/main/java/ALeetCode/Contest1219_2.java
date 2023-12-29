package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-19
 */
public class Contest1219_2 {
    public static void main(String[] args) {
        Contest1219_2 contest1219_2 = new Contest1219_2();
        contest1219_2.getDescentPeriods(new int[]{12,11,10,9,8,7,6,5,4,3,4,3,10,9,8,7});
    }

    public long getDescentPeriods(int[] prices) {
        int res = 0;

        int left = 0;
        int right = left + 1;
        while(right < prices.length){
            if(prices[right] - prices[right - 1] == -1){
                right++;
            }
            else{
                int len = right - left;
                res += len*(len-1)/2;
                left = right;
                right = left + 1;
            }
        }

        int len = right - left;
        res += len*(len-1)/2;
        res += prices.length;
        return res;
    }
}
