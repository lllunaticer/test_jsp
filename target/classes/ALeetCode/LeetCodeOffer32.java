package ALeetCode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-07-15
 */
public class LeetCodeOffer32 {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Integer> tmp = new LinkedList<>();
        dfs(root, res, tmp, target);
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, Deque<Integer> tmp, int target) {
        target -= root.val;
        if (target < 0) {
            return;
        }
        tmp.offerLast(root.val);
        if (target == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(tmp));
        }
        if (root.left != null) {
            dfs(root.left, res, tmp, target);
        }
        if (root.right != null) {
            dfs(root.right, res, tmp, target);
        }
        tmp.pollLast();
    }

    @Test
    public void testRemove(){
        List<Integer> arr =  new ArrayList<>();
        arr.add(1);
        arr.add(-2);
        arr.add(3);
        arr.add(4);
        System.out.println(arr);
        remove(arr, 3);
        System.out.println(arr);
    }

    private void remove(List<Integer> t, int val) {
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i).equals(val)) {
                t.remove(i);
                break;
            }
        }
    }
}
