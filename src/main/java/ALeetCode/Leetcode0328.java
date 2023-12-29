package ALeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-03-27
 */
public class Leetcode0328 {
    @Test
    public void test0328() {

    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> ans = new ArrayList<>();

        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());

        List<Integer> a1 = set1.stream()
                .filter(s -> !set2.contains(s))
                .collect(Collectors.toList());

        List<Integer> a2 = set2.stream()
                .filter(s -> !set1.contains(s))
                .collect(Collectors.toList());

        ans.add(a1);
        ans.add(a2);
        return ans;
    }
}
