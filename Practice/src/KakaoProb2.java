import java.util.*;
public class KakaoProb2 {
	static boolean[] visited;
	static int[] menu;
	static int maxVal = 0;
	static ArrayList<String> resultM;
	public static void main(String[] args) {
		//ABCD
		//AB
		//   B   D
		//A      D
		// --
		
		// A  : 11011
		// B :  11101   // 3
		// C :  10001   // 2
		// D:   11111  //  4
		 
		String[] orders= {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}; 
		//String[] orders= {"ABCDE", "ABE", "BDE", "ADE"}; 
		int[] course = {2,3,4};
		
		
		ArrayList<String> answers = new ArrayList<String>();
	
		menu = new int[26];
		visited = new boolean[26];
		resultM = new ArrayList<String>();

		
		int step = 0;
		for(int i=0; i<orders.length; ++i ) {
			StringBuilder order = new StringBuilder(orders[i]);
			for(int j=0; j<order.length(); ++j) {
				menu[order.charAt(j)-'A'] +=  1 << step;
			}
			step +=1;
		}
		
		for(int i=0; i<26; ++i) {
			if(Integer.bitCount(menu[i])<1) {
				menu[i]=0;
			}
		}

		
		for(int num : course) {
			maxVal = 0;
			resultM.clear();
			for(int j=0; j<26; ++j) {
				visited[j]= false;
			}
			dfs(0,num,0);
			for(String a : resultM) {
				answers.add(a);
			}
			
			
			
			
		}
		

		Collections.sort(answers);
		
		int i = 0;
		String[] answer  = new String[answers.size()];
		for( String a : answers) {
			answer[i++]  = a;
		}
	
	}
	
	public static void dfs(int depth, int num, int start) {
		
		if(depth == num) {
			
			int result = 0;
			StringBuilder tmp = new StringBuilder();
			for(int b=0; b<visited.length; ++b) {
				if(visited[b]) {
					
					
					result = (tmp.length()==0)?    menu[b]  : (result & menu[b]);
					
					
					tmp.append((char)(b+'A'));
				}
			}
			 
			
			if(Integer.bitCount(result)>maxVal) {
				maxVal = Integer.bitCount(result);
				if(Integer.bitCount(result)>1)
				{
					resultM.clear();
					resultM.add(tmp.toString());
		
				}
				
			}
			else if(Integer.bitCount(result)==maxVal && (Integer.bitCount(result)>1)) {
				resultM.add(tmp.toString());
			}
			
			
			return;
		}
		
		for(int i=start; i<menu.length; ++i) {
			
			if( Integer.bitCount(menu[i]) >= maxVal && !visited[i]) {
				// bit count satisfied
				visited[i] = true;
				dfs(depth+1,num,i+1);
				visited[i] = false;
				
			}
		}
		
		
	}

}
