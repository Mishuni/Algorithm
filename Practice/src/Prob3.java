
// 13:07 시작 15:33 끝 -> 2시간 30분 걸림 ㅠ 

public class Prob3 {
	
	public static int count;

	public static void main(String[] args) {
		
		int[][] key = { 
				{ 1, 0, 0 }, 
				{ 1, 0, 0 }, 
				{ 1, 0, 0 } };

		int[][] lock = { 
				{ 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1 }, 
				{ 0, 0, 1, 1 } };

		int M = key.length;
		int N = lock.length;

		// 1. lock 에서 홀의 갯수 구하기
		int holl = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (lock[i][j] == 0) {
					++holl;
				}
			}
		}
		
		// 2. key에서 돌기의 갯수 구하기
		int block = 0;
		for (int i = 0; i < M; ++i) {
			for (int j = 0; j < M; ++j) {
				if (key[i][j] == 1) {
					++block;
				}
			}
		}
		
		// 3. 홀과 돌기 갯수를 활용한 사례 쳐내기
		
		// 3-1. 전체 돌기가 홀보다 적으면 무조건 실패
		if (block < holl) {
			System.out.println(false);
			return;
		}
		
		// 3-2. 홀이 없는 경우는 무조건 성공
		if (holl == 0) {
			System.out.println(true);
			return;

		} 
		
		// 3-3. lock에 홀 밖에 없는 경우
		else if (holl == N * N) {
			// 3-3-1. key가 lock이랑 똑같으면 성공
			if (N == M && holl == block) {
				System.out.println(true);
				return;
			} 
			// 3-3-2. 그 외는 모두 실패
			else {
				System.out.println(false);
				return;
			}
		}
		
		// 4. 완전 탐색으로 가능한 경우 탐색
		for (int r = 0; r < 4; ++r) {
			// 4-1. key 의 (0,0)을 기준으로 이동하면서
			// lock 과 겹치는 부분을 확인
			for (int i = -M; i < N; ++i) {
				for (int j = -M; j < N; ++j) {
					// 4-1-1. (i,j) 부터 (i+M, j+M) 좌표에 위치한
					// key 행렬에서 lock행렬과 겹치는 부분을 찾기
					// 효율성을 위해 끝 점이 N을 넘어가면 lock의 끝 위치로 끝 좌표를 수정
					int i2 = (i+M < N)? i+M-1 : N-1;
					int j2 = (j+M < N)? j+M-1 : N-1;
					// 4-1-2. key와 lock이 현 위치 상황에서 알맞게 되는지 확인
					// 알맞게 되고, 전체 홀이 다 커버가 되었으면 성공
					if(check(key,lock,i,j,i2,j2) && count == holl) {
						System.out.println(true);
						return;
					}
				}
			}
			// 4-2. 배열 회전 시키기, 마지막에는 회전 안하기
			if (r != 3) {
				key = rotation(key, M);
			}
		}
		System.out.println(false);
		// return false;
	}
	
	// key, lock이 현재의 위치에서 맞는지 확인
	public static boolean check(int[][] key, int[][] lock, int i, int j, int i2, int j2) {
		count = 0;
		int r2 =0 ; int c2 = 0;
		for (int row = i; row <= i2; ++row) {
			c2=0;
			for (int col = j; col <= j2; ++col) {
				
				if (row >= 0 && row < lock.length && col >= 0 && col <= lock.length) {
					if (lock[row][col] + key[r2][c2] != 1) {
						return false;
					}
					if (lock[row][col] == 0) {
						++count;
					}
				}
				++c2;
			}
			++r2;
		}
		return true;
	}
	
	// 2차원 배열 90도 회전 시키는 함수
	public static int[][] rotation(int[][] map, int n) {
		int[][] tmp = new int[n][n];

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				tmp[i][j] = map[n - 1 - j][i];

			}
		}
		return tmp;
	}

}
