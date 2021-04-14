import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sw2382 {
	
	public static final int[][] DIRECTION = {{0,-1},{0,1},{-1,0},{1,0}} ;// 상하좌우
	
	public static class Mgroup {
		int x, y;
		int direc;
		int count;
		
		public Mgroup(int y, int x, int count, int direc) {
			this.y = y;
			this.x = x;
			this.count = count;
			this.direc = direc - 1;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("./src/2382.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
		
			int N = sc.nextInt(); // size
			int M = sc.nextInt(); // time
			int K = sc.nextInt(); // group
			
			Mgroup[] mgroups = new Mgroup[K];
			
			
			for(int i=0; i<K; ++i) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				mgroups[i] = new Mgroup(y, x, sc.nextInt(), sc.nextInt());
				
			}
			
			for(int m=0; m<M; ++m) {
				for(int i=0; i<K; ++i) {
					Mgroup mg = mgroups[i];
					if(mg.count==0) continue;
					int[] d = DIRECTION[mg.direc];
					
					
					mg.x += d[1];
					mg.y += d[0];
					
					
				}
			}
			int sum = 0;
			for(int i=0; i<N; ++i) {
				sum += mgroups[i].count;
			}
			System.out.printf("#%d %d%n",test_case,sum);
		}
		
	}

}
