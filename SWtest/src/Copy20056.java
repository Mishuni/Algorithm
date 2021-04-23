import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Copy20056 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static Queue<int[]> queue = new LinkedList<>();
	static Queue<int[]> will = new LinkedList<>();
	
	static int[] movex = {-1,-1,0,1,1,1,0,-1};
	static int[] movey = {0,1,1,1,0,-1,-1,-1};
	
	static int[][][] map;
	static int[][] cnt;
	static int N,M,K;
	
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws IOException{
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		
		map = new int[N][N][3];
		cnt = new int[N][N];
		
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int r = stoi(str[0]) - 1;
			int c = stoi(str[1]) - 1;
			map[r][c][0] = stoi(str[2]); // 질량
			map[r][c][1] = stoi(str[3]); // 속도
			map[r][c][2] = stoi(str[4]); // 방향
			// 해당 좌표 파이어볼 갯수
			cnt[r][c] = 1;
			queue.offer(new int[] {r,c});
			
		}
		
		while(K-- > 0) {
			move();
			process();
		}
		
		int result = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j)
			{
				if(cnt[i][j]==0) continue;
				else if(cnt[i][j]==1) result+=map[i][j][0];
				else result += (map[i][j][0]/5) * 4;
			}
		}
		System.out.println(result);
	}
	
	// 파이어볼 이동하는 함수
	// queue 에 담긴 파이어볼들 다 이동시키고
	// 기존 자리에 있던 정보들 지우기
	static void move() {
		// 파이어볼 갯수
		int size = queue.size();
		// 파이어볼 하나씩 체크
		while(size-- > 0) {
			
			int[] pos = queue.poll();
			// 파이어볼 위치
			int i = pos[0], j = pos[1];
			// 파이어볼 정보
			int m = map[i][j][0], s = map[i][j][1], d = map[i][j][2];
			
			// 하나 였으면 그냥 이동만
			if(cnt[i][j] == 1) {
				fly(i,j,m,s,d);
			}
			// 하나 이상 이면 4개로 나누기
			else if((cnt[i][j] > 1)){
				m/=5; s/=cnt[i][j];
				if(m!=0) {
					int dir = -1;
					// 짝수만 있거나 홀수만 있거나
					if(d==0 || d==cnt[i][j]) dir = 0;
					// 홀수 짝수 섞여 있었던 경우
					else dir = 1;
					
					// 4개 이동
					for(;dir<8; dir+=2) {
						fly(i,j,m,s,dir);
					}
				}
			}
			
			// 파이어볼이 다른 위치로 이동 했으므로 해당 좌표 정보 초기화
			for(int k=0; k<3; ++k) {
				
				map[i][j][k] = 0;
				
			}
			cnt[i][j] = 0;
		}
		
	}
	
	static void fly(int i, int j, int m, int s, int d) {
		
		// 음수 나올 경우를 대비해서 N을 더해줌
		int nx = (i + movex[d] * (s%N) + N) % N;
		int ny = (j + movey[d] * (s%N) + N) % N;
		// 이동 시키고 map 에 이동 정보 옮기기 위해 큐에 넣기
		will.offer(new int[] {nx,ny,m,s,d});
	}
	
	// 이동 후 처리
	static void process() {
		while(!will.isEmpty()) {
			int[] position = will.poll();
			int i = position[0]; int j = position[1];
			map[i][j][0] += position[2]; // 질량 더하기
			map[i][j][1] += position[3]; // 속도 더하기
			cnt[i][j]++ ; // 갯수 더하기
			
			if(cnt[i][j] == 1) {
				queue.offer(new int[] {i,j});
				map[i][j][2] = position[4];
			}
			else if(cnt[i][j] == 2) {
				map[i][j][2] = map[i][j][2] % 2 + position[4]%2;
			}
			else map[i][j][2] += position[4]%2;
		}
		
	}

}
