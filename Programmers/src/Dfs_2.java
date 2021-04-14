
import java.util.Arrays;
import java.util.LinkedList;

public class Dfs_2 {

	public static boolean[] primeNumbers;
	public static int[] primeTarget;
	public static String[] numSet;

	public static void main(String[] args) {
		String number = "17";
		// 1. 전체 숫자 중 소수 찾기
		primeNumbers = new boolean[(int) Math.pow(10, number.length())];
		primeNumbers[0] = true;
		primeNumbers[1] = true;
		for (int i = 2; i < primeNumbers.length / 2; ++i) {
			for (int j = 2 * i; j < primeNumbers.length; j += i) {
				if (!primeNumbers[j]) {
					primeNumbers[j] = true;
				}
			}
		}

		// 2. 각 문자 저장하고, dfs(완전탐색) 돌리기
		numSet = new String[number.length()];
		for (int i = 0; i < number.length(); ++i) {
			numSet[i] = number.substring(i, i + 1);
		}

		primeTarget = new int[(int) Math.pow(10, number.length())];
		for (int targetCnt = 1; targetCnt <= number.length(); ++targetCnt) {
			LinkedList<String> candidate = new LinkedList<String>();
			boolean[] check = new boolean[number.length()];
			dfsPrime(number.length(), targetCnt, candidate, check);
		}

		// 4. 결과 계산 및 출력
		long result = Arrays.stream(primeTarget).filter(x -> x == 1).count();
		System.out.println(result);
	}

	// 3. 완전 탐색
	public static void dfsPrime(int length, int targetCnt, 
			LinkedList<String> candidate, boolean[] check) {

		// 3-1. 기저사례 (갯수를 채운 경우)
		if (candidate.size() == targetCnt) {
			StringBuilder target = new StringBuilder("");
			for (String s : candidate) {
				target.append(s);
			}
			int temp = Integer.parseInt(target.toString());
			if (!primeNumbers[temp]) {
				// 3-1-1. 조합한 값이 소수인 경우
				primeTarget[temp] = 1;
				// System.out.println(temp);
			}
			return;
		}

		// 3-2. 조합할 갯수가 못 미치는 경우
		for (int i = 0; i < length; ++i) {
			if (!check[i]) {
				check[i] = true;
				candidate.add(numSet[i]);
				dfsPrime(length, targetCnt, candidate, check);
				check[i] = false;
				candidate.removeLast();
			}
		}

	}
}
