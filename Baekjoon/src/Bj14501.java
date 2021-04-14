import java.util.Scanner;

public class Bj14501 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] T = new int[N];
		int[] P = new int[N];
		int[] dp = new int[N];
		for(int i=0; i<N; i++) {
			T[i]=sc.nextInt();
			P[i]=sc.nextInt();
		}
		if(T[N-1]==1) {
			dp[N-1]=P[N-1];
		}else {
			dp[N-1]=0;
		}
		for(int i=N-2; i>=0; i--) {
			if(i+T[i]<N) {
				dp[i] = Math.max(dp[i+1], P[i]+dp[i+T[i]]);
			}else if(i+T[i]==N) {
				dp[i] = Math.max(dp[i+1],P[i]);
			}
			else {
				dp[i] = dp[i+1];
			}
		}
		System.out.println(dp[0]);
	}

}