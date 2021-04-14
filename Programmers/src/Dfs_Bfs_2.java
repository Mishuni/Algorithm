import java.util.LinkedList;
import java.util.Queue;

public class Dfs_Bfs_2 {

	
	public static void main(String[] args) {
		
		int n = 3; 
		int[][] computers = 
			{{1, 1, 0}, 
			 {1, 1, 1}, 
			 {0, 0, 1}};
		boolean[] checked = new boolean[n];
		int answer = 0;
		
		Queue<Integer> list = 
				new LinkedList<Integer>();
		list.add(0);
		
		// bfs 로 한 구역 뽑아내기
		while(!list.isEmpty()) {
			int tmp = list.remove();
			checked[tmp] = true;
			for(int i=1; i<n; ++i) {
				if(!checked[i] && computers[tmp][i]==1) {
					list.add(i);
					checked[i] = true;
				}
			}
			if(list.isEmpty()) {
				// 같은 구역 검사가 끝나면 네트워크 구역 추가
				++answer;
				for(int j=1; j<n; ++j) {
					if(!checked[j]) {
						// 검사를 못한 구역(있으면) 부터 다시 시작
						list.add(j);
						break;
					}
				}
			}
		}
		
		System.out.println(answer);
		
	}
    
}
