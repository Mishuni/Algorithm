import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bj10816 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Map<Integer, Integer> number = new HashMap<Integer,Integer>();
		
		for(int i=0; i<N; ++i) {
			int tmp = sc.nextInt();
			if(number.containsKey(tmp)) {
				number.put(tmp,number.get(tmp)+1);
			}else {
				number.put(tmp,1);
			}
		}
		int M = sc.nextInt();		
		for(int i=0; i<M; ++i) {
			int tmp = sc.nextInt();
			if(number.containsKey(tmp)) {
				System.out.printf("%d ",number.get(tmp));
			}
			else {
				System.out.printf("%d ",0);
			}
		}
		
	}

}
