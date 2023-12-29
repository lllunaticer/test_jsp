package ALeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-17
 */
public class LeetCode318 {
    public int maxProduct(String[] words) {
        String ss = "ss";
        int maxLength = Integer.MIN_VALUE;
        List<List<Character>> wordsList = Arrays.stream(words)
                .map(
                        word -> {
                            List<Character> res = new ArrayList<>();
                            for (char c : word.toCharArray()) {
                                res.add(c);
                            }
                            return res;
                        }
                )
                .collect(Collectors.toList());

        for (int i = 0; i < wordsList.size() - 1; i++) {
            for (int j = i + 1; j < wordsList.size(); j++) {
                if (isDisjoin(wordsList.get(i), wordsList.get(j))) {
                    int len = wordsList.get(i).size() * wordsList.get(j).size();
                    maxLength = Math.max(len, maxLength);
                }
            }
        }

        return maxLength;
    }

    private boolean isDisjoin(List<Character> first, List<Character> second) {
        return Collections.disjoint(first, second);
    }

    public static void main(String[] args) {
        int i = 0;
        System.out.println(Integer.toBinaryString(i));
        i |= (1 << 'a');
        System.out.println(Integer.toBinaryString(i));
        System.out.println((int)('a'));
    }
}
