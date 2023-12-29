package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-01-10
 */
public class ListNode {
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

    public static ListNode buildListNode(int[] arr) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i : arr) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return head.next;
    }
}
