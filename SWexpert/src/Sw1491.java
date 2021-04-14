import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sw1491 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("1491.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; ++t) {
			long N = sc.nextLong();
			long A = sc.nextLong();
			long B = sc.nextLong();
			long min = Long.MAX_VALUE;
			
			if ((long) Math.sqrt(N) == Math.sqrt(N)) {
				min = 0;
			} else {
				for (long i = 1; i <= (long)Math.sqrt(N); ++i) {
					for (long j = i; (long)j*i <= N; ++j) {
						// A : 정사각형의 정도를 반영 (정사각형과 멀수록 비중 커짐)
						// B : N개에서 몇개가 남는지를 반영 (많이 남을 수록 비중 커짐)
						long tmp = A * (long) (j - i) + B * (long)(N - (long)i*j);
						if (tmp < min)
							min = tmp;
					}
				}
			}
			System.out.printf("#%d %d\n", t, min);
		}
		sc.close();
	}

}
