package ALeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-07
 */
public class SolutionPony {
    private static final Set<Character> target = new HashSet<>();

    static {
        target.add('a');
        target.add('e');
        target.add('i');
        target.add('o');
        target.add('u');
    }

    public int countVowelSubstrings(String word) {
        char[] words = word.toCharArray();
        List<Character> stackList = new ArrayList<>();

        int cnt = 0;
        int left = 0;
        int right = 0;
        while (left < words.length) {
            if (stackList.containsAll(target)) {
                cnt++;
                if (right < words.length && target.contains(words[right])) {
                    stackList.add(words[right]);
                    right = right + 1;
                } else {
                    left = left + 1;
                    right = left;
                    stackList.clear();
                }
            } else {
                if (right < words.length && target.contains(words[right])) {
                    stackList.add(words[right]);
                    right = right + 1;
                } else {
                    left = right + 1;
                    right = right + 1;
                    stackList.clear();
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        SolutionPony solutionPony = new SolutionPony();
        System.out.println(solutionPony.countVowelSubstrings("cuaieuouac"));
    }
}
