package ALeetCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-14
 */
public class LeetCode1114 {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int time = 0;
        while (tickets[k] > 0) {
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] == 0) {
                    if (i == k) {
                        return time;
                    }
                } else {
                    tickets[i]--;
                    time++;
                    if(i==k && tickets[k] ==0){
                        return time;
                    }
                }
            }
        }
        return time;
    }

    public static void main(String[] args) {
        LeetCode1114 leetCode1114 = new LeetCode1114();
        System.out.println(leetCode1114.timeRequiredToBuy(new int[] {84, 49, 5, 24, 70, 77, 87, 8}, 3));
    }
}
