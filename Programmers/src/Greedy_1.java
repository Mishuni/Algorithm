import java.util.Arrays;

public class Greedy_1 {

	public static void main(String[] args) {

		int n = 7;
		int[] lost = { 2, 3, 6, 7 };
		int[] reserve = { 3, 1, 2, 4 };
		// 순서대로 정렬
		Arrays.sort(lost);
		Arrays.sort(reserve);
		int total = n - lost.length;
		boolean[] checked = new boolean[n + 1];

		// 1. 도난 = 여분인 자가 있는지 확인

		for (int i = 0; i < reserve.length; ++i) {
			for (int j = 0; j < lost.length; ++j) {
				if (reserve[i] == lost[j]) {
					checked[reserve[i]] = true;
					++total;
				}
			}
		}

		// 2. 체육복 빌리기
		for (int i = 0; i < lost.length; ++i) {
			if (!checked[lost[i]]) {
				for (int j = 0; j < reserve.length; ++j) {
					if (!checked[reserve[j]]) {
						if (reserve[j] == lost[i] - 1) {
							++total;
							checked[reserve[j]] = true;
							break;
						} else if (reserve[j] == lost[i] + 1) {
							++total;
							checked[reserve[j]] = true;
							break;
						} else if (reserve[j] > lost[i] + 1) {
							break;
						}

					}
				}
			}
		}

		System.out.println(total);
	}

}
