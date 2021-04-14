
import java.util.*;

public class Leet2 {

	public static void main(String[] args) {
		// test case
		ListNode l1 = new ListNode(9,new ListNode(9)); //99
		ListNode l2 = new ListNode(1, new ListNode(9,new ListNode(1))); //191
		
		// initialize
		ListNode answer = new ListNode(0);
		ListNode current = answer;
		ListNode obj1 = l1;
		ListNode obj2 = l2;
		int carry = 0;
		
		// 2. repeat until the end of the list
		while(obj1!=null || obj2!=null) {
			current.next = new ListNode();
			current = current.next;
			// 2-1. extract one element if it exists
			int n1 = (obj1!=null)? obj1.val : 0;
			int n2 = (obj2!=null)? obj2.val : 0;
			
			// 2-2. sum & carry
			int sum = n1 + n2 + carry;
			carry = (int)(sum / 10);
			sum = sum % 10;
			
			// 2-3. save sum to the next object as result
			current.val=sum;
			
			obj1 = (obj1!=null)? obj1.next : obj1;
			obj2 = (obj2!=null)? obj2.next : obj2;
			
			
		}
		
		// 3. save the 1 if carry exists
		if(carry==1) {
			current.next=new ListNode(1);
		}
		
		// return answer.next;
		// confirm the result
		while(answer.next!=null) {
			System.out.print(answer.next.val+" ");
			answer = answer.next;
		}
		
	}

}

 class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
