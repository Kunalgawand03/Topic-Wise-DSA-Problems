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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        int cnt = 0;
        while(temp != null && cnt < k){
            temp = temp.next;
            cnt++;
        }

        if(cnt < k){
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        cnt = 0; 
        while(curr != null && cnt < k){
            ListNode front = curr.next;
            curr.next = prev;
            prev = curr;
            curr = front;
            cnt++;
        }

        head.next = reverseKGroup(curr , k);

        return prev;
        
    }
}