import java.util.Arrays;
import java.util.LinkedList;

public class Dfs_Bfs_4 {
	public static String[] answer;

	public static void main(String[] args) {
		String[][] tickets
//		= {		{"ICN", "JFK"}, 
//				{"HND", "IAD"}, 
//				{"JFK", "HND"}		};
				= { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" }, { "ATL", "SFO" } };
		int target = tickets.length;
		boolean[] checked = new boolean[target];
		answer = new String[target + 1];

		// 인천에서 다음 갈 후보들 중 알파벳 높은 거 추가
		// checked 체크
		answer[0] = "ICN";
		dfs(tickets, checked, "ICN", new LinkedList<String>());
		System.out.println(Arrays.toString(answer));

	}

	public static void dfs(String[][] tickets, boolean[] checked, String start, LinkedList<String> list) {

		if (list.size() == tickets.length) {
			//System.out.println(list);
			// 알파벳 비교로 최종 경로 추출
			if (answer[1] != null) {
				for (int i = 1; i < answer.length; ++i) {

					if (!answer[i].equals(list.get(i - 1))) {
						// 같은 도시명이 아닐 때, 순서 체크
						if (answer[i].compareTo(list.get(i - 1)) > 0) {
							// list 알파벳이 좀 더 앞서 갈 때
							break;
						} else {
							return;
						}
					}

				}
			}
			// 최종 저장 경로 바꾸기
			for (int i = 1; i < answer.length; ++i) {
				answer[i] = list.get(i - 1);
			}
			return;
		}

		for (int i = 0; i < tickets.length; ++i) {

			if (!checked[i] && tickets[i][0].equals(start)) { 
				// 출발지 같고, 한번도 안 쓴 티켓
				checked[i] = true;
				list.add(tickets[i][1]);
				dfs(tickets, checked, tickets[i][1], list);
				checked[i] = false;
				list.removeLast();
			}

		}

	}

}
