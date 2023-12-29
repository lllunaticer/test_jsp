package ALeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-03-06
 */
public class Contest0306 {
    @Test
    public void testContest() {
        int[][] des = new int[][] {{1, 2, 1}, {2, 3, 0}, {3, 4, 1}};
        createBinaryTree(des);
    }

    public TreeNode createBinaryTree(int[][] descriptions) {
        HashMap<Integer, TreeNode> map = new HashMap<>();
        Set<TreeNode> parent = new HashSet<>();

        for (int[] des : descriptions) {
            int val = des[0];
            int son = des[1];
            int mark = des[2];
            TreeNode node;
            if (map.get(val) == null) {
                node = new TreeNode(val);
                map.put(val, node);
                parent.add(node);
            }
            node = map.get(val);

            TreeNode sonNode;
            if (map.get(son) == null) {
                sonNode = new TreeNode(son);
                map.put(son, sonNode);
            }
            sonNode = map.get(son);
            parent.remove(sonNode);

            if (mark == 1) {
                node.left = sonNode;
            } else {
                node.right = sonNode;
            }
        }
        return parent.stream().findFirst().get();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void testNon() {
        int[] nums = new int[] {2,2,1,1,3,3,3};
        System.out.println(replaceNonCoprimes(nums));
    }

    public List<Integer> replaceNonCoprimes(int[] nums) {
        LinkedList<Integer> res = new LinkedList<>();

        for (int i : nums) {
            res.add(i);
        }

        int idx = 0;

        while (idx < res.size() - 1) {
            int left = res.get(idx);
            int right = res.get(idx + 1);
            int gcd = gcd(left, right);
            if (gcd > 1) {
                long lcm = ((long) left * (long) right) / gcd;
                res.remove(idx);
                res.set(idx, (int) lcm);
                idx=0;
            } else {
                idx++;
            }
        }
        return res;
    }

    private int gcd(int i, int j) {
        return j == 0 ? i : gcd(j, i % j);
    }
}
