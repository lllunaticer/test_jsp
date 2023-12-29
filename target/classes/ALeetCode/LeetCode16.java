package ALeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-02
 */
public class LeetCode16 {
    public int threeSumClosest(int[] nums, int target) {
        Map<Integer, Integer> res = new HashMap<>();

        List<Integer> list = new ArrayList<>();


        Arrays.sort(nums);
        int len = nums.length;
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < len - 2; i++) {
            int left = i + 1;
            int right = len - 1;

            while (left < right) {
                int tmp = nums[i] + nums[left] + nums[right];
                sum = Math.abs(tmp - target) < Math.abs(sum - target) ? tmp : sum;
                if (tmp == target) {
                    return target;
                }
                if (tmp < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        LeetCode16 leetCode16 = new LeetCode16();
        System.out.println(leetCode16.threeSumClosest(new int[] {-1, 0, 1, 1, 55}, 3));
    }
}
