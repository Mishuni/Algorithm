import java.util.LinkedList;
import java.util.Scanner;

public class Bj15649 {

	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		// checked = new boolean[n+1];
		// list = new LinkedList<Integer>();
		arr = new int[m];
		dfs(n, m);
		sc.close();
	}

	public static void dfs(int n, int m) {

		// 기저 사례
		if (m == 0) {
			for (int i = 0; i < arr.length; ++i) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}

		// 완전 탐색
		for (int i = 1; i <= n; ++i) {
			if (arr.length - m > 0) {
				if (arr[arr.length - m - 1] <= i) {
					arr[arr.length - m] = i;
				} else {
					continue;
				}
			}
			else {
				arr[0] = i;
			}
			dfs(n, m - 1);
		}

	}

}
