import java.util.Scanner;

public class Bj13458 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		long[] stu = new long[N];
		
		
		
		for(int i=0; i<N; ++i) {
			
			stu[i] = sc.nextLong();
			
		}
		
		long B = sc.nextInt();
		long C = sc.nextInt();
		
		long total = 0;
		for(int i=0; i<N; ++i) {
			
			if(stu[i]<=B) {
				++total;
			}
			else {
				
			total += Math.ceil((double)(stu[i]-B)/(double)C)+1;
				
			}
			
		}
		System.out.println(total);
	}

}
