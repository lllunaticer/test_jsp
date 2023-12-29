package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-19
 */
public class Contest1219 {
    public static void main(String[] args) {

    }

    public String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
        int spacesIdx = 0;
        int sIdx = 0;
        while (sIdx < s.length()) {
            if (spaces[spacesIdx] == sIdx) {
                sb.append(" ");
                spacesIdx++;
            }
            sb.append(s.charAt(sIdx));
            sIdx++;
        }
        return sb.toString();
    }
}
