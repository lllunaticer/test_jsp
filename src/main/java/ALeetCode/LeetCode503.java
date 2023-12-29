package ALeetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-26
 */
public class LeetCode503 {
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n];

        Map<Integer,Integer> vMap = new HashMap<>();
        for(int i = 0; i< n * 2 - 1; i++){
            while(!stack.isEmpty() && stack.peek()<nums[i % n]){
                vMap.put(stack.pop(), nums[i%n]);
            }
            stack.push(nums[i%n]);
        }
        while(!stack.isEmpty()){
            vMap.put(stack.pop(), stack.isEmpty()? -1 : stack.peek());
        }

        for(int i = 0; i<nums.length; i++){
            res[i] = vMap.get(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode503 leetCode503 = new LeetCode503();
        leetCode503.nextGreaterElements(new int[]{1,2,3,4,3});
    }
}
