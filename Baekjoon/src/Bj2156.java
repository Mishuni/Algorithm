import java.util.LinkedList;
import java.util.Scanner;

public class Bj2156 {

	public static void main(String[] args) {
		// 1. 포도주 값 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] wine = new int[N];
		LinkedList<Amount> dp = new LinkedList<Amount>();
		for (int i = 0; i < N; i++) {
			wine[i] = sc.nextInt();
		}
		
		// 2. 동적계획법으로 최대 값 찾기
		dp.add(new Amount());
		dp.add(new Amount(0, wine[0], wine[0]));
		for(int i = 1; i<N ; i++) {
			// 2-1. 3개의 case별로 값을 저장
			int a = dp.get(1).getMax();
			int b = dp.get(0).getMax() + wine[i];
			int c = dp.get(1).drink_jump + wine[i];
			dp.add(new Amount(a,b,c));
			// 2-2. 바로 전전경우의 값까지만 필요하므로, 첫번째 요소 지우기
			dp.remove();
		}
		System.out.println(dp.get(1).getMax());
	}

}

class Amount {
	int drink_not;   // 현재 포도주 안 먹고 생략했을 때
	int drink_jump;  // 전전포도주 먹은 상태에서 현재 포도주 마셨을 때
	int drink_seq;   // 바로 전 포도주 연속적으로 먹을 때

	Amount() {
		this.drink_not = 0;
		this.drink_jump = 0;
		this.drink_seq = 0;
	}

	Amount(int a, int b, int c) {
		this.drink_not = a;
		this.drink_jump = b;
		this.drink_seq = c;
	}

	public int getMax() {
		// 세 값 중 max 값을 반환
		return (drink_not > drink_jump) ? 
				(drink_seq > drink_not) ? drink_seq : drink_not
				: (drink_seq > drink_jump) ? drink_seq : drink_jump;
	}
	
}
