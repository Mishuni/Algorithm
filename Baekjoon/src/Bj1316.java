import java.util.HashMap;
import java.util.Scanner;

public class Bj1316 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder s;
		HashMap<Character, Boolean> m = new HashMap<Character, Boolean>();
		int result = 0;
		boolean check = true;
		int n = sc.nextInt(); sc.nextLine();
		while (n != 0) {
			s = new StringBuilder(sc.nextLine());
			check = true;
			// 1글자면 통과
			if (s.length() <= 1) {
				result++;
			}
			// 문자열 한글자씩 탐색
			else {
				for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if (!m.containsKey(c)) {
						if (i > 0)
							m.replace(s.charAt(i - 1), false);
						m.put(c, true);
					}
					else if (!m.get(c)) {
						check = false;
						break;
					}
				}
				m.clear();
				if (check)
					result++;
			}
			
			n--;
		}
		System.out.println(result);

	}

}
