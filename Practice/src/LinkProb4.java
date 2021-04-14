import java.util.*;
public class LinkProb4 {

	public static void main(String[] args) {
		int maze[][] = {{0, 1, 0, 1}, {0, 1, 0, 0}, {0, 0, 0, 0}, {1, 0, 1, 0}};
		for(int[] m : maze) {
			System.out.println(Arrays.toString(m));
		}
		
		int[][] direction = { {1,0}, {0,1}, {-1,0}, {0,-1}  }; // down right up left
		int[] startDir = direction[1]; // down
		int startx = 0 ;
		int starty = 0;
		int cnt = 0;
		int dir = 0 ;
		
		int nextx = startx + startDir[0];
		int nexty = starty + startDir[1];
		
		if( nextx  < 0  ) {
			// up to left
			startDir = direction[++dir];
		}
		else if(nextx >= maze.length) {
			// 
			
			
		}
		
		
	}

}
