import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sw2806 {
	
	public static int[] arr;
	public static int sum;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./src/2806.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int n = sc.nextInt();
			sum = 0;
			arr = new int[n];
			
			dfs(0,n);
			System.out.printf("#%d %d%n",test_case,sum);
		}

	}
	
	public static void dfs(int start, int n) {
		if(start==n) {
			sum+=1;
			return;
		}
		
		for(int x=0; x<n; ++x) {
			
			boolean check = true;
			for(int y=0; y<start; ++y) {
				if(arr[y]==x || arr[y]+(start-y)==x || arr[y]-(start-y)==x) {
					check = false;
					break;
				}
			}
			if(check) {
				arr[start]=x;
				dfs(start+1,n);
			}
			
		}
	}

}
