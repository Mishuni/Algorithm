import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Sw2105 {
	
	public static int[][] map;
	public static int max_cnt;
	public static int N;
	public static int[] start = new int[2];
	public static HashSet<Integer> desserts;
	public static boolean[][] visited;
	// 어디서든 방향의 흐름이 같아도 된다는 점
	// 하우, 하좌, 상좌, 상우 
	public static final int[][] DIRECTIONS = {{1,1},{1,-1},{-1,-1},{-1,1}};
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("./src/2105.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
			
		for(int test_case = 1; test_case <= T; test_case++) {
			
			// 1. Initialize
			N = sc.nextInt();
			map = new int[N][N];
			max_cnt = 0;
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					map[i][j] = sc.nextInt();
				}
			}
			
			// 2. DFS
			// 마지막 행,열은 할 필요 없음
			for(int i=0; i<N-1; ++i) {
				start[0]=i;
				for(int j=0; j<N-1; ++j) {
					desserts = new HashSet<>();
					start[1]=j;
					visited = new boolean[N][N];
					dfs(i,j,0,0);
				}
			}
			
			// 3. 결과 출력
			max_cnt = (max_cnt<=1)? -1 : max_cnt;
			System.out.printf("#%d %d%n", test_case, max_cnt);
			
		}
			
	}
	
	// 4. DFS 함수
	public static void dfs(int y, int x, int path, int flow) {
		
		// 기저함수 (Basis)
		// 4-1. 이번 자리가 방문한 적이 있으면
		if(visited[y][x]) {
			// 4-1-1. 방문한 적이 있는데 시작점이면
			if(y==start[0] && x==start[1]) {
				// 4-1-1-1. 카페 투어가 잘 완료됐으므로 max 값 비교
				if(path > max_cnt) {
					max_cnt = path;
				}
			}
			// 4-1-2. 방문한 적이 있는데 시작점이 아니면 해당 투어 끝내기
			else {
				return;
			}
		}
		// 4-2. 4방향이 모두 끝났는데, 방문한 적이 있는 곳으로 안 돌아온 경우 투어 끝내기
		else if(flow==DIRECTIONS.length) {
			return;
		}
		
		// 4-3. 현재 디저트 값이 중복되는지 체크
		int current = map[y][x];
		int pre = desserts.size();
		desserts.add(current);
		// 4-3-1. 현재 디저트 넣는데 이전이랑 set 길이가 같으면
		// 중복되는 값이 있다는 뜻이므로 투어 끝내기
		if(pre == desserts.size()) {
			return;
		}
		// 4-3-2. 길이 늘어난거면 해당 자리 방문하기
		visited[y][x] = true;
		
		// 4-4. 같은 방향으로 다음 카페로 넘어가기
		int[] direc = DIRECTIONS[flow];
		int nexty = y+direc[0];
		int nextx = x+direc[1];
		if(nexty>=0 && nexty<N && nextx>=0 && nextx <N) {
			dfs(nexty, nextx, path+1, flow);
		}
		
		// 4-5. 다음 방향으로 다음 카페로 넘어가기
		int next_flow = (flow==3)? 0 : flow+1;
		direc = DIRECTIONS[next_flow];
		nexty = y+direc[0];
		nextx = x+direc[1];
		if(nexty>=0 && nexty<N && nextx>=0 && nextx <N) {
			dfs(nexty, nextx, path+1, flow+1);
		}
		
		// 4-6. 현재 자리 방문 안한걸로 돌려놓기
		visited[y][x] = false;
		desserts.remove(current);
		
		
	}

}
