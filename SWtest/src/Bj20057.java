import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj20057 {

	static final int[][] DIRECTIONS = {{0,-1},{1,0},{0,1},{-1,0}};
	
	static final int[][][] SCATTERS_DIREC = {
			{{-1,1},{1,1}}, // 1%
			{{-2,0},{2,0}}, // 2%
			{{0,-2}}, // 5%
			{{-1,0},{1,0}}, // 7%
			{{-1,-1},{1,-1}}, // 10%
			{{0,-1}} // a
	};
	// 왼쪽은 그대로, 아래는 y와x반대 y좌표 마이너스, 오른쪽은 x좌표만 마이너스, 위는 y와x반대
	
	static final int[] ratio = {1,2,5,7,10,0};
	
	static int N,step,cnt,direc,result,y,x;
	static int[][] map;
	
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		map = new int[N][N];
		result = 0;
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		step = 1;
		direc = 0;
		cnt = 0;
		y = N/2;
		x = N/2;
		
		// move
		while(step<=N) {
			
			int[] dir = DIRECTIONS[direc];
			
			// move
			for(int i=0; i<step; ++i) {
				int amount = map[y][x]; // current morae
				map[y][x] = 0;
				y = y + dir[0];
				x = x + dir[1];
				// end
				if(y==0 && x ==-1) {
					//result += amount;
					break;
				}
				map[y][x] += amount;
				if(map[y][x]!=0) {
					
					// scatter
					int remain = map[y][x] ;
					int all = map[y][x] ;
					map[y][x] = 0;
					
					for(int s=0; s<6; ++s) {
						
						int[][] scatter = SCATTERS_DIREC[s];
						int rt = ratio[s];
						
						for(int sc =0; sc<scatter.length; ++sc) {
							int scy=0, scx = 0;
							
							switch(direc) {
							case 0:
								scy = y + scatter[sc][0];
								scx = x + scatter[sc][1];
								break;
							case 1:
								scy = y - scatter[sc][1];
								scx = x + scatter[sc][0];
								break;
							case 2:
								scy = y + scatter[sc][0];
								scx = x - scatter[sc][1];
								break;
							case 3:
								scy = y + scatter[sc][1];
								scx = x + scatter[sc][0];
								break;
							}
							
							int append = 0;
							if(rt != 0 ) {
								append = all * rt /100;
								remain -= append;
							}
							else {
								append = remain;
							}
							
							// in range
							if(scy>=0 && scy <N && scx >=0 & scx <N) {
								map[scy][scx] += append;
							}
							else {
								result += append;
							}
							
						}
					}
				}
			}
			
			// after move
			if(step==N) {
				break;
			}
			direc = (direc+1)%4;
			cnt = cnt + 1;
			if(cnt==2) {
				cnt = 0;
				step += 1;
			}
		}
		
		System.out.println(result);
	}

}
