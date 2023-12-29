package ALeetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-26
 */
public class LeetCode496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> vMap = new HashMap<>();
        for(int i = nums2.length-1; i>=0; i--){
            while(!stack.isEmpty() && stack.peek()<nums2[i]){
                vMap.put(stack.pop(), stack.isEmpty()? -1: stack.peek());
            }
            stack.push(nums2[i]);
        }
        while (!stack.isEmpty()){
            vMap.put(stack.pop(), stack.isEmpty()? -1: stack.peek());
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = vMap.get(nums1[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode496 leetCode496 = new LeetCode496();
        leetCode496.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
    }
}
