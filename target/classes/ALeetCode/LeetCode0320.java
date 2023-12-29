package ALeetCode;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-03-20
 */
public class LeetCode0320 {
    public int countHillValley(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return 0;
        }
        int cnt = 0;
        int first = 0;
        int second = 0;
        int third = 0;
        while (third < len - 1) {

            while (first + 1 < len - 1 && nums[first] == nums[first + 1]) {
                first += 1;
            }
            if(first>=len-2){
                return cnt;
            }

            second = first + 1;
            while (second + 1 < len - 1 && nums[second] == nums[second + 1]) {
                second += 1;
            }

            if(second>=len-1){
                return cnt;
            }

            third = second + 1;
            while (third + 1 < len - 1 && nums[third] == nums[third + 1]) {
                third += 1;
            }

            if ((nums[first] > nums[second] && nums[third] > nums[second]) || (nums[first] < nums[second]
                    && nums[third] < nums[second])) {
                cnt++;
            }
            first = second;

        }


        return cnt;
    }

    @Test
    public void test() {
        System.out.println(countHillValley(
                new int[] {79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79,
                        79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79,
                        79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 64}));
    }
}
