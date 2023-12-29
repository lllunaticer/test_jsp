package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-18
 */
public class LeetCode476 {
    public int findComplement(int num) {
        int x = 0;
        for (int i = 0; i <= 31; i++) {
            if (num >>> i == 0) {
                x = i - 1;
                break;
            }
        }
        int mask = x == 30 ? 0x7fffffff : ((1 << (x + 1)) - 1);
        return num ^ mask;
    }


    public static void main(String[] args) {
        LeetCode476 leetCode476 = new LeetCode476();
        System.out.println(leetCode476.findComplement(5));
    }
}
