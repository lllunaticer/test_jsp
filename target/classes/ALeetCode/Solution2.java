package ALeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-24
 */
public class Solution2 {
    public int nextBeautifulNumber(int n) {
        char[] nums = String.valueOf(n).toCharArray();
        int[] res = new int[nums.length];
        if (nums.length == 2) {
            if (n < 22) {
                return 22;
            } else {
                return 122;
            }
        }
        if (nums.length == 3) {
            if (n > 221) {
                return 1333;
            } else {
                if (122 > n) {
                    return 122;
                }
                if (212 > n) {
                    return 212;
                }
                if (221 > n) {
                    return 221;
                }
            }
        }
        if (nums.length == 4) {
            if (n > 3331) {
                return 14444;
            } else {
                if (1333 > n) {
                    return 13333;
                }
                if (3133 > n) {
                    return 3133;
                }
                if (3313 > n) {
                    return 3313;
                }
                if (3331 > n) {
                    return 3331;
                }

            }
        }
        if (nums.length == 5) {
            if (n > 44441) {
                return 155555;
            } else {
                //1,55555
                int idx = -1;
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = 5;
                    if (nums[i] < 5) {
                        idx = i;
                    }
                }
                res[idx + 1] = 1;
                StringBuilder tmp = new StringBuilder();
                for(int i: res){
                    tmp.append(i);
                }
                int tmpNum = Integer.parseInt(tmp.toString());

                //22,333
                idx = -1;
                for(int i = 0; i<nums.length; i++){
                    nums[i] = 3;
                    if(nums[i]<3){

                    }
                }
            }
        }
        if (nums.length == 6) {
            if (n > 555551) {
                return 1666666;
            } else {
                //1,55555

                //22, 4444
            }
        }

        if (nums.length == 7) {
            return 1666666;
        }
        return 0;
    }
}
