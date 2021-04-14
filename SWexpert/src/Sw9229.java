import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sw9229 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream(".\\src\\sw9229_input.txt"));
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		int t = 1;
		while (t <= TC) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[] snacks = new int[N];
			int result = -1;
			for (int i = 0; i < N; ++i) {
				snacks[i] = sc.nextInt();
				if (snacks[i] >= M) {
					snacks[i] = 0;
				}
			}
			for (int i = 0; i < N - 1; ++i) {
				if (snacks[i] == 0) {
					continue;
				} else {
					for (int j = i + 1; j < N; j++) {
						if (snacks[j] == 0) {
							continue;
						} else {
							int temp = snacks[i] + snacks[j];
							if (temp == M) {
								result = M;
								break;
							}
							else if(temp>result && temp<M){
								result = temp;
							}
						}
						if(result == M) {
							break;
						}
					}
				}
				if(result == M ) break;
			}
			System.out.printf("#%d %d",t,result);
			System.out.println();
			t++;
		}

		sc.close();
	}

}
