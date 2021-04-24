import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;


class Shark  {
	
	int num;
	int y,x;
	int direc;
	int[][] priority;
	
	Shark(int n, int y, int x){
		this.num = n;
		this.y = y; this.x = x;
		this.priority = new int[4][4];
	}
	
	int[] current_proi() {
		return priority[this.direc];
	}
	
}

public class Bj19237 {
	
	static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}};
	static int N,M,K, alive, cnt;
	static int[][][] map;
	static Shark[] sharks;
	static boolean[] dead;

	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static void move() {
		
		// check next position
		for(int i=1; i<=M; ++i) {
			
			if(!dead[i]) {
				Shark now = sharks[i];
				boolean already = false;
				int[] mysmell = new int[3];
				mysmell[0] = -1;
				
				for(int direc : now.current_proi()) {
					
					int nexty = now.y + DIRECTIONS[direc][0];
					int nextx = now.x + DIRECTIONS[direc][1];
					
					if(nexty>=0 && nexty<N && nextx>=0 && nextx<N) {
						
						// must move (smell = 0)
						if(map[nexty][nextx][2]==0) {
							map[now.y][now.x][0] = 0;
							now.y = nexty;
							now.x = nextx;
							now.direc = direc;
							already = true;
							break;
						}
						// my smell
						else if(map[nexty][nextx][1]==now.num) {
							if(mysmell[0]==-1) {
								mysmell[0] = nexty;
								mysmell[1] = nextx;
								mysmell[2] = direc;
							}
						}
						
					}
				}
				
				if(!already && mysmell[0]!=-1) {
					map[now.y][now.x][0] = 0;
					now.y = mysmell[0];
					now.x = mysmell[1];
					now.direc = mysmell[2];
					
				}
				
			}
		}
		
		// move next position
		for(int i=1; i<=M; ++i) {
			if(dead[i]) continue;
			Shark now = sharks[i];
			if(map[now.y][now.x][0]==0) {
				map[now.y][now.x][0] = now.num;
				map[now.y][now.x][1] = now.num;
				map[now.y][now.x][2] = K+1;
			}else {
				dead[i]=true;
				--alive;
			}
		}
		
		// remove smell one
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(map[i][j][2]>0) {
					map[i][j][2] -= 1;
					if(map[i][j][2]==0) {
						map[i][j][1]=0;
					}
				}
				
			}
		}
		
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. init
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		alive = M;
		cnt = 0 ;
		
		map = new int[N][N][3];
		sharks = new Shark[M+1];
		dead = new boolean[M+1];
		// set sharks
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				int tmp = stoi(st.nextToken());
				map[i][j][0] = tmp;
				if(tmp>0) {
					map[i][j][1] = tmp;
					map[i][j][2] = K;
					sharks[tmp] = new Shark(tmp,i,j);
				}
			}
		}
		// set direc
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; ++i) {
			int tmp = stoi(st.nextToken());
			sharks[i].direc = tmp-1;
		}
		// set prior
		for(int i=1; i<=M; ++i) {
			for(int j=0; j<4; ++j) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<4; ++c) {
					sharks[i].priority[j][c]=stoi(st.nextToken())-1;
				}
			}
		}
		
		while(alive!=1 && cnt <= 1000) {
			// move
			move();
			++cnt;
		}
		
		if(cnt>1000) cnt = -1;
		System.out.println(cnt);
		
	}

}
