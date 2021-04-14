import java.util.Scanner;

public class Bj10816_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		int[] card = new int[20000001];
		for (int i = 0; i < N; ++i) {
			 ++card[sc.nextInt()+10000000] ;
		}
		int M = sc.nextInt();
		
		for (int i = 0; i < M; ++i) {
			sb.append(card[sc.nextInt()+10000000]+" ");
		}		
		System.out.println(sb.toString());
	}

}
