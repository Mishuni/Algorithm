import java.util.Scanner;
import java.io.FileInputStream;

public class Sw1486 {
	static int N, B;
	static int[] tall;
	static int result, subresult;

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("./src/1486.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			B = sc.nextInt();
			tall = new int[N];
			for (int i = 0; i < N; ++i) {
				tall[i] = sc.nextInt();
			}
			subresult = 0;
			result = Integer.MAX_VALUE;
			dfs(0);
			System.out.printf("#%d %d\n", test_case, result - B);
		}
	}

	public static void dfs(int start) {
		if (start == N - 1) {
			if (subresult >= B) {
				if (result < subresult)
					return;
				else if (result > subresult) {
					result = subresult;
					return;
				}
			} 
			else {
				subresult += tall[start];
				if (subresult >= B && result > subresult) {
					result = subresult;
				}
				subresult -= tall[start];
			}
			return;
		} 
		else {

			subresult += tall[start];
			dfs(start + 1);
			subresult -= tall[start];
			dfs(start + 1);

		}
	}

}