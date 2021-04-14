import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Sw1221 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("./src/1221.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		String[] characters = {"ZRO","ONE","TWO","THR","FOR","FIV","SIX","SVN","EGT","NIN"};
		for(int test_case = 1; test_case <= T; test_case++) {
			
			String case_str = sc.next();
			int num = sc.nextInt();
			int[] cnt = new int[10];
			if(test_case==1)
				System.out.println(case_str);
			else
				System.out.printf("%n%s%n",case_str);
			for(int i=0; i<num; ++i) {
				
				String tmp = sc.next();
				switch(tmp) {
				case "ZRO":
					//cnt[0]+=1;
					System.out.printf("ZRO ");
					break;
				case "ONE":
					cnt[1]+=1;
					break;
				case "TWO":
					cnt[2]+=1;
					break;
				case "THR":
					cnt[3]+=1;
					break;
				case "FOR":
					cnt[4]+=1;
					break;
				case "FIV":
					cnt[5]+=1;
					break;
				case "SIX":
					cnt[6]+=1;
					break;
				case "SVN":
					cnt[7]+=1;
					break;
				case "EGT":
					cnt[8]+=1;
					break;
				case "NIN":
					cnt[9]+=1;
				
				}
				
			}
			for( int i=0; i<10; ++i) {
				for(int j=0; j<cnt[i]; ++j) {
					
					System.out.print(characters[i]+" ");
				}
			}
			
		}
	}

}
