import java.util.Arrays;
import java.util.Comparator;

public class Greedy_6 {

	public static void main(String[] args) {
		int n = 6;
		int[][] costs 
		//={{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		//={{0,1,1},{0,2,2},{1,2,5},{1,3,3},{2,3,8},{3,4,1}};
		={{0,3,1},{5,0,2},{1,3,1},{1,4,2},{4,2,6},{1,5,3}};
		// [[0,1,1],[0,2,2],[1,2,5],[1,3,3],[2,3,8],[3,4,1]] 

		Arrays.sort(costs, new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				if(arg0[2]<arg1[2]) return -1;
				if(arg0[2]<arg1[2]) return 0;
				return 1;
			}
			
		});
		
		int[] checked = new int[n];
		boolean[] visited = new boolean[n];
		for(int i=0; i<n ; ++i) {
			checked[i] = i;
		}
		int sum = 0 ;
		for(int i=0; i<costs.length; ++i) {
			int[] start = costs[i];
			if(checked[start[0]]!=checked[start[1]] && (!visited[start[0]] || !visited[start[1]]) ) {
				if(start[0]<start[1])
				checked[start[1]] = findRoot(checked,start[0]);
				else {
					checked[start[0]] = findRoot(checked,start[1]);
				}
				sum += start[2];
				visited[start[0]] =true;
				visited[start[1]] =true;
			}
			for(int j=0; j<n; ++j) {
				
				checked[j] = findRoot(checked,checked[j]);
			}
		}
		System.out.println(Arrays.toString(checked));
		System.out.println(sum);
		
	}
	
	public static int findRoot(int[] checked, int parent) {
		if(checked[parent]==parent) {
			return parent;
		}
		else {
			return findRoot(checked, checked[parent]);
		}
	}

}
