import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sw1244 {
	public static int result;
	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("./src/1244.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
				
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int number=sc.nextInt();
			int cnt=sc.nextInt();
			
			// 1. 숫자 하나 씩 떼어내어서 char array로 만들
			char[] numbers = Integer.toString(number).toCharArray();
			result = 0;
			
			// 2. 최대 교환 횟수는 총 숫자를 넘지 않게 하고, dfs 탐색 시작
			if(numbers.length<cnt) cnt = numbers.length;
			dfs(cnt,0, numbers);
				
			// 3. 결과 출력
			System.out.printf("#%d %d%n",test_case, result);
			
		}
		
	}
	
	// 4. dfs 함수
	public static void dfs(int cnt, int start, char[] numbers) {
		
		// 4-1. 기저 조건
		if(cnt==0) {
			int current = charArrToInt(numbers);
			if(current>result) {
				result = current;
			}
			return;
		}

		// 4-2. 교환 실행
		for(int i=start; i<numbers.length-1; ++i) {
			for(int j=i+1; j<numbers.length; ++j) {
				int src = Integer.parseInt(String.valueOf(numbers[i]));
				int trg = Integer.parseInt(String.valueOf(numbers[j]));
				
				numbers[i] = (char)(trg+'0');
				numbers[j] = (char)(src+'0');
				dfs(cnt-1, i, numbers);
				numbers[i] = (char)(src+'0');
				numbers[j] = (char)(trg+'0');
				
			}
		}
		
	}
	
	// 5. char array to int
	public static int charArrToInt(char[] numbers) {
		return Integer.parseInt(new String(numbers));
	}

}

