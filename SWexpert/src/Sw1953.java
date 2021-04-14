import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Sw1953 {
	
	public static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
	
	// 각 터널마다 갈 수 있는 방향, 각각 상하좌우
	public static final boolean[][] TANNELS = {
			{false,false,false,false},
			{true,true,true,true},
			{true,true,false,false},
			{false,false,true,true},
			{true,false,false,true},
			{false,true,false,true},
			{false,true,true,false},
			{true,false,true,false}
	};
	
	// 각 방향마다 못가는 터널은 0, 갈 수 있는 터널은 1
	public static int[][] NEXT_POSS = {
			
			{0,1,1,0,0,1,1,0}, // 상
			{0,1,1,0,1,0,0,1}, // 하
			{0,1,0,1,1,1,0,0}, // 좌
			{0,1,0,1,0,0,1,1}  // 우
			
	};

	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("./src/1953.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
			
		for(int test_case = 1; test_case <= T; test_case++) {
			
			// 1. 변수 초기화
			int N = sc.nextInt();
			int M = sc.nextInt();
			int R = sc.nextInt();
			int C = sc.nextInt();
			int L = sc.nextInt();

			int[][] map = new int[N][M];
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<M; ++j) {
					map[i][j] = sc.nextInt();
				}
			}
			
			boolean[][] visited = new boolean[N][M];
			Queue<ArrayList<Integer>> path = new LinkedList();
			ArrayList<Integer> start = new ArrayList();
			start.add(R); start.add(C);
			path.add(start);
			int result = 1;
			int hour = 1;
			int turn = 1;
			int cand = 0;
			visited[R][C] = true;
			
			// 2. BFS 시작
			while(!path.isEmpty()&& hour<L) {
				ArrayList<Integer> current = path.poll();
				boolean[] possible = TANNELS[map[current.get(0)][current.get(1)]];
				
				for(int p=0; p<4; ++p) {
					// 2-1. 현재 위치에서 이동가능한 방향만 확인
					if(possible[p]) {
						
						int next_y = current.get(0)+DIRECTIONS[p][0];
						int next_x = current.get(1)+DIRECTIONS[p][1];
						
						// 2-1-1. 다음 위치가 지도 범위 안이면
						if(next_x<M && next_x >=0 && next_y<N && next_y >=0  ) {
							// 2-1-1-1. 다음 위치에 이미 방문 이력이 있으면 넘어가기
							if(visited[next_y][next_x]) continue;
							// 2-1-1-2. 다음 위치에 터널이 있고 && 현재의 터널과 다음 터널이 연결되어 있는 경우 
							if(map[next_y][next_x]>0 && NEXT_POSS[p][map[next_y][next_x]]==1) {
								// 2-1-1-2-1. 갈 수 있다고 판단하고 BFS 후보에 다음 위치 집어넣기
								ArrayList<Integer> next = new ArrayList();
								visited[next_y][next_x] = true;
								next.add(next_y);
								next.add(next_x);
								path.offer(next);
								++result;
								// 2-1-1-2-2. 다음 hour 에서의 후보 갯수 늘리기
								++cand;
							}
						}
						
					}
				}
				// 2-2. 다음 hour로 넘어갈 수 있는지 확인
				--turn;
				if(turn==0) {
					++hour;
					turn = cand;
					cand=0;
				}
				
			}
			
			// 2-3. 결과 출력
			System.out.printf("#%d %d%n",test_case,result);
			
		}
			
			
		
	}

}
