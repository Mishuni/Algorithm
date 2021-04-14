import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sw2112 {
	public static int min_cnt;
	public static boolean[] pass;
	public static int[] medicine;
	public static int[][] surface;
	public static int D,W,K,M;
	
	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("./src/2112.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
			
		for(int test_case = 1; test_case <= T; test_case++) {
			
			// 1. Initialize
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			min_cnt = Integer.MAX_VALUE;
			
			surface = new int[D][W];
			
			for(int i=0; i<D; ++i) {
				for(int j=0; j<W; ++j) {
					surface[i][j] = sc.nextInt();
				}
			}
			
			// 1. 처음부터 성능검사 통과인지 확인
			// first check
			boolean possible = check(surface);
			if(possible) System.out.printf("#%d %d%n",test_case,0);
			
			// 2. 추가 약물 투입 시작
			else {
				medicine = new int[D];
				// 2-1. 1개부터 D개 까지 약물 늘려가며 확인
				for(int i=1; i<=D; ++i) {
					// M은 투입 약물 갯수
					M = i;
					// 한번이라도 True 가 나오면 그 i 값이 결과
					if(dfs(0,0)) {
						min_cnt = i;
						break;
					}
				}
				
				// 2-2. 결과값 출력
				System.out.printf("#%d %d%n",test_case,min_cnt);
			}
			
		}
		
	}
	
	// 3. 깊이 우선 탐색
	public static boolean dfs(int step, int pre) {
		
		// 3-1. 기저함수 : 현재 약물 갯수가 충족된 경우
		if(step == M) {
			
			// 3-1-1. 기존 단면 복사
			int[][] copy =new int[D][W];
			for(int r=0; r<D; ++r) {
				System.arraycopy(surface[r], 0, copy[r], 0, surface[r].length);
			}
			
			// 3-1-2. 약물 투입할 행에 해당 특성 번호 업데이트
			for(int r=0; r<D; ++r) {
				if(medicine[r]!=0) {
					for(int c=0; c<W; ++c) {
						// 3-1-2-1. 기존 medicine에 해당 특성의 +1 을 저장했으므로 1빼주고 업데이트
						copy[r][c] = medicine[r]-1;
					}
				}
			}
			
			// 3-1-3. 변경한 단면이 통과되는지 확인
			return (check(copy)) ;
			
		}
		
		// 3-2. 다음 약물 투입 행 탐색
		for(int i=pre; i <D; ++i ) {
			
			// 3-2-1. 이 행에 A 특성 넣기
			medicine[i] = 1; // A
			if(dfs(step+1,i+1)) return true;
			// 3-2-2. 이 행에 B 특성 넣기
			medicine[i] = 2; // B
			if (dfs(step+1,i+1)) return true;
			// 3-2-3. 이 행에 약물 안 넣기
			medicine[i] = 0;
			
		}
		
		return false;
		
	}
	
	// 4. 성능 검사 통과되는지 확인하는 함수
	public static boolean check(int[][] sf) {
		
		for(int j=0; j<W; ++j) {
			int pre = -1;
			int cnt = 0;
			for(int i=0; i<D; ++i) {
				
				int current = sf[i][j];
				if(current == pre) {
					++cnt;
					if(cnt>=K) {
						break;
					}
					continue;
				}
				
				cnt = 1;
				pre = current;
				
			}
			if(cnt<K) {
				return false;
			}
		}
		
		return true;
		
	}

}
