import java.util.Scanner;

public class Bj13460_2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		char[][] board = new char[N][M];
		int[] goal = new int[2];
		
		for(int i=0; i<N; ++i) {
			String tmp = sc.next();
			board[i] = tmp.toCharArray();
		}
		
		// BFS
		
		
	}

}
