import java.util.Arrays;
import java.util.Scanner;

public class Bj2309 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = 9;
		int[] ch = new int[N];
		int sum = 0;
		for(int i=0; i<N; i++) {
			ch[i] = sc.nextInt();
			sum += ch[i];
		}
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(sum - ch[i]- ch[j] == 100) {
					ch[i] = -1;
					ch[j] = -1;
					break;
				}
			}
			if(ch[i]==-1) {
				break;
			}
		}
		Arrays.sort(ch);
		for(int i=0; i<N; i++) {
			if(ch[i]==-1) {
				continue;
			}
			else {
				System.out.println(ch[i]);
			}
		}
	}

	

}

