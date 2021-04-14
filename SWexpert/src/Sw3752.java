import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Sw3752 {
	public static HashSet<Integer> result;
	public static int[] scores;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./src/3752.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			// 1. initialize
			int size = sc.nextInt();
			result = new HashSet<>();
			result.add(0);
			
			// 2. DP
			for(int i=0; i<size; ++i){
				int tmp = sc.nextInt();
				HashSet<Integer> copy = (HashSet<Integer>) result.clone();
				Iterator<Integer> pre = copy.iterator();
				while(pre.hasNext()) {
					result.add(pre.next()+tmp);
				}
			}
			
			// 3.Print result
			System.out.printf("#%d %d%n",test_case,result.size());
			
		}
	}
	
	
}
