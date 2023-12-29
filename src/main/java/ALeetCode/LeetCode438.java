package ALeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-29
 */
public class LeetCode438 {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }

        int[] target = new int[26];
        int[] windows = new int[26];
        for (char c : p.toCharArray()) {
            target[c - 'a']++;
        }
        Random random = new Random();
        random.nextInt();

        int len = p.length();
        char[] sChars = s.toCharArray();
        int index = 0;
        for (; index < len - 1; index++) {
            windows[sChars[index] - 'a']++;
        }
        List<Integer> res = new ArrayList<>();

        while (index < s.length()) {
            windows[sChars[index] - 'a']++;
            if (isTwoArrayEquals(target, windows)) {
                res.add(index - len + 1);
            }
            windows[sChars[index - len + 1] - 'a']--;
            index++;
        }

        return res;
    }

    private boolean isTwoArrayEquals(int[] c1, int[] c2) {
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode438 leetCode438 = new LeetCode438();
        System.out.println(leetCode438.findAnagrams("cbaebabacd", "abc"));
    }
}
