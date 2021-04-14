import java.util.Scanner;
public class Bj2675 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder S = new StringBuilder();
		
		for(int i=0; i<T; ++i) {
			S = new StringBuilder();
			int R = sc.nextInt();
			String[] tmp = sc.next().split("");
			for(int j=0; j<tmp.length; ++j) {
				for(int r=0; r<R; ++r) {
					S.append(tmp[j]);
				}
			}
			System.out.println(S.toString());
		}
	}

}
