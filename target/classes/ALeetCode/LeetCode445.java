package ALeetCode;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-01-10
 */
public class LeetCode445 {
    @Test
    public void test() {
        int[] arr1 = new int[] {7, 2, 4, 3};
        int[] arr2 = new int[] {5, 6, 4};
        ListNode l1 = ListNode.buildListNode(arr1);
        ListNode l2 = ListNode.buildListNode(arr2);
        addTwoNumbers(l1, l2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //先反转链表
        l1 = revertListNode(l1);
        l2 = revertListNode(l2);

        int t = 0;
        ListNode res = new ListNode(0);
        ListNode prev = null;
        ListNode cur = res;
        while(l1 != null || l2 != null){
            if(l1 == null){
                t = t + l2.val;
                l2 = l2.next;
            }
            else if(l2 == null){
                t = t + l1.val;
                l1 = l1.next;
            }else{
                t = t + l1.val + l2.val;
                l2 = l2.next;
                l1 = l1.next;
            }
            cur.val = t %10;
            t /= 10;
            ListNode next = new ListNode(0);
            cur.next = next;
            prev = cur;
            cur = next;
        }

        if(t != 0){
            cur.val = t;
        }else{
            prev.next = null;
        }
        Deque<int[]> seed = new LinkedList<>();

        return revertListNode(res);
    }

    private ListNode revertListNode(ListNode l) {
        ListNode prev = null;
        ListNode cur = l;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
