import java.util.Arrays;

public class Stack_Queue_1 {

	public static void main(String[] args) {
		int[] heights = {3,9,9,3,5,7,2};
		int[] results = new int[heights.length];
		for(int i=heights.length-1; i>0 ;--i) {
			for(int j=i-1; j>=0; --j) {
				if(heights[j]>heights[i]) {
					results[i]=j+1;
					break;
				}
			}
		}
		System.out.println(Arrays.toString(results));

	}

}

