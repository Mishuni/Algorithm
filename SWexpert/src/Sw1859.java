import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sw1859 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("./src/1859.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int n = sc.nextInt();
			int[] pay = new int[n];
			for(int i=0; i<n; ++i) {
				pay[i] = sc.nextInt();
			}
			long sum = 0;
			int max = 0;
			
			for(int i=n-1; i>=0; --i) {
				if(max<pay[i]) {
					max = pay[i];
				}
				else {
					int mg = max - pay[i];
					sum+=mg;
				}
			}
			System.out.printf("#%d %d%n",test_case,sum);
		}
	}

}
