import java.util.Arrays;
import java.util.Scanner;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sw1215 {

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("1215.txt"));
		Scanner sc = new Scanner(System.in);
		
		char[][] board = new char[8][8];

		// greedy + 완벽 탐색
		// 탐색 길이의 시작을 기준으로 가능한 가로 세로 다 탐색 
		for(int test_case = 1; test_case <= 10; test_case++)
		{	
			int answer = 0 ;
			int len =sc.nextInt();
			
			// 1. 회문 길이가 1인 경우
			if(len==1) {
				System.out.println("#"+test_case+" "+64);
				continue;
			}
			
			// 2. 1이 아닌 경우 문자 입력받고 완전 탐색 
			
			for(int i=0; i<8; ++i) {
				String line = sc.next();
				for(int j=0; j<8; ++j) {
					board[i][j]=line.charAt(j);
					
					// 2-1. 가로 탐색
					if(j>len-2) {
						if(checkPalindrome(Arrays.copyOfRange(board[i], j-len+1, j+1))) {
							++answer;
						}
					}
				}
				// 2-2. 세로 탐색
				if(i>len-2) {
					char[] tmp = new char[len];
					for(int j=0; j<8; ++j) {
						int t = 0;
						for(int c=i-len+1; c<i+1; ++c) {
							tmp[t++]=board[c][j];
						}
						if(checkPalindrome(tmp)) {
							++answer;
						}
					}
				}

			}
			// 4. 결과 출력
			System.out.println("#"+test_case+" "+answer);
			
		}
	}
	
	// 3. 회문인지 아닌지를 판단하는 함수
	public static boolean checkPalindrome(char[] tmp) {
		// 3-1. 회문 길이가 2인 경우
		if(tmp.length==2) {
			if(tmp[0]==tmp[1]) {
				return true;
			}
			
			return false;
		}
		
		int half = tmp.length/2;
		
		// 3-2. 길이의 중간을 기준으로 회문인지 아닌지 판단
		for(int c=0; c<half; ++c) {
			if(tmp[c]!=tmp[tmp.length-1-c]) {
				return false;
			}
		}
		return true;
	}

}