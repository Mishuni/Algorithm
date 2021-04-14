import java.util.Arrays;
import java.util.Scanner;
public class Bj1654 {
	public static long max_result;
	public static long[] lines;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int K = sc.nextInt();
		long N = sc.nextInt();
		lines = new long[K];
		for(int i=0; i<K; ++i) {
			lines[i] = sc.nextInt();
		}
		sc.close(); sc= null;
		Arrays.sort(lines);
		if(N==1) {
			System.out.println(lines[K-1]);
			return;
		}
		else if(K==1) {
			System.out.println(lines[K-1]/N);
			return;
		}
		else if(N==2) {
			System.out.println((lines[K-1]/2 > lines[K-2])? lines[K-1]/2 : lines[K-2]);
			return;
		}
		else {
			long left = lines[K-1]/N ;
			long right = lines[K-2]+1;
			max_result = 0 ;
			if(left>=right) {
				System.out.println(left);
				return;
			}
			
			while(left<=right) {
				long half = (left+right)/(long)2;
				if(half==0) half = 1;
				long sum = 0 ;
				for(int i=0; i<K; ++i) {
					sum += lines[i]/half;
				}
				if(sum<N) {
					right = --half;

				}else {
					if(half>max_result) {
						max_result = half;
					}
					left = ++half; 
				}
			}
		}
		System.out.println(max_result);
	}
	

}
