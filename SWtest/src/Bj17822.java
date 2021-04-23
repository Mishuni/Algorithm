import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Bj17822 {

	
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static int[][] map;
	static int N,M,T;
	static int[] xdk;
	static int sum, num_cnt;
	
	static final int[][] DIRECTIONS = {{0,-1},{0,1},{-1,0},{1,0}};
	
	static void move() {
		
		// move
		for(int th =1; th*xdk[0]<=N; ++th) {
			
			int cell = th * xdk[0] -1 ;
			int[] copy = map[cell].clone();
			for(int m=0; m<M; ++m) {
				
				int next = (m + xdk[2] * xdk[1] + M) % M;
				map[cell][next] = copy[m];
			}
			
		}
		
		
		// check bfs
		
		boolean[][] visited = new boolean[N][M];
		
		Queue<int[]> queue = new LinkedList();
		boolean atLeast = false;
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				
				if(!visited[i][j] && map[i][j]!=0) {
					
					queue.add(new int[] {i,j});
					boolean check = false;
					int cnt = 1;
					int value = map[i][j];
					visited[i][j] = true;
					
					while(!queue.isEmpty()) {
						
						int[] start = queue.poll();
						
						for(int[] direc : DIRECTIONS) {
							
							int nexty = (start[0]+direc[0]);
							int nextx = (start[1]+direc[1]+M)%M;
							
							if(nexty>=0 && nexty<N && !visited[nexty][nextx] &&map[nexty][nextx]==value ) {
								++cnt;
								--num_cnt;
								if(!check) check = true;
								if(!atLeast) atLeast = true;
								
								queue.add(new int[] {nexty,nextx});
								map[nexty][nextx] = 0;
								
								visited[nexty][nextx] = true;
							}
							
						}
						
					}

					// if there are same value
					if(check) {
						map[i][j] = 0;
						sum -= cnt * value;
						--num_cnt;
					}
					
				}
				
			}
		}
		
		
		// if there is no same
		if(!atLeast) {
			float avg = (float)sum / (float)num_cnt;
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<M; ++j) {
					
					if(map[i][j]!=0) {
						if((float)map[i][j]>avg) {
							map[i][j] -= 1;
							sum -= 1;
						}
						else if((float)map[i][j]<avg){
							map[i][j] += 1;
							sum += 1;
						}
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		T = stoi(st.nextToken());
		
		map = new int[N][M];
		xdk = new int[3];
		sum = 0;
		num_cnt = N * M;
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; ++j) {
				int tmp =  stoi(st.nextToken());
				map[i][j] = tmp;
				sum+= tmp;
			}
		}
		
		for(int t=0; t<T; ++t) {
			
			st = new StringTokenizer(br.readLine());
			xdk[0] = stoi(st.nextToken());
			int dir = stoi(st.nextToken());
			xdk[1] = (dir==0)? 1 : -1;
			xdk[2] = stoi(st.nextToken());
			move();
			
		}
		
		System.out.println(sum);
	}

}
