package ALeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-09
 */
public class LeetCode488 {
    // 手中最多只有5个球，所以，操作次数不会多于5
    int INF = 6;

    public int findMinStep(String board, String hand) {
        // 记忆化缓存
        Map<String, Integer> memo = new HashMap<>();
        // 递归开始
        int ans = dfs(board, hand.toCharArray(), memo);
        // 判断结果
        return ans >= INF ? -1 : ans;
    }

    private int dfs(String board, char[] hand, Map<String, Integer> memo) {
        // 如果board全部消除完了，直接返回
        if (board.length() == 0) {
            return 0;
        }

        // 如果缓存中已经处理过了，直接返回
        if (memo.containsKey(board)) {
            return memo.get(board);
        }

        // 存储本次递归的结果
        int ans = INF;

        // 将手中的球填序的board的任意位置尝试去消除
        for (int i = 0; i < hand.length; i++) {
            char c = hand[i];
            if (c != '0') {
                for (int j = 0; j < board.length(); j++) {
                    // 构造新的board，插入到旧board的任意位置
                    StringBuilder newBoard = new StringBuilder()
                            .append(board.substring(0, j))
                            .append(c)
                            .append(board.substring(j));
                    // 尝试消除
                    System.out.println(newBoard);
                    removeSame(newBoard);
                    System.out.println(newBoard);
                    // 表示这个球已经用过了
                    hand[i] = '0';
                    // 进入下一次递归
                    ans = Math.min(ans, dfs(newBoard.toString(), hand, memo) + 1);
                    // 回溯，恢复状态
                    hand[i] = c;
                }
            }
        }

        // 记录到缓存中
        memo.put(board, ans);
        // 返回结果
        return ans;
    }

    private void removeSame(StringBuilder board) {
        if (board.length() > 0) {
            char tmp = board.charAt(0);
            int sum = 0;
            int left = 0;
            int right = 0;
            for (int i = 0; i <= board.length(); i++) {
                if (i == board.length()) {
                    if (sum >= 3) {
                        board.delete(left, board.length());
                    }
                    return;
                }
                if (board.charAt(i) == tmp) {
                    sum += 1;
                    right = i;
                    continue;
                }
                if (sum >= 3) {
                    removeSame(board.delete(left, right + 1));
                    return;
                } else {
                    left = i;
                    right = i;
                    sum = 1;
                    tmp = board.charAt(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        LeetCode488 leetCode488 = new LeetCode488();
        StringBuilder sb = new StringBuilder().append("WWWRRBBWW");
        leetCode488.removeSame(sb);
        System.out.println(sb);
    }
}
