import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj14889 {
	
	public static int N, half, total, min;
	public static int[][] table;
	public static boolean[] visited;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. init
		N = stoi(st.nextToken());
		half = N/2;
		
		table = new int[N][N];
		total = 0 ;
		min = Integer.MAX_VALUE;
		visited = new boolean[N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; ++j) {
				
				int tmp = stoi(st.nextToken());
				table[i][j] = tmp;
				total += tmp;
				
			}
		}
		
		// 2. 시작점이 절반을 넘어가면 스타트 팀과 링크팀만 반대로 될 뿐
		// 앞에서 계산한 차이값은 똑같으므로 절반까지만 탐색
		for(int i=0; i<N/2; ++i) {
			dfs(0,0,i);
		}
		// 3. 결과 출력
		System.out.println(min);
		
	}
	
	// 4. dfs
	public static void dfs(int step, int sum, int start) {
		visited[start] = true;
		// 4-1. 기저 함수, N/2 개가 선택된 경우
		if(step == half-1) {
			int sum2 = total;
			for(int i=0; i<N; ++i) {
				if(!visited[i]) {
					for(int j=0; j<N; ++j) {
						if(visited[j]&&i!=j) {
							// 4-2. 전체 값에서 visited 한곳과 안한곳의 능력치 빼주기
							sum2 -= (table[i][j]+table[j][i]);
						}
					}
				}
			}
			// 4-2. 남은 값에서 기존 선택된 능력치의 2배를 빼주면 최종 차이값
			// 차이값이 최소값이면 저장
			int diff = Math.abs(sum2 - 2*sum);
			min = (diff<min) ? diff : min;
			visited[start] = false;
			return;
			
		}
		
		// 4-2. dfs 시작
		for(int i=start+1; i< N; ++i) {
			int added = sum;
			for(int j=0; j<i; ++j) {
				if(visited[j]&&i!=j) {
					// 2-1. 새로 추가되는 요소와 기존 추가된 요소간의 능력치 추가
					added += table[i][j] + table[j][i];
				}
			}
			dfs(step+1, added, i);
		}
		visited[start] = false;
		
	}

}
