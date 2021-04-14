import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Bj2668 {

	static int[] number;
	static int first;
	static boolean[] visited;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		number = new int[N + 1];
		visited = new boolean[N+1];
		list = new ArrayList<Integer>();
		for (int i = 1; i <= N; ++i) {
			number[i] = sc.nextInt();
		}
		sc.close(); sc= null;
		
		for(int i=1; i <=N; i++) {
			visited[i] = true;
			first = i;
			dfs(i);
			visited[i] = false;
		}
		Collections.sort(list);
		System.out.println(list.size());
		for(int i:list) {
			System.out.println(i);
		}

	}
	public static void dfs(int start) {
		if(!visited[number[start]]) {
			visited[number[start]] = true;
			dfs(number[start]);
			visited[number[start]] = false;
		}
		if(number[start]==first) {
			list.add(first);
		}
	}

}

/*
 * 1 2 3 4 5 6
 * 2 3 1 6 5 5
 * -> 4 1 2 3 5 
*/