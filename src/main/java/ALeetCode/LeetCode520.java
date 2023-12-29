package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-13
 */
public class LeetCode520 {
    public boolean detectCapitalUse(String word) {
        char[] words = word.toCharArray();
        char first = words[0];
        if (first >= 'A' && first <= 'Z') {
            if (words.length >= 2) {
                char second = words[1];
                if (second >= 'A' && second <= 'Z') {
                    for (int i = 2; i < words.length; i++) {
                        if (words[i] >= 'a' && words[i] <= 'z') {
                            return false;
                        }
                    }
                } else {
                    for (int i = 2; i < words.length; i++) {
                        if (words[i] >= 'A' && words[i] <= 'Z') {
                            return false;
                        }
                    }
                }
            }
        } else {
            for (char c : words) {
                if (c >= 'A' && c <= 'Z') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println('a' - 'A');
    }
}
