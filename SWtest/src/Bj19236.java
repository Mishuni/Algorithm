import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

class Fish{
	
	int y,x,direc;
	
	public Fish(int y, int x, int direc){
		this.y = y;
		this.x = x;
		this.direc = direc;
	}

	public void move(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	public void rotate() {
		direc = (direc==7)? 0 : direc+1;
	}

	@Override
	public String toString() {
		return "Fish [y=" + y + ", x=" + x + ", direc=" + direc + "]";
	}
	
}


public class Bj19236 {
	
	public static final int[][] DIRECTIONS = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	public static int[][] map ;
	public static final int N = 4;
	public static int max;
	public static Fish[] fishes;
	public static boolean[] eated;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[N][N];
		fishes = new Fish[17];
		eated = new boolean[17];
		
		for(int i=0; i<N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				int tmp = stoi(st.nextToken());
				map[i][j] = tmp;
				int direc = stoi(st.nextToken());
				fishes[tmp] = new Fish(i,j,direc-1);
			}
		}
		max = map[0][0];
		
		// eat
		eated[map[0][0]]=true;
		int sum = map[0][0];
		map[0][0] = -1; // here
		Fish shark = new Fish(0,0,fishes[sum].direc);
		
		move(map,fishes);
		dfs(shark,sum,map,fishes);
		
		System.out.println(max);
	}
	
	// 상어가 움직일 곳이 있는지 체크하는 함수
	public static boolean check(Fish shark, int[][] cmap) {
		int nexty= shark.y+DIRECTIONS[shark.direc][0];
		int nextx= shark.x+DIRECTIONS[shark.direc][1];
		while(nexty>=0 && nexty<N && nextx<N && nextx>=0) {
			
			if(cmap[nexty][nextx]>0) return true;
			
			nexty+=DIRECTIONS[shark.direc][0];
			nextx+=DIRECTIONS[shark.direc][1];
			
			
		}
		return false;
	}
	public static void dfs(Fish shark, int sum, int[][] cmap, Fish[] cfishes) {
		// basis
		if(!check(shark,cmap)) {
			if(sum>max) {
				max = sum;
			}
			return;
		}
		
		// 이동 가능한 곳으로 다 이동
		int dir = shark.direc;
		int nexty= shark.y+DIRECTIONS[dir][0];
		int nextx= shark.x+DIRECTIONS[dir][1];
		// 벽을 만날 때까지 상어의 방향으로 이동
		while(nexty>=0 && nexty<N && nextx<N && nextx>=0 ) {
			// if shark can eat fish
			if(cmap[nexty][nextx]>0) {
				// copy
				int[][] ccmap = new int[N][N];
				for(int i=0; i<N; ++i) {
					ccmap[i] = cmap[i].clone();
				}
				Fish[] ccfishes = new Fish[17];
				for(int i=1; i<17; ++i) {
					ccfishes[i] = new Fish(cfishes[i].y, cfishes[i].x, cfishes[i].direc);
				}
				// next coordinate
				int num = ccmap[nexty][nextx];
				Fish next_shark = new Fish(nexty,nextx,ccfishes[num].direc);
				// eat
				eated[num] = true;
				ccmap[shark.y][shark.x] = 0;
				ccmap[nexty][nextx] = -1;
				// then
				move(ccmap, ccfishes);
				dfs(next_shark,sum+num,ccmap,ccfishes);
				// back
				eated[num] = false;
				
				
			}
			nexty+= DIRECTIONS[dir][0];
			nextx+= DIRECTIONS[dir][1];
		}
		
	}
	
	// 물고기들 이동 함수
	public static void move(int[][] cmap, Fish[] cfishes) {
		// 1번 부터 16번 물고기까지 이동
		for(int i=1; i<17; ++i) {
			// not eaten
			if(!eated[i]) {
				Fish current = cfishes[i];
				
				for(int j=0; j<8; ++j) {
					
					int nexty = current.y + DIRECTIONS[current.direc][0];
					int nextx = current.x + DIRECTIONS[current.direc][1];
					
					if(nexty>=0 && nexty<N && nextx>=0 && nextx<N && cmap[nexty][nextx]>-1) {
						
						int tmp = 0;
						if(cmap[nexty][nextx]!=0) {
							tmp = cmap[nexty][nextx];
							cfishes[tmp].move(current.y, current.x);
						}
						
						cmap[nexty][nextx] = i;
						cmap[current.y][current.x] = tmp;
						
						current.move(nexty,nextx);
						
						break;
					}
					
					current.rotate();
					
				}
				
			}
			
		}
		
	}

}
