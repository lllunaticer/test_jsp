package ALeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-28
 */
public class LeetCode869 {
    public boolean reorderedPowerOf2(int n) {
        List<Integer> element = new ArrayList<>();
        while (n > 0) {
            element.add(n % 10);
            n /= 10;
        }
        Integer[] elements = new Integer[element.size()];
        element.toArray(elements);
        boolean[] res = new boolean[1];
        dfs(0, elements, 0, res);
        return res[0];
    }

    private void dfs(int cnt, Integer[] element, int value, boolean[] res) {
        if (cnt == element.length) {
            res[0] = res[0] || isPower2(value);
            return;
        }
        for (int i = 0; i < element.length; i++) {
            if (element[i] == -1) {
                continue;
            }
            if (cnt == 0 && element[i] == 0) {
                continue;
            }
            value = value * 10 + element[i];
            int tmp = element[i];
            element[i] = -1;
            cnt++;
            dfs(cnt, element, value, res);
            cnt--;
            value /= 10;
            element[i] = tmp;
        }
    }

    private boolean isPower2(int num) {
        return num > 0 && (num & (num-1)) == 0;
    }

    public static void main(String[] args) {
        LeetCode869 leetCode869 = new LeetCode869();
        System.out.println(leetCode869.reorderedPowerOf2(64));
    }
}
