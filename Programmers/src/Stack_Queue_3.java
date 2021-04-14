
public class Stack_Queue_3 {

	public static void main(String[] args) {
		int[] priorities = {2,1,3,2};
		boolean[] print = new boolean[priorities.length];
		int location = 2 ;
		int max_value = 0;
		int max_idx = 0;
		
		for(int i=0; i<priorities.length; ++i) {
			if(priorities[i]>max_value) {
				max_value = priorities[i];
				max_idx = i;
			}
		}
		
		
		while(max_idx != location) {
			
			for(int i=0; i<priorities.length; ++i) {
				if(priorities[i]>max_value && !print[i]) {
					max_value = priorities[i];
					max_idx = i;
				}
			}
		}
	}

}

