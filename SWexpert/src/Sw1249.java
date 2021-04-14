import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Sw1249 {
	
	// 지도에서 움직일 방향 (동,서,남,북)
	public static final int[][] DIRECTION = {{0,1},{0,-1},{1,0},{-1,0}};
	public static int[][] map ;
	public static int[][] visited;
	
	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("./src/1249.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int n = sc.nextInt();
			map = new int[n][n];
			visited = new int[n][n];
			
			// 1. input
			for(int r=0; r<n; ++r) {
				String[] num2 = sc.next().split("");
				
				for(int i=0; i<n; ++i) {
					map[r][i] =  Integer.parseInt(num2[i]);
					visited[r][i] = -1;
				}
			}
			
			
			// 2. start bfs
			Queue<LinkedList<Integer>> check = new LinkedList<LinkedList<Integer>>();
			LinkedList<Integer> tmp = new LinkedList<Integer>();
			tmp.add(0); tmp.add(0);
			check.add(tmp);
			visited[0][0] = 0;
			
			while(!check.isEmpty()) {
				LinkedList<Integer> current = check.poll();
				int startx = current.get(0);
				int starty = current.get(1);
				
				for( int[] direction : DIRECTION ) {
					int nextx = startx + direction[0];
					int nexty = starty + direction[1];
					
					// 2-1. 해당 방향으로 움직인 좌표가 지도 범위 안에 있으면
					if(nextx>=0 && nextx<n && nexty>=0 && nexty<n) {
						// 현재 이동 값 계산
						int step = visited[starty][startx]+map[nexty][nextx];
						// 2-1-1. 움직인 좌표를 방문해본 적이 없거나 
						// 계산된 이동 값이 기존 값보다 작은 경우
						if(visited[nexty][nextx]==-1 || visited[nexty][nextx]> step) {
							// queue에 해당 좌표 추가 하고 이동 값 갱신
							tmp = new LinkedList<Integer>();
							tmp.add(nextx);
							tmp.add(nexty);
							check.add(tmp);
							visited[nexty][nextx] = step;
						}
					}
				}
			}
			
			
			// 3. print result
			System.out.printf("#%d %d%n",test_case,visited[n-1][n-1]);
			
		}
			
	}

}
