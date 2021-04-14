import java.util.*;
public class LinkProb2 {

	public static void main(String[] args) {
		int[] ball = {11, 2, 9, 13, 24};
		int[] order = {9, 2, 13, 24, 11};
		int[] answer = new int[order.length];
		
		boolean[] check = new boolean[1000001];
		
		int first = 0;
		int end = ball.length-1;
		int i = 0;
		
		for(int current : order) {
			
			if(first == end ) {
				answer[order.length-1] = current;
				break;
			}
			int idx = Arrays.binarySearch(ball, current);
			if(idx == first) {
				answer[i++] = current;
				++first;
				
				while(  check[ball[first]]  ) {
					
					answer[i++]=ball[first];
					++first;
					
				}
				
			}
			else if(idx==end) {
				answer[i++]=current;
				--end;
				
				while(  check[ball[end]]  ) {
					
					answer[i++]=ball[end];
					--end;
					
				}
				
			}
			else {
				// save
				check[current] = true;
			}
			
		}
		
		System.out.println(Arrays.toString(answer));
	}

}
