package algorithm;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-03-27
 */
public class BinarySearch {
    int rowLen;
    int colLen;

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int[] firstRow = matrix[0];
        rowLen = firstRow.length;
        colLen = matrix.length;

        int idx = binarySearchReturnIdx(firstRow, target);
        for (int i = 0; i <= idx; i++) {
            if (binarySearchReturnValue(matrix, target, i) == target) {
                return true;
            }
        }
        return false;
    }

    private int binarySearchReturnIdx(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int mid;
        while (left < right) {
            mid = 1 + left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private int binarySearchReturnValue(int[][] nums, int target, int col) {
        int lo = 0;
        int hi = colLen - 1;
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid][col] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return nums[lo][col];
    }

    @Test
    public void testBinary() {
        int[][] ints = new int[][] {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        System.out.println(findNumberIn2DArray(ints, 5));
    }
}
