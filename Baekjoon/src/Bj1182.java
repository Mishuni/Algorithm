import java.util.Scanner;

public class Bj1182 {
	public static int S, result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		S = sc.nextInt();
		int[] number = new int[N];
		result = 0;
		for(int i=0; i<N; i++) {
			number[i]=sc.nextInt();
		}
		for(int i=1; i<(1<<N); i++) {
			int sum = 0;
			for(int j=0; j<N; j++) {
				if((i&(1<<j))!=0) {
					sum+=number[j];
				}
			}
			if(sum==S) {
				result++;
			}
		}
	
		System.out.println(result);
	}
}
