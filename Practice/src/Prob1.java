
// 11:43 시작 12:34 끝
public class Prob1 {

	public static void main(String[] args) {
		String s = "aabbaccc";
		
		// 1. 변수 설정
		int repeat = 1;
		int min_len = s.length();
		
		// 2. 단위 완전 탐색
		for (int l = 1; l <= s.length() / 2 ; ++l) {
			int turn = 0, len = 0;
			
			// 2-1. 앞 뒤 조각들의 반복 체크 및 결과 길이 측정
			while (turn < s.length() / l - 1) {
				// 2-1-1. 앞,뒤 같으면
				if (s.substring(l * turn, (turn +1) * l).equals(s.substring((turn +1) * l, (turn+ 2) * l))) {
					// 2-1-1-1. 반복 추가
					++repeat;
					// 2-1-1-2. 마지막 체크 였으면, 마지막 문자열 추가
					if (turn == s.length() / l - 2) {
						len += (repeat == 1) ? l : Integer.toString(repeat).length() + l;
						break;
					}
				} 
				// 2-1-2. 앞,뒤 다르면
				else {
					// 2-1-2-1. 그동안 계산한 반복횟수 고려해서 문자열 추가
					len += (repeat == 1) ? l : Integer.toString(repeat).length() + l;
					repeat = 1;
					// 2-1-2-2. 마지막 체크 였으면, 마지막 남은 문자열 추가
					if (turn == s.length() / l - 2) {
						len += l;
					}
				}
				
				// 2-1-3. 지금까지 구한 길이가 최소 길이보다 크면 다음 단위로 넘기기
				if (len > min_len) {
					break;
				}
				++turn;
			}
			
			// 2-2. 추가로 남는 문자열 추가
			if (s.length() % l > 0) {
				len += s.length() % l;
			}
			
			// 2-3. 최소길이 측정
			if (len < min_len) {
				min_len = len;
			}
		}
		
		System.out.println(min_len);

	}

}
