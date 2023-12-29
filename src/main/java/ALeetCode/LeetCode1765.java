package ALeetCode;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-01-29
 */
public class LeetCode1765 {
    @Test
    public void test1765() {
        int[][] isWater = new int[][] {{0, 1}, {0, 0}};
        highestPeak(isWater);
    }

    public int[][] highestPeak(int[][] isWater) {
        //找0， 从0开始搜索
        //如果全图都是陆地，那就从头开始编号即可 条件说至少有一个水格子

        int rows = isWater.length;
        int cols = isWater[0].length;
        int[][] res = new int[rows][cols];
        Queue<int[]> que = new LinkedList<>();
        int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = -1;
                if (isWater[i][j] == 1) {
                    res[i][j] = 0;
                    que.offer(new int[] {i, j});
                }
            }
        }

        while (!que.isEmpty()) {
            int[] pos = que.poll();
            int x, y;
            int min = Integer.MAX_VALUE;
            for (int[] direction : directions) {
                x = pos[0] + direction[0];
                y = pos[1] + direction[1];
                if (x < 0 || y < 0 || x >= rows || y >= cols || res[x][y] >= 0) {
                    continue;
                }
                que.offer(new int[] {x, y});

                for (int[] dir : directions) {
                    if (x + dir[0] < 0 || x + dir[0] >= rows || y + dir[1] < 0 || y + dir[1] >= cols) {
                        continue;
                    }
                    if (isWater[x + dir[0]][y + dir[1]] == 1) {
                        res[x][y] = 1;
                        break;
                    } else {
                        if (res[x + dir[0]][y + dir[1]] > -1) {
                            min = Math.min(min, res[x + dir[0]][y + dir[1]]);
                        }
                    }
                }
                if (res[x][y] < 0) {
                    res[x][y] = min + 1;
                }
            }
        }
        return res;
    }
}
