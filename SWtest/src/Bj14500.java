import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Bj14500 {
	
	public static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int[][] paper;
	public static int max_score;
	public static boolean[][] visited;
	public static int N,M;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 1. Initialize
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		paper = new int[N][M];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; ++j) {
				paper[i][j] = stoi(st.nextToken());
			}
		}
		
		max_score = 0 ;
		visited = new boolean[N][M];
		
		// 2. dfs 하고 특수한 상황인 ㅗ 모양 4가지 방향으로 별도 탐색
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				// dfs 에서 알아서 false 넣어주기 때문에
				// visited 를 매번 초기화 해줄 필요 없음 (하면 시간초과남)
				int current = paper[i][j];
				
				// 2-1. DFS 시작
				dfs(i,j,0,current);
				
				// 2-2. 특수한 'ㅗ','ㅜ','ㅓ','ㅏ' 탐색
				// 2-2-1. 4가지 방향 다 되는 경우
				if(i!=0 && i!=N-1 && j!=0 && j!=M-1) {
					// 2-2-1-1. 중간으로 부터 사방칸들중 차례로 하나만 빼면서 점수 계산
					for(int except=0; except<4; ++except) {
						totalScore(i,j,current,except);
					}
				}
				// 2-2-2. 위 방향만 안되는 경우
				else if(i==0 && i!=N-1 && j!=0 && j!=M-1) {
					// 2-2-2-1. ㅜ 방향으로 탐색
					totalScore(i,j,current,0);
				}
				// 2-2-3. 아래 방향만 안되는 경우
				else if(i!=0 && i==N-1 && j!=0 && j!=M-1) {
					// 2-2-3-1. ㅗ 방향으로 탐색
					totalScore(i,j,current,1);
				}
				// 2-2-4. 왼쪽 방향만 안되는 경우
				else if(i!=0 && i!=N-1 && j==0 && j!=M-1) {
					// 2-2-4-1. ㅏ 방향으로 탐색
					totalScore(i,j,current,2);
				}
				// 2-2-5. 오른쪽 방향만 안되는 경우
				else if(i!=0 && i!=N-1 && j!=0 && j==M-1) {
					// 2-2-5-1. ㅓ 방향으로 탐색
					totalScore(i,j,current,3);
				}
			}
		}
		
		// 3. 결과 출력
		System.out.println(max_score);
	}
	
	// 4. 특수 모양 통합 점수 구하고, max 값 체크 하는 함수
	public static void totalScore(int i, int j, int current, int except) {
		
		int score = current;
		// 4-1. 현재 좌표에서 except 방향만 빼고 점수 더하기
		for(int d=0; d<4; ++d) {
			
			if(d!=except) {
				int[] dir = DIRECTIONS[d];
				score += paper[i+dir[0]][j+dir[1]];
			}
		}
		// 4-2. 더한 값이 최대값보다 큰지 비교
		if(score>max_score) {
			max_score = score;
		}
		
	}
	
	// 5. DFS
	public static void dfs(int y, int x,int step, int score) {
		
		// 5-1. 기저함수 , 4개 탐색한 경우 최대값 비교
		if(step==3) {
			
			if(score>max_score) {
				max_score = score;
			}
			return;
		}
		
		// 5-2. 4가지 방향으로 가능한 경우 더 탐색
		visited[y][x] = true;
		
		for(int[] direc : DIRECTIONS) {
			
			int nexty = y+direc[0];
			int nextx = x+direc[1];
			
			if(nexty>=0 && nexty<N && nextx>=0 && nextx<M) {
				
				if(!visited[nexty][nextx]) {
					dfs(nexty,nextx,step+1,score+paper[nexty][nextx]);
				}
			}
		}
		visited[y][x] = false;
		
	}

}
