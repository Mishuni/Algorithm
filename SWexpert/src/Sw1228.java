import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Sw1228 {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		System.setIn(new FileInputStream("./src/1228.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=10;
				
		for(int test_case = 1; test_case <= T; test_case++) {
			
			
			int N  = sc.nextInt();
			
			LinkedList<String> ten = new LinkedList();
			for(int i=0; i<N; ++i) {
				ten.add(sc.next());
			}
			
			int cm = sc.nextInt();
			
			for(int c=0; c<cm ; ++c) {
				
				sc.next();
				int start = sc.nextInt();
				int iter = sc.nextInt();
				
				for(int r=0; r<iter; ++r) {
					if(start<10) {
						ten.add(start, sc.next());
						ten.pollLast();
						start++;
					}
					else {
						sc.next();
					}
				}
					
			}
			System.out.printf("#%d ",test_case);
			for(int i=0; i<10; ++i) {
				System.out.printf("%s ",ten.get(i));
			}
			System.out.println();
			
			
		}
		
		
	}

}
