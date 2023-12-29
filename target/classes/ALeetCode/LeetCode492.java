package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-23
 */
public class LeetCode492 {
    public int[] constructRectangle(int area) {
        int sqrt = (int) Math.sqrt(area);
        int[] res = new int[2];
        int a = 0;
        int b = 0;
        for (int i = sqrt; i > 0; i--) {
            if (area % i == 0) {
                a = i;
                b = area / i;
                res[0] = Math.max(a, b);
                res[1] = Math.min(a, b);
                break;
            }
        }
        return res;
    }
}
