/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;

        for(int i = 0; i < left-1; i++){
            prev = prev.next;
        }

        ListNode curr = prev.next;
        for(int i = 0; i < right - left ; i++){
            ListNode front = curr.next;
            curr.next = front.next;
            front.next = prev.next;
            prev.next = front;
        }

        return dummy.next;
    }
}