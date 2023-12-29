package ALeetCode;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-04-08
 */
public class BinarySearch {

    @Test
    public void testNotBigThan() {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        int target = 4;
        int l = 0, r = array.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (array[mid] > target) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        System.out.println(array[l]);
    }

    @Test
    public void testNotBigThan2() {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        int target = 4;
        int l = 0, r = array.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (array[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(array[l]);
    }

    @Test
    public void testNotBigThan2_1() {
        int[] array = new int[] {1, 2, 3, 5, 6, 7, 8};
        int target = 4;
        int l = 0, r = array.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (array[mid] > target) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        System.out.println(array[l]);
    }

    @Test
    public void testNotSmallThan() {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        int target = 4;
        int l = 0, r = array.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (array[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(array[l]);
    }

    @Test
    public void testNotSmallThan2() {
        int[] array = new int[] {1, 2, 3, 5, 6, 7, 8};
        int target = 4;
        int l = 0, r = array.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (array[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(array[l]);
    }
}
