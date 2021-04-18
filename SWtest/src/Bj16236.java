import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 먹을 수 있는 물고기 정보 저장하는 클래스
class Position implements Comparable<Position>{
	int y, x, distance,status;
	public Position(int y, int x, int distance, int status) {
		this.y = y;
		this.x = x;
		this.distance = distance;
		this.status = status;
	}
	
	@Override
	public int compareTo(Position o) {
		
		if(this.y<o.y) return -1;
		if(this.y>o.y) return 1;
		if(this.y==o.y) return (this.x>o.x)? 1:-1 ;
		
		return 0;
	}

}

public class Bj16236 {
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static int[][] ocean;
	public static int N, size, time, eated;
	public static final int[][] DIRECTIONS = {{0,1},{1,0},{-1,0},{0,-1}};
	public static Queue<ArrayList<Integer>> queue;
	public static ArrayList<Integer> start;
	public static int[] count;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 1. Initialize
		N = stoi(st.nextToken());
		ocean = new int[N][N];
		count = new int[7]; // 각 사이즈의 물고기 갯수
		size = 2; // 현재 아기 상어 사이즈
		eated = 0; // 지금 사이즈에서 먹은 물고기 갯수
		time = 0; // 총 이동 시간
		
		queue = new LinkedList();
		start = new ArrayList();
		
		// 1-1. 공간 정보 저장
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				int tmp = stoi(st.nextToken());
				ocean[i][j] = tmp;
				// 1-1-1. 현재 값이 9이면 아기상어 위치 저장
				if(tmp==9) {
					// 1-1-1-1. bfs 의 첫번째 탐색 지점으로 추가
					start.add(i);
					start.add(j);
					queue.add(start);
				}
				// 1-1-2. 현재 값이 0이면 pass
				else if(tmp==0) {
					continue;
				}
				// 1-1-3. 물고기가 있는 공간이면 사이즈에 맞개 갯수 추가해주기
				else {
					// 1-1-3-1. tmp 사이즈 물고기 1개 추가
					count[tmp]+=1;
				}
 			}
		}
		
		// 2. 아기상어 보다 작은 물고기가 1개라도 있으면 bfs 시도하기
		while(check()) {
			// 2-1. 현재 위치는 0으로 만들고
			// bfs 로 거리가 제일 가깝고 먹을 수 있는 물고기 위치 찾기
			ocean[start.get(0)][start.get(1)]=0;
			Position target = bfs();
			
			// 2-2. 물고기 체크
			// 2-2-1. 먹을 수 있는 물고기 없으면 엄마 상어에게 구조 요청
			if(target==null) {
				break;
			}
			// 2-2-2. 먹을 수 있는 물고기 있는 경우
			else {
				// 2-2-2-1. 해당 물고기 사이즈 갯수 1개 줄이기
				count[target.status] -= 1;
				// 2-2-2-2. start 지점을 해당 물고기 위치로 옮기기
				queue.clear();
				start.set(0, target.y);
				start.set(1, target.x);
				queue.add(start);
				// 2-2-2-3. 해당 물고기까지의 이동 시간 추가, 먹은 갯수 추가
				time += target.distance;
				++eated;
				// 2-2-2-4. 먹은 갯수가 상어 크기와 같고 상어 사이즈가 6이하이면
				if(size<=6 && eated==size) {
					// 2-2-2-4-1. 상어 크기 키워주기
					if(size==6) {
						size = 7;
					}else {
						++size;
					}
					eated=0;
				}
			}
		}
		// 3. 결과 출력
		System.out.println(time);
	}
	
	// count 에서 상어 사이즈보다 작은 물고기가 1개라도 있는지 확인
	public static boolean check() {
		for(int i=1; i<size; ++i) {
			if(count[i]>0) {
				return true;
			}
		}
		return false;
	}
	
	// BFS 함수
	public static Position bfs() {
		
		int cnt = 1;
		int level = 1;
		int pre = 0;
		boolean find = false;
		// 같은 거리를 가진 물고기 중에서 행,열이 가장 작은 물고기를 찾기 위해 우선순위큐 사용
		PriorityQueue<Position> pq = new PriorityQueue();
		boolean[][] visited = new boolean[N][N];
		
		while(!queue.isEmpty()) {
			start = queue.remove();
			--cnt;
			// 현재 위치에서 4가지 방향으로 물고기 체크
			for(int i=0; i<4; ++i) {
				int nexty = start.get(0)+DIRECTIONS[i][0];
				int nextx = start.get(1)+DIRECTIONS[i][1];
				
				if(nexty>=0 && nexty<N && nextx>=0 && nextx<N && !visited[nexty][nextx]) {
					int status = ocean[nexty][nextx];
					visited[nexty][nextx] = true;
					// 만약 이미 먹을 수 있는 물고기 나왔으면, 동일한 거리에서 또 먹을 수 있는게 있는지 탐색
					if(find) {
						if(status<size && status!=0) {
							// find same prior
							Position pos = new Position(nexty,nextx,level,status);
							pq.add(pos);
						}
					}
					// 아직 먹을 수 있는 물고기 안나온 상황
					else {
						// 해당 거리에서 처음으로 먹을 수 있는 물고기가 나왔으면
						if(status<size && status!=0) {
							// 해당 물고기 우선순위큐(다음 먹이 후보군)에 넣기
							find = true;
							Position pos = new Position(nexty,nextx,level,status);
							pq.add(pos);
						}
						
						// 지나갈 수만 있는 상황
						else if(status==size || status==0) {
							// 해당 위치는 지나갈 수만 있으므로 queue에 추가
							ArrayList<Integer> tmp = new ArrayList();
							tmp.add(nexty); tmp.add(nextx);
							queue.add(tmp);
							++pre;
						}
					}
				}
			}
			
			// next level
			if(cnt==0) {
				// 만약 먹을 수 있는 물고기 찾은 상태면 루프 종료
				if(find) break;
				// 그런게 아니라면 다음 거리 레벨로 넘어가기
				cnt = pre;
				pre = 0;
				level += 1;
			}
			
		}
		
		// 먹이 후보군이 하나라도 있었다면, 가장 첫번째 요소 리턴하기
		if(pq.size()>0) {
			return pq.remove();
		}
		else {
			return null;
		}
		
	}

}
