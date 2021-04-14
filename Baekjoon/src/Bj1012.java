import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj1012 {
	public static int M, N, K;
	public static boolean[][] visited;
	public static int[][] farm;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T != 0) {

			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			visited = new boolean[N][M];
			farm = new int[N][M];
			
			for(int i=0; i<K; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				farm[b][a] = 1;
			}
			
			bfs();
			T--;
		}
	}
	
	public static void bfs() {
		Queue<Integer> x = new LinkedList<Integer>();
		Queue<Integer> y = new LinkedList<Integer>();
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(farm[i][j]==1 && !visited[i][j]) {
					count++;
					visited[i][j]=true;
					x.add(i);
					y.add(j);
					while(!x.isEmpty()&&!y.isEmpty()) {
						int n = x.poll();
						int m = y.poll();
						for(int d=0; d<4; d++) {
							int temp_x = n+dx[d];
							int temp_y = m+dy[d];
							if(temp_x>=0&&temp_y>=0&&temp_x<N&&temp_y<M) {
								if(farm[temp_x][temp_y]==1&&!visited[temp_x][temp_y]) {
									visited[temp_x][temp_y] = true;
									x.add(temp_x);
									y.add(temp_y);
								}
							}
						}
					}
				}
				
			}
		}
		System.out.println(count);
	}

}
