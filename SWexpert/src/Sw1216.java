import java.util.Scanner;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sw1216 {

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("1216.txt"));
		Scanner sc = new Scanner(System.in);
		int n = 100;
		char[][] board = new char[n][n];
		int max = 1;

		// greedy + 완벽 탐색
		// 탐색 길이의 시작을 기준으로 가능한 가로 세로 다 탐색
		for (int test_case = 1; test_case <= 10; test_case++) {
			sc.nextInt();

			// 1. 문자 입력받기
			for (int i = 0; i < n; ++i) {
				String line = sc.next();
				for (int j = 0; j < n; ++j) {
					board[i][j] = line.charAt(j);
				}
			}

			// 2. 제일 긴 후보군 부터 탐색
			boolean check = false;
			int len = n;

			while (len > 1 && !check) {
				// 시작 기준 점의 범위
				int start = 0;
				int end = n - len;

				for (int i = 0; i < n; ++i) {

					for (int c = start; c <= end; ++c) {
						// 가로 검색
						for (int t = 0; t < len / 2; ++t) {
							if (board[i][c+t] != board[i][c + len - 1 - t]) {
								break;
							} else {
								if (t == len / 2 - 1) {
									max = len;
									check = true;
									break;
								}
							}
						}
 
						// 세로 검색
						for (int t = 0; t < len / 2; ++t) {
							if (board[c + t][i] != board[c + len - 1 - t][i]) {
								break;
							} else {
								if (t == len / 2 - 1) {
									max = len;
									check = true;
									break;
								}
							}
						}
					}
					
					if(check) {
						break;
					}
				}
				--len;
			}

			// 3. 결과 출력
			System.out.println("#" + test_case + " " + max);

		}
	}

}