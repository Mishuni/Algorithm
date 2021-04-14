import java.util.*;
public class LinkProb3 {
	public static int minCnt = Integer.MAX_VALUE;
	public static int result = 0;
	public static void main(String[] args) {
		// dfs or dp
		int n = 9;
		int[] answer = new int[2];
		if(n<=9 ) {
			answer[0] = 0;
			answer[1] = n;
			return;
		}
		
		StringBuilder str = new StringBuilder(Integer.toString(n));
		
		for(int i=1; i<str.length(); ++i) {
			
			String left = str.substring(0,i);
			String right = str.substring(i);
			if(right.charAt(0)!='0') {
				dfs(left,right,1);
			}
				
			
			
		}
		
		answer[0] = minCnt;
		answer[1] = result;
		
		System.out.println(Arrays.toString(answer));
		
	}
	
	public static void dfs(String left, String right, int cnt) {
		int num1 = Integer.parseInt(left);
		int num2 = Integer.parseInt(right);
		int nextNum = num1 + num2 ;
		
		
		if(cnt>minCnt) {
			return;
		}
		
		if(nextNum <=9) {
			// basis
			
			if(  cnt < minCnt ) {
				minCnt = cnt;
				result = nextNum;
				
			}
			return;
			
		}
		
		StringBuilder tmp = new StringBuilder(Integer.toString(nextNum));
		for(int j=1; j<tmp.length(); ++j) {
			String left2 = tmp.substring(0,j);
			String right2 = tmp.substring(j);
			if(right2.charAt(0)!='0') {
				dfs(left2,right2,cnt+1);
			}
		}
	}

}
