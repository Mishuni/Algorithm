import java.util.Scanner;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sw1217 {

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("1217.txt"));
		Scanner sc = new Scanner(System.in);
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{ 	sc.nextInt();
			int n = sc.nextInt();
			int m = sc.nextInt();
			int answer = pow(n,m);
			
			System.out.println("#"+test_case+" "+answer);
			
		}
	}
	
	public static int pow(int n, int m) {
		if(m==0) {
			return 1;
		}
		if(m==1) {
			return n;
		}
		
		return n*pow(n,m-1);
		
	}


}