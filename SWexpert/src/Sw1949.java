import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Sw1949 {
	
	public static int max_bong;
	public static int[][] mountain;
	public static boolean[][] visited;
	public static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}}; // ^v<>
	public static Queue<ArrayList<Integer>> starts;
	public static int max_path;
	public static int K, N;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("./src/1949.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
			
		for(int test_case = 1; test_case <= T; test_case++) {
			
			// 1. Initialize
			max_bong = 0;
			max_path = 0;
			N = sc.nextInt();
			K = sc.nextInt();
			mountain = new int[N][N];
			visited = new boolean[N][N];
			starts = new LinkedList<>();
			// 1-2. get input & start points
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					mountain[i][j] = sc.nextInt();
					if(mountain[i][j]>max_bong) {
						max_bong = mountain[i][j];
						starts.clear();
						ArrayList<Integer> point = new ArrayList();
						point.add(i);
						point.add(j);
						starts.add(point);
						
					}
					else if(mountain[i][j]==max_bong) {
						ArrayList<Integer> point = new ArrayList();
						point.add(i);
						point.add(j);
						starts.add(point);
					}
				}
			}
			
			// 2. Start Dfs from each start point
			int i = 1;
			while(!starts.isEmpty()) {
				ArrayList<Integer> curr = starts.poll();
				dfs(curr.get(0),curr.get(1),0, true, max_bong+1);
			}
			
			// 3. Print result
			System.out.printf("#%d %d%n",test_case,max_path);
			
		}
		
		
	}
	
	// 4. dfs function
	public static void dfs(int y, int x, int step, boolean remain, int previous) {
		
		// 4-1. basis
		// 4-1-1. out of range or visited
		if(  x>N-1 || x<0 || y>N-1 || y<0 ) {
			if(step > max_path)max_path = step; 
			return;
		}
		else if(visited[y][x]) {
			if(step > max_path)max_path = step; 
			return;
		}
		
		int current = mountain[y][x];
		
		// 4-1-2. current height is higher than previous 
		if(previous <= current) {
			// 4-1-2-1. K is not remained
			if(!remain) {
				if(step > max_path)max_path = step; 
				return;
			}
			
			// 4-1-2-2. when even though using K, it is not worked
			if(previous <= current-K) {
				if(step > max_path)max_path = step; 
				return;
			}
			
			// 4-1-2-3. use K
			current = previous-1;
			remain = false;
		}
		
		// 4-2. Loop for 4 directions
		visited[y][x] = true;
		
		for(int i=0; i<4; ++i) {
			int[] direc = DIRECTIONS[i];
			int next_y = y+direc[0];
			int next_x = x+direc[1];
			
			dfs(next_y,next_x,step+1,remain,current);
			
		}
		
		visited[y][x] = false;
		
	}

}
