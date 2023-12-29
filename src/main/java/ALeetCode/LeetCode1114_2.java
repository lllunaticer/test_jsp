package ALeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-14
 */
public class LeetCode1114_2 {
    public ListNode reverseEvenLengthGroups(ListNode head) {
        List<Integer[]> needsToReverse = new ArrayList<>();
        int length = 0;
        ListNode idx = head;
        while (idx != null) {
            length++;
            idx = idx.next;
        }

        int nextDistance = 1;
        int i = 0;
        while (i <= length) {
            if (i + 1 + nextDistance > length) {
                int left = i + 1;
                if (length > left && (length - left) % 2 == 0) {
                    needsToReverse.add(new Integer[] {left, length});
                }
                break;
            }
            if (nextDistance % 2 != 0) {
                i = i + nextDistance;
                nextDistance += 1;
            } else {
                int left = i + 1;
                int right = left + nextDistance - 1;
                if (left <= length) {
                    if (right < length) {
                        needsToReverse.add(new Integer[] {left, right});
                    } else {
                        needsToReverse.add(new Integer[] {left, length});
                    }
                }
                nextDistance++;
                i = right;
            }

        }
        if (needsToReverse.size() > 0) {
            Integer[] integers = needsToReverse.get(needsToReverse.size() - 1);
            int last = integers[1];
            if ((length - last) % 2 == 0) {
                needsToReverse.add(new Integer[] {last + 1, length});
            }
        }
        for (Integer[] ops : needsToReverse) {
            reverseBetween(head, ops[0], ops[1]);
        }
        return head;
    }

    class ListNode {
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

    public ListNode reverseBetween(ListNode head, int m, int n) {

        if (m == n) {
            return head;
        }
        int count = 1;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode front_tail = dummy;
        while (count < m) {
            front_tail = front_tail.next;
            count++;
        }

        ListNode p = front_tail.next;
        ListNode tmp = p.next;
        ListNode tt = tmp.next;
        tmp.next = p;
        p.next = null;
        ListNode middle_tail = p;

        while (count < n - 1) {
            p = tmp;
            tmp = tt;
            tt = tmp.next;
            tmp.next = p;
            count++;
        }
        middle_tail.next = tt;
        front_tail.next = tmp;

        return dummy.next;
    }

    public static void main(String[] args) {
        int length = 9;
        List<Integer[]> needsToReverse = new ArrayList<>();

        int nextDistance = 1;
        int i = 0;
        while (i <= length) {
            if (i + 1 + nextDistance > length) {
                int left = i + 1;
                if (length > left && (length - left + 1) % 2 == 0) {
                    needsToReverse.add(new Integer[] {left, length});
                }
                break;
            }
            if (nextDistance % 2 != 0) {
                i = i + nextDistance;
                nextDistance += 1;
            } else {
                int left = i + 1;
                int right = left + nextDistance - 1;
                if (left <= length) {
                    if (right < length) {
                        needsToReverse.add(new Integer[] {left, right});
                    } else {
                        needsToReverse.add(new Integer[] {left, length});
                    }
                }
                nextDistance++;
                i = right;
            }
        }
        needsToReverse.forEach(n -> System.out.println(n[0] + "," + n[1]));
    }
}
