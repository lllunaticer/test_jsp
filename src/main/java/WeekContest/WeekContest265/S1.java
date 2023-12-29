package WeekContest.WeekContest265;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-31
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class S1 {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] res = new int[2];

        int preVal = head.val;
        ListNode cur = head.next;

        if (cur == null) {
            res[0] = -1;
            res[1] = -1;
            return res;
        } else {
            List<Integer> idxList = new ArrayList<>();
            int idx = 1;
            while (cur.next != null) {
                if (preVal < cur.val && cur.val > cur.next.val) {
                    idxList.add(idx);
                } else if (preVal > cur.val && cur.val < cur.next.val) {
                    idxList.add(idx);
                }
                preVal = cur.val;
                idx++;
                cur = cur.next;
            }
            idxList = idxList.stream().sorted().collect(Collectors.toList());

            Integer integer = idxList.stream().min(Integer::compareTo).orElse(-1);
            int min = Integer.MAX_VALUE;

            if (idxList.size() == 0 || idxList.size() == 1) {
                return new int[] {-1, -1};
            }

            if (idxList.size() == 2) {
                return new int[] {idxList.get(1) - idxList.get(0), idxList.get(1) - idxList.get(0)};
            }

            int first = idxList.get(0);
            int second = idxList.get(idxList.size() - 1);
            for (int i = 0; i < idxList.size(); i++) {
                if (i + 1 < idxList.size()) {
                    if (idxList.get(i + 1) - idxList.get(i) < min) {
                        min = idxList.get(i + 1) - idxList.get(i);
                    }
                }
            }
            res[0] = min;
            res[1] = second - first;
            return res;
        }

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        S1 s1 = new S1();
        ListNode head = new ListNode(5);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next = new ListNode(2);

        System.out.println(Arrays.toString(s1.nodesBetweenCriticalPoints(head)));
    }
}