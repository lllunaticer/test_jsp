package ALeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-08-13
 */
public class PointOffer48 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dups = new HashMap<>();
        int j = -1, res = 0;
        List<Integer> k = new LinkedList<>();
        Queue<Integer> queue =  new LinkedList<>();
        queue.offer(1);
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if (dups.containsKey(tmp)) {
                j = dups.get(tmp);
            }
            dups.put(tmp, i);
            res = Math.max(res, i - j);
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("abba"));
    }
}
