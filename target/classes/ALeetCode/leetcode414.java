package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-13
 */
public class leetcode414 {
    private int one;
    private int two;
    private int three;
    private boolean[] flag = new boolean[3];

    public int thirdMax(int[] nums) {
        one = Integer.MIN_VALUE;
        two = one;
        three = one;
        for (int i = 0; i < nums.length; i++) {
            replaceNum(nums[i]);
        }

        if (!flag[1]) {
            return one;
        } else if (!flag[2]) {
            return two;
        }
        return three;
    }

    private void replaceNum(int num) {
        if (num > one) {
            if (flag[0]&&!flag[1]) {
                two = one;
                flag[1] = true;
            }
            one = num;
            if (!flag[0]) {
                flag[0] = true;
            }
        } else if (num != one && num > two) {
            if (flag[1]&&!flag[2]) {
                three = two;
                flag[2] = true;
            }
            two = num;
            if (!flag[1]) {
                flag[1] = true;
            }
        } else if (num != one && num != two && num > three) {
            three = num;
            if (!flag[2]) {
                flag[2] = true;
            }
        }
    }

    public static void main(String[] args) {
        leetcode414 leetcode414 = new leetcode414();
        System.out.println(leetcode414.thirdMax(new int[] {1,2,2,5,3,5}));
    }
}
