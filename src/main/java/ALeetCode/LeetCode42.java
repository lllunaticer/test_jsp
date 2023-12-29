package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-10
 */
public class LeetCode42 {
    public int trap(int[] height) {
        int res = 0;
        int p1 = 0;
        int p2 = 1;
        int tmp_occupied = 0;
        while (p2 <= height.length - 1) {
            if (height[p2] >= height[p1]) {
                int width = p2 - p1 - 1;
                int water = width * height[p1] - tmp_occupied;
                res += water;
                tmp_occupied = 0;
                p1 = p2;
                p2 = p1 + 1;
            } else {
                tmp_occupied += height[p2];
                p2++;
            }
        }

        tmp_occupied = 0;
        p1 = height.length - 1;
        p2 = p1 - 1;
        while (p2 >= 0) {
            if (height[p2] > height[p1]) {
                int width = p1 - p2 - 1;
                int water = width * height[p1] - tmp_occupied;
                res += water;
                tmp_occupied = 0;
                p1 = p2;
                p2 = p1 - 1;
            } else {
                tmp_occupied += height[p2];
                p2--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LeetCode42 leetCode42 = new LeetCode42();
        leetCode42.trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }
}
