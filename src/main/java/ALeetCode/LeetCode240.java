package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-25
 */
public class LeetCode240 {

    public boolean searchMatrix2(int[][] matrix, int target) {
        int col = matrix.length;
        int row = matrix[0].length;

        for (int[] i : matrix) {
            int lo = 0;
            int hi = row;
            int mid;
            while (lo < hi) {
                mid = lo + (hi - lo) / 2;
                if (i[mid] == target) {
                    return true;
                } else if (i[mid] < target) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        LeetCode240 leetCode240 = new LeetCode240();
        System.out.println(leetCode240.searchMatrix2(
                new int[][] {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24},
                        {18, 21, 23, 26, 30}}, 14));
    }
}
