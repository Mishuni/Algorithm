import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj19238 {

	static int[][] map;
	static int[][] des;
	static boolean[] done;
	static int remain;
	static int gas;
	static int N,M;
	static int cary, carx;
	
	static int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}};
	
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static int[] bfs() {
		int[] cus = {N,N,-1};
		Queue<int[]> queue = new LinkedList();
		queue.add(new int[] {cary,carx});
		boolean[][] visited = new boolean[N][N];
		visited[cary][carx] = true;
		
		int pre = 0;
		int cnt = 1;
		int path = 0;
		boolean found = false;
		
		while(!queue.isEmpty() && path<gas) {
			--cnt;
			
			int[] current = queue.poll();
			
			for(int[] direc : DIRECTIONS) {
				int nexty = current[0] + direc[0];
				int nextx = current[1] + direc[1];
				
				if(nexty>=0 && nexty<N && nextx>=0 && nextx<N) {
					
					if(!visited[nexty][nextx] && map[nexty][nextx]!=1) {
						int status = map[nexty][nextx];
						// empty
						if(status == 0) {
							++pre;
							queue.add(new int[] {nexty,nextx});
							visited[nexty][nextx] = true;
						}
						// people
						else if(status < 0 && !done[-1*status]) {
							System.out.println(status);
							System.out.println(nexty+":"+nextx);
							found = true;
							if(nexty<cus[0]) {
								cus[0] = nexty;
								cus[1] = nextx;
							}
							else if(nexty==cus[0] && nextx < cus[1]) {
								cus[0] = nexty;
								cus[1] = nextx;
							}
						}
						
					}
					
				}
				
			}
			
			if(cnt==0) {
				path++;
				if(found) {
					cus[2] = path;
					return cus;
				}
				cnt = pre;
				pre=0;
				
			}
		}
		
		
		return cus;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		gas = stoi(st.nextToken());
		
		map = new int[N][N];
		des = new int[M+1][2];
		done = new boolean[M+1];
		remain = M;
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				
				map[i][j] = stoi(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		cary = stoi(st.nextToken())-1;
		carx = stoi(st.nextToken())-1;
		
		for(int i=1; i<=M; ++i) {
			st = new StringTokenizer(br.readLine());
			map[stoi(st.nextToken())-1][stoi(st.nextToken())-1]=(-1)*i;
			des[i] = new int[]{stoi(st.nextToken())-1,stoi(st.nextToken())-1};
		}
		
		for(int[] c : map) {
			System.out.println(Arrays.toString(c));
		}
		
		while(gas>0 && remain>0) {
			
			// choose a customer using bfs
			int[] customer = bfs();
			System.out.println(Arrays.toString(customer));
			if(customer[2]==-1) {
				break;
			}
			
			// go to des using bfs
			
			// test
			break;
			
		}
		
		
	}

}
