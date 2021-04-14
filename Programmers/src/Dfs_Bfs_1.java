import java.util.Arrays;

public class Dfs_Bfs_1 {
	
	public static int ans;
	
	public static void main(String[] args) {
		
		ans = 0;
		int[] numbers = {2,1,1,1,2,5};
		int target = 3 ;
		
		int sum = 0;
		for(int i=0; i<numbers.length; ++i) {
			sum += numbers[i];
		}
		if(sum==target) {
			System.out.println(1);
			return ;
		}
		if((sum%2==0) && (target%2!=0)) {
			// sum은 짝수,타겟은 홀수인 경우
			System.out.println(0);
			return;
		}
		else if((sum%2!=0) && (target%2==0)) {
			// sum은 홀수, 타겟은 짝수인 경우
			System.out.println(0);
			return;
		}
		
		dfs(numbers, target, sum);
		System.out.println(ans);
	}
	
	public static void dfs(int[] numbers, int target, int sum) {
		
		if(sum < target) {
			return ;
		}

		if( sum == target ) {
			++ans;
			System.out.println(Arrays.toString(numbers));
			return ;
		}
		
		if(numbers.length==1) {
			if(sum-numbers[0]*2 == target) {
				System.out.println(Arrays.toString(numbers));
				System.out.println(sum);
				++ans;
			}
			return;
		}
		
		for(int i=0; i<numbers.length; ++i) {
			// 해당 숫자 뺀 
			dfs(Arrays.copyOfRange(numbers, i+1, numbers.length)
					, target, sum-numbers[i]*2);

		}
		
	}

}
