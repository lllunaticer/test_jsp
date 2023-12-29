package ALeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-29
 */
public class LeetCode519 {
    int[][] matrix;
    List<int[]> record;
    Random random;
    int m;
    int n;

    public LeetCode519(int m, int n) {
        this.m = m;
        this.n = n;
        matrix = new int[m][n];
        record = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                record.add(new int[] {i, j});
            }
        }
    }

    public int[] flip() {
        int index = random.nextInt(record.size());
        int[] res = record.get(index);
        record.remove(index);
        return res;
    }

    public void reset() {
        this.matrix = new int[m][n];
        this.record = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                record.add(new int[] {i, j});
            }
        }
    }

    public static void main(String[] args) {
        LeetCode519 leetCode519 = new LeetCode519(3, 1);
        leetCode519.flip();
        leetCode519.flip();
        leetCode519.flip();
        leetCode519.reset();
        leetCode519.flip();
    }
}
