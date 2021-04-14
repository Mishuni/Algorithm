import java.util.Arrays;

public class Stack_Queue_6 {

	public static void main(String[] args) {
		int[] price = {1,2,3,2,4};
		int[] answer = new int[price.length];
		
		for(int i=0; i<price.length-1; ++i) {
			
			for(int j=i+1; j<price.length; ++j) {
				if(price[j]<price[i]) {
					answer[i] = j-i;
					break;
				}
				if(j==price.length-1) {
					answer[i] = price.length-1-i;
				}
			}
			
		}
		System.out.println(Arrays.toString(answer));
		
		
	}

}

