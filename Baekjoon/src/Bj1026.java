import java.util.Arrays;
import java.util.Scanner;

public class Bj1026 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] A = new int[N];
		int[] B = new int[N];
	
		for(int i=0; i<N; i++) {
			A[i]=sc.nextInt();
		}
		Arrays.sort(A);
		for(int j=0; j<N; j++) {
			B[j]=sc.nextInt();
		}
		Arrays.sort(B);
		
		int S = 0;
		for(int i=0; i<N; i++) {
			int tmp = A[N-i-1]*B[i];
			S +=tmp;
		}
		sc.close(); sc = null;
		System.out.println(S);
	}

}
