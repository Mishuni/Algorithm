import java.util.Scanner;
public class Bj2193 {

	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		int N = sc.nextInt();
		
		if(N==1) {System.out.println(1); return;}
		else if(N>=2) {
			long[] dp = new long[N+1];
			dp[0] = 0; dp[1] = 1;
			dp[2] = 1;
			for(int i=3;i<N+1;i++) {
				dp[i]=dp[i-2]+dp[i-1];
			}
			System.out.println(dp[N]);
		}
		// 1 1
		// 2 1
		// 3 2
		// 4 3 1000 1010 1001
		// 5 5  10000 10100 10010 10001 10101
		// 6 8  100000 100001 101000 100100 100010 101010 101001 100101 
		
	}

}
