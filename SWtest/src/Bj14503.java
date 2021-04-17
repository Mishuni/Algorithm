import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj14503 {

	public static final int[][] DIRECTIONS = {{-1,0},{0,1},{1,0},{0,-1}};
	public static int N,M;
	public static int[][] map;
	public static int clean;
	
	public static int stoi(String s) {
		
		return Integer.parseInt(s);
	}
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		map  = new int[N][M];
		
		st =  new StringTokenizer(br.readLine());
		int nexty = stoi(st.nextToken());
		int nextx = stoi(st.nextToken());
		int direc = stoi(st.nextToken());
		
		for(int i=0; i<N; ++i) {
			st =  new StringTokenizer(br.readLine());
			for(int j=0; j<M; ++j) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		boolean stop = false;
		
		while(!stop) {
			// 1. 현재 위치를 청소한다.
			if(map[nexty][nextx]==0) {
				map[nexty][nextx] =  2;
				++clean;
			}
			boolean all = true;
			
			// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향으로부터 차례대로 탐색을 진행한
			for(int d=0; d<4; ++d) {
				// 왼쪽 돌고 전진해보기
				direc = (direc==0)? 3 : direc-1;
				int[] direction = DIRECTIONS[direc];
				nexty += direction[0];
				nextx += direction[1];
				
				if(nexty>=0 && nexty<N && nextx>=0 && nextx<M) {
					// 2-a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
					if(map[nexty][nextx]==0) {
						all = false;
						break;
					}
				}
				// 2-b. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
				nexty -= direction[0];
				nextx -= direction[1];
			}
			// 2-c,d. 네 방향 모두 청소가 이미 되어있거나 벽인 경우
			if(all) {
				// 2-c. 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
				nexty += DIRECTIONS[(direc+2)%4][0];
				nextx += DIRECTIONS[(direc+2)%4][1];
				// 2-d. 쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
				if(nexty>=0 && nexty<N && nextx>=0 && nextx<M) {
					if(map[nexty][nextx]==1) {
						stop=true;
					}
				}else {
					stop=true;
				}
			}
		}
		
		System.out.println(clean);
	}

}
