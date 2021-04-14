import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class Sw1289 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("1289.txt"));
		Scanner sc = new  Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String[] tmp = sc.next().split("");
			String pre = tmp[0];
			int cnt = (pre.equals("1"))? 1:0;
			for(String data : tmp) {
				if(!pre.equals(data)) {
					++cnt;
				}
				pre = data;
			}
			System.out.printf("#%d %d\n",test_case,cnt);
		}
		
	}

}
