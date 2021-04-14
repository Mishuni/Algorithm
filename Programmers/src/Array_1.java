import java.util.Arrays;

public class Array_1 {

	public static void main(String[] args) {
		
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] command = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		
		System.out.println(Arrays.toString(solution(array,command)));
	}
	
	 public static int[] solution(int[] array, int[][] commands) {
	        int[] answer = new int[commands.length];
	        
	        for(int i=0; i<commands.length; ++i){
	            
	            int[] sub = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
	            Arrays.sort(sub);
	            answer[i]=sub[commands[i][2]-1];
	            
	        }
	        
	        return answer;
	    }
}
