import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj11403 {
	public static int N;
	public static int[][] graph;
	public static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		graph = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = sc.nextInt();
			}
		}
		bfs();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%d ",graph[i][j]);
			}
			System.out.println();
		}
		sc.close();sc = null;
	}

	public static void bfs() {

		Queue<Integer> x = new LinkedList<Integer>();
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			for (int j = 0; j < N; j++) {
				if (graph[i][j] == 1 && !visited[j]) {
					visited[j] = true;
					x.add(j);
				}
			}
			while (!x.isEmpty()) {
				int tmp = x.poll();
				for (int j = 0; j < N; j++) {
					if (graph[tmp][j] == 1 && !visited[j]) {
						visited[j] = true;
						graph[i][j] = 1;
						x.add(j);
					}
				}
			}
		}

	}
}
