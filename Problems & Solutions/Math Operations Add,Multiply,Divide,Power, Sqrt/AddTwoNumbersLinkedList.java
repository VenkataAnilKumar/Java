/********************************************
Add Two Numbers: LinkedList

You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

Solution

Time ~ O(Na + Nb), Space ~ O(Na + Nb) 
public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
**********************************************/
public class AddTwoNumbersLinkedList{

public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;

    int carry = 0;
    while (l1 != null || l2 != null) {
        int digit = ((l1 != null) ? l1.val : 0) + ((l2 != null) ? l2.val : 0) + carry;
        carry = digit / 10;
        curr.next = new ListNode(digit % 10);
        if (l1 != null) l1 = l1.next;
        if (l2 != null) l2 = l2.next;
        curr = curr.next;
    }

    if (carry > 0)  curr.next = new ListNode(carry);
    return dummy.next;
}
}