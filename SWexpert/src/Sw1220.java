import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Sw1220 {

	public static void main(String[] args) throws FileNotFoundException {


		System.setIn(new FileInputStream("./src/1220.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=10;
				
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int N = sc.nextInt();
			int[][] map = new int[N][N];
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					map[i][j] = sc.nextInt();
				}
			}
			int sum = 0;
			for(int i=0; i<N; ++i) {
				int pre = map[0][i];
				int bottle = 0;
				for(int j=1; j<N; ++j) {
					int cur = map[j][i];
					if(cur==0) {
						continue;
					}
					if(pre==1 && cur == 2) {
						++bottle;
					}
					pre = cur;
				}
				sum += bottle;
				
			}
			
			System.out.printf("#%d %d%n",test_case,sum);
		}
		
	}

}
