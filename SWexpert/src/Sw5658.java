import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sw5658 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("5658.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		StringBuilder line;
		int N, K, size;
		LinkedList<Integer> numberSet = new LinkedList<Integer>();
		LinkedList<Integer> decimal = new LinkedList<Integer>();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			size = N / 4;
			line = new StringBuilder(sc.next());

			for (int i = 0; i < size; ++i) {
				StringBuilder temp;
				for (int j = 0; j < 4; ++j) {
					if (size * (j + 1) + i <= N) {

						numberSet.add(Integer.parseInt(line.substring(j * size + i, size * (j + 1) + i),16));

					} else {
						temp = new StringBuilder(line.substring(j * size + i));
						int plus = size - temp.length();
						for (int t = 0; t < plus; ++t) {
							temp.append(line.charAt(t));
						}
						numberSet.add(Integer.parseInt(temp.toString(),16));
					}
				}
			}
			
			Collections.sort(numberSet,Comparator.reverseOrder());
			int pre = numberSet.get(0);
			for(int i=1; i<K; ++i) {
				int curr = numberSet.get(i);
				if(pre==curr) {
					numberSet.remove(i);
					--i;
				}else {
				pre = curr;}
			}
			System.out.printf("#%d %d\n",test_case,numberSet.get(K-1));
			numberSet.removeAll(numberSet);
			decimal.removeAll(decimal);
		}
		sc.close(); sc = null;
	}

}
