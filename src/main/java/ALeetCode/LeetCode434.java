package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-13
 */
public class LeetCode434 {

    public int countSegments(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length;i++) {
            if (isLetter(chars[i])) {
                result++;
                while (i<chars.length && isLetter(chars[i])){
                    i++;
                }
            }
        }
        return result;
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static void main(String[] args) {
        LeetCode434 leetCode434 = new LeetCode434();
        System.out.println(leetCode434.countSegments("a, b, c"));
    }
}
