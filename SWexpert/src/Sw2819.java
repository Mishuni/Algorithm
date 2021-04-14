import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Sw2819 {
	
	public static HashSet<Integer> result;
	public static int[][] map;
	public static final int[][] DIRECTION 
		= {{0,1},{0,-1},{1,0},{-1,0}};	// E, W, S, N
	
	public static void main(String[] args) throws FileNotFoundException {
		// BFS
		System.setIn(new FileInputStream("./src/2819.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			// 1. initialize
			result = new HashSet<Integer>();
			map = new int[4][4];
			for(int y=0; y<4; ++y) {
				for(int x=0; x<4; ++x) {
					map[y][x]=sc.nextInt();
				}
			}
			int step = 0;
			int[] foot = {-1,-1,-1,-1,-1,-1,-1};
			
			// 2. Start BFS
			for(int y=0; y<4; ++y) {
				for(int x=0; x<4; ++x) {
					bfs(x,y,step,foot);
				}
			}
			
			// 3. Print Result
			System.out.printf("#%d %d%n",test_case,result.size());
			
			
		}
	}
	
	// 4. BFS function
	public static void bfs(int startx, int starty, int step, int[] foot) {
		// 4-1. basis
		if(step == 6) {
			foot[step] = map[startx][starty];
			int num = 0;
			for(int d=0; d<7; ++d) {
				num += foot[d]*(int)Math.pow(10, d);
			}
			result.add(num);
			return;
		}
		
		// 4-2. BFS action
		foot[step]=map[startx][starty];
		for(int[] direction : DIRECTION ){
			int nextx = startx + direction[1];
			int nexty = starty + direction[0];
			
			// 4-2-1. If the next step is available in map, start next step
			if(nextx>=0 && nextx<4 && nexty>=0 && nexty<4) {
				bfs(nextx,nexty,step+1,foot);
			}
		}
		
	}
	
	

}
