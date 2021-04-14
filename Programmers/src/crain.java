import java.util.*;

public class crain {

	public static void main(String[] args) {

		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		int answer = 0;
		
		Stack<Integer> stack = new Stack<>();
		int num ;
		int length = board.length;
		
		for(int i=0; i<moves.length; ++i) {
			num = moves[i]-1;
			
			for(int j=0; j<length; ++j) {
				
				if(board[j][num]!=0) {
					
					if( !stack.empty() && stack.peek()==board[j][num]) {
						answer+=2;
						stack.pop();
					}
					else {
						stack.add(board[j][num]);
					}
					board[j][num]=0;
					break;
					
				}
				
			}
		}
		
		System.out.println(answer);
		
	}

}
