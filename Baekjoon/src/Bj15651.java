import java.util.Scanner;

public class Bj15651 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int min , max ;
		StringBuilder s1 = new StringBuilder("1");
		StringBuilder s2 = new StringBuilder(N);
		for(int i=1; i<M; ++i) {
			s1.append("1");
			s2.append(N);
		}
		min = Integer.parseInt(s1.toString());
		max = Integer.parseInt(s2.toString());
		
		for(int i=min; i<max; ++i) {
			
		}
	}

}
