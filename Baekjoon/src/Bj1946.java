import java.util.Scanner;

public class Bj1946 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] place ;
		
		while(T!=0) {
			
			int N = sc.nextInt();
			int cnt = N;
			place = new int[N+1];
			for(int i=0; i<N; i++) {
				int index = sc.nextInt();
				place[index]=sc.nextInt();
			}
			for(int i=N; i>1; i--) {
				int a = place[i];
				for(int j=i-1; j>0; j--){
					if(a>place[j]) {
						cnt--;
						break;
					}
				}
			}
			System.out.println(cnt);
			T--;
		}
	}

}
