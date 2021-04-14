import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj2667 {
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	public static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		visited = new boolean[N][N];
		map = new int[N][N];
		
		for(int i=0; i<N ; i++) {
			String tmp = sc.nextLine().trim();
			for(int j=0; j<N; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		
		bfs(0);
		
	}
	
	public static void bfs(int n) {
		Queue x = new LinkedList<Integer>();
		Queue y = new LinkedList<Integer>();
		
		x.add(n);
		y.add(n);
		
		while(!x.isEmpty()&&!y.isEmpty()) {

			//int tmp_x = 
		}
		}

}
