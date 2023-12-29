package WeekContest.WeekContest265;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-31
 */
public class S2 {
    public int smallestEqual(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 10 == i) {
                return i;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        List<Integer> idxList = new ArrayList<>();
        idxList.add(1);
        idxList.add(2);
        idxList.add(4);
        idxList.add(10);
        idxList.add(3);
        idxList = idxList.stream().sorted().collect(Collectors.toList());

        int min = Integer.MAX_VALUE;

        int first = idxList.get(0);
        int second = idxList.get(idxList.size() - 1);
        for (int i = 0; i < idxList.size(); i++) {
            if (i + 1 < idxList.size() - 1) {
                if (idxList.get(i + 1) - idxList.get(i) < min) {
                    min = idxList.get(i + 1) - idxList.get(i);
                }
            }
        }
        System.out.println(Arrays.toString(new int[] {min, second - first}));
    }
}
