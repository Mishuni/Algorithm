public class Stack_Queue_5 {

	public static void main(String[] args) {
		String arrangement = "()(((()())(())()))(())";
		int total = 0;
		int current = 0;
		for(int i=0; i<arrangement.length(); ++i) {
			
			char tmp = arrangement.charAt(i);
			if(tmp=='(') {
				if(arrangement.charAt(i+1)==')') {
					total += current;
					++i;
				}
				else {
					++current;
				}
			}
			else {
				++total;
				--current;
			}
			
		}
		System.out.println(total); 
		
	}

}

