package ALeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-07
 */
public class SolutionPony3 {
    private static final Set<Character> target = new HashSet<>();

    static {
        target.add('a');
        target.add('e');
        target.add('i');
        target.add('o');
        target.add('u');
    }

    public long countVowels(String word) {
        char[] chars = word.toCharArray();
        int res = 0;
        for(int i = 0; i<chars.length; i++){
            if(target.contains(chars[i])){
                res += (i+1)*(chars.length-i);
            }
        }
        return res;
    }
}
