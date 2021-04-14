import java.util.*;

public class SchoolWay {

	public static void main(String[] args) {

		int m =4;
		int n=3; 
		int[][] puddles = {{2,2}};
		
		int[][] map = new int[n][m];
		long[][] count = new long[n][m];
		
		// 0. 환경 세팅 ( BFS 를 위한 Stack 설정 등 )
		for(int i=0; i<puddles.length; ++i) {
			map[puddles[i][1]-1][puddles[i][0]-1] = -1;
		}
		int[][] direction = {{1,0},{0,1}}; // 아래방향,오른쪽방향
		Queue<LinkedList<Integer>> check = new LinkedList<LinkedList<Integer>>();
		LinkedList<Integer> tmp = new LinkedList<Integer>();
		tmp.add(0);
		tmp.add(0);
		check.add(tmp);
		count[0][0]=1;
		
		// 1. BFS 시작
		while(!check.isEmpty()) {
			LinkedList<Integer> current = check.poll();
			int startx = current.get(0); // 세로 (x축) 의 현재 탐색 지점
			int starty = current.get(1); // 가로 (y축) 의 현재 탐색 지점
			
			if(startx == n-1 && starty ==m-1) {
				// 1-1. 마지막 도착지점인 경우 끝내기
				break;
			}
			
			for(int[] dir : direction) {
				int nextx = startx + dir[0];
				int nexty = starty + dir[1];
			
				if(nextx <n && nexty<m && map[nextx][nexty]!=-1) {
					// 1-2. 이동한 좌표가 지도 범위에 들어가는 경우
					
					int cost = map[startx][starty] +1 ;
					if( map[nextx][nexty]==0  ) {
						// 1-2-1. 지금껏 이 좌표에 도달한 경우가 없는 경우
						map[nextx][nexty] = cost;
						count[nextx][nexty] = count[startx][starty];
						tmp = new LinkedList<Integer>();
						tmp.add(nextx);
						tmp.add(nexty);
						check.add(tmp);
					}
					else if( cost == map[nextx][nexty]  ) {
						// 1-2-2. 현재 저장되어 있는 최소 경로가 지금 경로의 비용과 같은 경우
					   count[nextx][nexty] = ( count[nextx][nexty] + count[startx][starty] ) % 1000000007;
					   
					   
					}
					else if(cost < map[nextx][nexty]) {
						// 1-2-3. 현재 저장되어 있는 최소 경로가 더 큰 경우
						map[nextx][nexty] = cost;
						count[nextx][nexty] = count[startx][starty];
						continue;
					}
				}
			
			}
		}
		
		System.out.println((int)(count[n-1][m-1]% 1000000007));
 		
	}

}
