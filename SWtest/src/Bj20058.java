import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj20058 {
	
	
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static void solve() {
		
		int divide = (int)Math.pow(2, L);
		int num = map.length/divide;
		// divide x divide  * num * num
		int[][] copy = new int[map.length][map.length];
		int i = 0;
		for(int[] c : map) {
			copy[i++] = c.clone();
		}
		// move
		for(int rn=0; rn<num; ++rn) {
			
			for(int cn=0; cn<num; ++cn) {
				// 0,0 + divide * n
				for(int r=0; r<divide; ++r) {
					for(int c=0; c<divide; ++c) {

						int oriy = r + divide * rn;
						int orix = c + divide * cn;
						
						int ori = map[oriy][orix];
						
						int nexty = c + divide * rn;
						int nextx = divide-1-r + divide * cn;
						// 8 4 2, 2*4 -1
						copy[nexty][nextx] = ori;
						
					}
				}
			}
			
		}
		
		i = 0;
		for(int[] c : copy) {
			map[i++] = c.clone();
		}

		// check ice
		for(i=0; i<map.length; ++i) {
			for(int j=0; j<map.length; ++j) {
				
				if(copy[i][j]>0) {
					int cnt = 0 ;
					for(int[] direc: DIRECTIONS) {
						int ny = i+direc[0];
						int nx = j+direc[1];
						
						if(ny>=0&&ny<map.length&&nx>=0&&nx<map.length) {
							
							if(copy[ny][nx]>0) {
								++cnt;
								if(cnt>=3) {
									break;
								}
							}
							
						}
					}
					if(cnt<3) {
						map[i][j]-=1;
						result--;
					}
					
				}
				
			}
		}
		
	}
	
	static void search() {
		
		Queue<int[]> queue = new LinkedList();
		boolean[][] visited = new boolean[map.length][map.length];
		
		
		for(int i=0; i<map.length; ++i) {
			for(int j=0; j<map.length; ++j) {
				
				if(map[i][j]>0 && !visited[i][j]) {
					queue.clear();
					queue.add(new int[] {i,j});
					int ice_cnt = 0;
					
					
					while(!queue.isEmpty()) {
						
						int[] start = queue.poll();
						
						for(int[] direc : DIRECTIONS) {
							
							int ny = start[0] + direc[0];
							int nx = start[1] + direc[1];
							
							if(ny>=0&&ny<map.length&&nx>=0&&nx<map.length) {
								
								if(map[ny][nx]>0 && !visited[ny][nx]) {
									
									queue.add(new int[] {ny,nx});
									visited[ny][nx] = true;
									++ice_cnt;
								}
								
								
							}
							
						}
					}
					
					if(ice_cnt>max_mass) {
						max_mass =ice_cnt;
					}
					
				}
				
			}
		}
		
	}
	
	static int N,Q,L,result,max_mass;
	static int[][] map;
	static final int[][] DIRECTIONS = {{0,1},{1,0},{-1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = stoi(st.nextToken());
		Q = stoi(st.nextToken());
		
		map = new int[(int)Math.pow(2, N)][(int)Math.pow(2, N)];
		
		for(int i=0; i<map.length; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<map.length; ++j) {
				int tmp =  stoi(st.nextToken());
				map[i][j] =  tmp;
				result+=tmp;
				
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; ++i) {
			L = stoi(st.nextToken());
			solve();
		}
		
		max_mass = 0;
		search();
		
		System.out.println(result);
		System.out.println(max_mass);
		
	}

}
