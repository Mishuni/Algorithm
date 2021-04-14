import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj2178 {
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	public static boolean[][] visited;
	public static int[][] map;
	public static int N,M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 행
		M = sc.nextInt(); // 열
		visited = new boolean[N][M];
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String tmp = sc.next();
			for(int j=0;j<M;j++) {
				map[i][j] = tmp.charAt(j)-'0';
			}
		}
		bfs(0,0);
	}
	
	public static void bfs(int n, int m) {
			Queue<Integer> x = new LinkedList<Integer>();
			Queue<Integer> y = new LinkedList<Integer>();
			x.add(n);
			y.add(m);
			while(!x.isEmpty()&&!y.isEmpty()) {
				n = x.poll();
				m = y.poll();
				visited[n][m]=true;
				for(int d=0; d<4; d++) {
					
					int tmp_x = n+dx[d];
					int tmp_y = m+dy[d];
					if(tmp_x>-1&&tmp_y>-1&&tmp_x<N&&tmp_y<M) {
						if(!visited[tmp_x][tmp_y]&&map[tmp_x][tmp_y]==1) {
							x.add(tmp_x);
							y.add(tmp_y);
							map[tmp_x][tmp_y] = map[tmp_x-dx[d]][tmp_y-dy[d]]+1;
							visited[tmp_x][tmp_y] = true;
						}
					}
				} 
				}
			System.out.println(map[N-1][M-1]);
	}

}
