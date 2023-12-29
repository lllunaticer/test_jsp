package ALeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-22
 */
public class LeetCode229 {
    public List<Integer> majorityElement(int[] nums) {
        int c1 = 0;
        int cnt1 = 0;
        int c2 = 0;
        int cnt2 = 0;

        List<Integer> res = new ArrayList<>();
        if(nums.length<3){
            if(nums.length == 1){
                res.add(nums[0]);
            }
            if(nums.length == 2){
                res.add(nums[0]);
                if(nums[0] != nums[1]){
                    res.add(nums[1]);
                }
            }
            return res;
        }

        for(int i: nums){
            if(cnt1 == 0 && cnt2 == 0){
                c1 = i;
                cnt1++;
            }
            else if(cnt1 == 0){
                if(i == c2){
                    cnt2++;
                }else{
                    c1 = i;
                    cnt1++;
                }
            }
            else if(cnt2 == 0){
                if(i == c1){
                    cnt1++;
                }else{
                    c2 = i;
                    cnt2++;
                }
            }
            else{
                if(i == c1){
                    cnt1++;
                }else if(i == c2){
                    cnt2++;
                }else{
                    cnt1--;
                    cnt2--;
                }
            }
        }

        cnt1 = 0;
        cnt2 = 0;

        for(int i: nums){
            if(i == c1){
                cnt1++;
            }
            if(i == c2){
                cnt2++;
            }
        }

        int com = nums.length/3;
        if(nums.length%3 == 0){
            com++;
        }
        if(cnt1 >= com){
            res.add(c1);
        }
        if(c2!=c1 && cnt2 > com){
            res.add(c2);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode229 leetCode229 = new LeetCode229();
        System.out.println(leetCode229.majorityElement(new int[] {6,5,5}));
    }
}
