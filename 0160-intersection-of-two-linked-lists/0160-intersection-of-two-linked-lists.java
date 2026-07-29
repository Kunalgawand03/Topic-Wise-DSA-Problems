/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int lenA = length(headA), lenB = length(headB);
    ListNode a = headA, b = headB;
    while (lenA > lenB) { a = a.next; lenA--; }
    while (lenB > lenA) { b = b.next; lenB--; }
    while (a != b) { a = a.next; b = b.next; }
    return a;
}
    int length(ListNode head) {
    int len = 0;
    while (head != null) { len++; head = head.next; }
    return len;
    }
}