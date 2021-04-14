import java.util.Scanner;

public class Bj12100 {

	public static int N;
	public static int[][] map;
	// 방향 왼쪽부터 상 하 좌 우
	public static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int max_value;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// 1. Initialize
		N = sc.nextInt();
		map = new int[N][N];
		max_value = 0;
		
		// 1-1. N이 1인 경우 탐색 할 필요 없이 값 출력
		if(N==1) {
			System.out.println(sc.next());
			return;
		}
		// 1-2. map 값을 받을 때, 제일 큰 수를 max 값으로 저장
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				int tmp = sc.nextInt();
				if(tmp>max_value) max_value = tmp;
				map[i][j] = tmp;
			}
		}
		
		// 2. DFS 탐색 시작
		dfs(0,map,-1);
		
		// 3. 결과 출력
		System.out.println(max_value);
		
	}
	
	// 4. DFS 함수
	public static void dfs(int step, int[][] board, int flow) {
		
		// 4-1. basis : 이동 횟수가 5번이 넘은 경우 끝내기
		if(step>=5) {
			return;
		}
		
		// 4-2. 4가지 방향으로 블록을 이동시키기
		for(int i=0; i<4; ++i) {
			// 4-2-1. 필요한 변수 생성
			// added : 이번 턴에서 이미 블록이 합쳐진 좌표 표시
			// copy : 이전 board를 깊은 복사한 배열
			boolean[][] added = new boolean[N][N];
			int[][] copy = new int[N][N];
			for(int c=0; c<N; ++c) {
				copy[c] = board[c].clone();
			}
			
			// 4-2-2. 이동 방향 '상' 인 경우
			if(i==0) {
				for(int c=0; c<N; ++c) {
					for(int r=1; r<N; ++r) {
						// 같은 열에서 두번째 행부터 이동해서 끝 행까지 이동
						if(copy[r][c]!=0)
							copy = move(r,c,i,copy,added);
						
					}
				}
				// 이동한 보드로 다음 dfs 시작	
				dfs(step+1, copy, i);
			}
			
			// 4-2-3. 이동 방향 '하'인 경우
			else if(i==1) {
				for(int c=0; c<N; ++c) {
					for(int r=N-2; r>=0; --r) {
						// 같은 열에서 끝에서 한칸 전 행부터 첫번째 행까지 이동
						if(copy[r][c]!=0)
							copy = move(r,c,i,copy,added);
						
					}
				}
				// 이동한 보드로 다음 dfs 시작	
				dfs(step+1, copy, i);
			}
			
			// 4-2-4. 이동 방향 '좌'인 경우
			if(i==2) {
				for(int r=0; r<N; ++r) {
					for(int c=1; c<N; ++c) {
						// 같은 행에서 두번째 열부터 끝 열까지 이동
						if(copy[r][c]!=0)
							copy = move(r,c,i,copy,added);
						
					}
				}
				// 이동한 보드로 다음 dfs 시작	
				dfs(step+1, copy, i);
			}
			
			// 4-2-5. 이동 방향 '우'인 경우
			else if(i==3) {
				for(int r=0; r<N; ++r) {
					for(int c=N-2; c>=0; --c) {
						// 같은 행에서 끝에서 한칸 전 열부터 첫 열까지 이동
						if(copy[r][c]!=0)
							copy = move(r,c,i,copy,added);
						
					}
				}
				// 이동한 보드로 다음 dfs 시작	
				dfs(step+1, copy, i);
			}
		}
		
	}
	
	// 5. 좌표 이동 함수
	public static int[][] move(int y, int x, int direc, int[][] board, boolean[][] added) {
		
		// 5-1. 필요 변수 생성
		int[][] copy = new int[N][N];
		for(int i=0; i<N; ++i) {
			copy[i] = board[i].clone();
		}
		int[] direction = DIRECTIONS[direc];
		
		int prey = y;
		int prex = x;
		int nexty = y + direction[0];
		int nextx = x + direction[1];
		
		int current = copy[y][x];
		
		// 5-2. 될 때 까지 다음 좌표 검사
		while(nexty>=0 && nexty<N && nextx>=0 && nextx<N) {
			int next = copy[nexty][nextx];
			// 5-2-1. 다음 값이 현재 값과 같은 경우 검사 하고 이동 끝내기
			if(next==current) {
				// 5-2-1-1. 다음 좌표가 이전에 어떤 거랑 합해진 경우가 아닌 경우
				if(!added[nexty][nextx]) {
					// 5-2-1-1-1. 다음 좌표를 2배하고 max 값 검사
					copy[prey][prex] = 0;
					copy[nexty][nextx] *= 2;
					added[nexty][nextx] = true;
					if(copy[nexty][nextx]>max_value) {
						max_value = copy[nexty][nextx];
					}
				}
				return copy;
			}
			// 5-2-2. 다음 값이 0인 경우, 그 자리로 현재 값 옮겨주고 다음 좌표로 넘어가기
			else if(next==0) {
				copy[prey][prex] = 0;
				copy[nexty][nextx] = current;
				prey = nexty;
				prex = nextx;
				nexty += direction[0];
				nextx += direction[1];
			}
			// 5-2-3. 다음 값이 0이 아닌데 현재 값과 다른 경우, 이제 못 움직임으로 끝내기
			else if(next!=current) {
				return copy;
			}
		}
		
		return copy;
		
	}
}
