import java.util.*;

public class Prob6 {

	public static void main(String[] args) {
		// Prob1. 배열을 순차적으로 2개로 쪼개는데
		// 왼쪽 배열의 총 합이 오른쪽 배열의 총 합보다 큰 경우의 수
		List<Integer> list = new LinkedList<Integer>();

		list.add(-5);
		list.add(-3);
		list.add(-2);
		list.add(10);
		list.add(20);
		list.add(-30);
		
		System.out.println(splitIntoTwo(list));
	}
	
	  public static int splitIntoTwo(List<Integer> arr) {

		  	int n = arr.size();
		    int count = 0;
		    int left = arr.get(0);
		    int right = 0 ;
		    for(int i=1; i<n; ++i){
		        right += arr.get(i);
		    }
		    
		    if(left>right) ++count;
		    
		    for(int i=1; i<n-1; ++i) {
		    	left += arr.get(i);
		    	right -= arr.get(i);
		    	if(left>right) {
		    		++count;
		    	}
		    }
		    return count;
	  }


}
