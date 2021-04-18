import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 1. 연합 국가들을 묶는 클래스
class United{
	ArrayList<ArrayList<Integer>> contries;
	int sum,y,x;
	
	// 1-1. 시작 나라 기준으로 초기화
	United(int y,int x,int self){
		this.contries = new ArrayList();
		this.sum = self;
		this.y = y;
		this.x = x;
	}
	// 1-2. 연합국 추가
	public void addContry(ArrayList<Integer> tmp, int p) {
		contries.add(tmp);
		sum+=p;
	}
	// 1-3. 인구 평균으로 연합국 인구들 수정하기
	public void uniformPeople(int[][] map) {
		int avg = sum / (contries.size()+1);
		map[this.y][this.x] = avg;
		for(ArrayList<Integer> contry : contries) {
			map[contry.get(0)][contry.get(1)] = avg;
		}
	}
}

public class Bj16234 {

	public static int N,L,R,result;
	public static int[][] map;
	public static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}};
	public static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 2. initialize
		N = stoi(st.nextToken());
		L = stoi(st.nextToken());
		R = stoi(st.nextToken());
		result = -1;
		
		map = new int[N][N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		// 2. 각 연합국들 bfs로 찾기
		boolean again  = true;
		while(again) {
			++result;
			again  = false;
			visited = new boolean[N][N];
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					// 2-1. i,j 가 어떤 연합국에도 소속되지 않았으면
					if(!visited[i][j]) {
						visited[i][j] = true;
						// 2-1-1. 만약 bfs 했는데, 연합국이 1개의 나라라도 추가되었으면
						if(bfs(i,j)) {
							// 2-1-1-1. 현재 인구 이동할 나라가 있다는 뜻이므로 다음 챕터도 가능
							again = true;
						}
						// 2-1-2. 연합국이 1개라도 추가 안되었으면
						else {
							// 2-1-2-1. 현재 나라는 어느 연합국에도 소속이 안됐으므로 false로 수정
							visited[i][j] = false;
						}
					}
				}
			}
		}
		
		// 3. 결과 출력
		System.out.println(result);
		
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	// 4. bfs 함수
	public static boolean bfs(int y, int x) {
		// 4-1. 시작 나라 정보로 변수 초기화
		Queue<ArrayList<Integer>> queue = new LinkedList();
		ArrayList<Integer> start = new ArrayList();
		United united = new United(y,x,map[y][x]);
		start.add(y); start.add(x);
		queue.add(start);
		// 4-2. bfs 시작
		while(!queue.isEmpty()) {
			start = queue.remove();
			int source = map[start.get(0)][start.get(1)];
			
			// 4-2-1. 현재 위치 부터 4방향으로 가능성 확인
			for(int i=0; i<4; ++i) {
				int nexty = start.get(0) + DIRECTIONS[i][0];
				int nextx = start.get(1) + DIRECTIONS[i][1];
				// 4-2-1-1. 다음 위치가 지도 안에 있고, 다른 연합국에 소속되지 않은 경우
				if(nexty>=0 && nexty<N && nextx>=0 && nextx<N && !visited[nexty][nextx]) {
					int desti = map[nexty][nextx];
					int diff = Math.abs(source-desti);
					// 4-2-1-1-1. 이전 위치의 인구와 차이가 L에서 R사이인 경우
					if(diff>=L && diff<=R) {
						// 4-2-1-1-1-1. 해당 나라는 이번 연합국에 포함시키기
						visited[nexty][nextx] =true;
						ArrayList<Integer> next = new ArrayList();
						next.add(nexty);
						next.add(nextx);
						queue.add(next);
						united.addContry(next,desti);
					}
				}
			}
		}
		// 4-3. 현재 연합국이 1개라도 추가된 경우
		if(united.contries.size()>0) {
			// 4-3-1. 연합국에 소속된 나라들 인구 이동 시작
			united.uniformPeople(map);
			return true;
		}
		// 4-4. 연합국이 없는 경우
		else {
			return false;
		}
		
		
	}

}
