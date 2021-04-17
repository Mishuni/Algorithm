
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
//> 1: 0->2, 2->5, 3->0, 5->3
//< 2: 0->3, 2-> 0, 3->5, 5->2
//^ 3: 0->1, 1->5, 4->0, 5->4
//v 4: 0-> 4, 1->0, 4->5, 5->1
public class Bj14499 {
	
	// 좌표 움직임 순서대로 동 서 북 남
	public static final int[][] DIRECTIONS= {{0,1},{0,-1},{-1,0},{1,0}};
	// 좌표에 따른 주사위 움직임 순서대로 동 서 북 남
	public static final int[][] DIREC_DICE = {
			
			{2,-1,5,0,-1,3},
			{3,-1,0,5,-1,2},
			{4,0,-1,-1,5,1},
			{1,5,-1,-1,0,4}
			
	};
	
	// string을 int 로 바꿔주는 함수
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws IOException {
		
		// 1. Initialize
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		int Y = stoi(st.nextToken());
		int X = stoi(st.nextToken());
		int K = stoi(st.nextToken());
		
		int[][] board = new int[N][M];
		// 1-1. 주사위를 한줄로 정렬 했을 때 
		// 순서대로 1(윗 면), 2, 3, 4, 5, 6(바닥면)
		int[] dice = {0,0,0,0,0,0}; // top, ., ., ., ., bottom
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; ++j) {
				board[i][j] = stoi(st.nextToken());
			}
		}
		
		int nexty = Y;
		int nextx = X;
		// 다음 명령 줄 읽기
		st = new StringTokenizer(br.readLine());
		
		// 2. 명령 순서대로 실행
		for(int k=0; k<K; ++k) {
			
			int flow = stoi(st.nextToken())-1;
			int[] direction = DIRECTIONS[flow];
			int[] direc_dice = DIREC_DICE[flow];
			// 2-1. 다음 좌표로 이동
			nexty += direction[0];
			nextx += direction[1];
			// 2-2. 다음 좌표가 지도 범위 안에 있는 경우
			if(nextx>=0&&nextx<M&&nexty>=0&&nexty<N) {
				int[] copy = dice.clone();
				// 2-2-1. 이동 방향에 따라 주사위 회전 시키기
				for(int d=0; d<6; ++d) {
					if(direc_dice[d]!=-1) {
						dice[direc_dice[d]] = copy[d]; 
					}
				}
				
				// 2-2-2. 다음 좌표가 0 아니면
				if(board[nexty][nextx]!=0) {
					// 2-2-2-1. 칸에 적힌 숫자 주사위 바닥면으로 옮기기
					dice[5] = board[nexty][nextx];
					board[nexty][nextx] = 0;
				}
				// 2-2-3. 다음 좌표가 0 이면
				else {
					// 2-2-2-2. 칸에 주사위 바닥면 숫자 복사하기
					board[nexty][nextx]=dice[5];
				}
				// 2-2-4. 이동하고 나서 윗면 출력
				System.out.println(dice[0]);
			}
			// 2-3. 다음 좌표가 범위 안에 없는 경우
			else {
				// 2-3-1. 원래 좌표로 돌아가고 해당 명령 넘어가기
				nexty -= direction[0];
				nextx -= direction[1];
			}
			
		}
	}
	
}
