import java.util.LinkedList;
import java.util.Queue;

public class Dfs_Bfs_3 {

	public static void main(String[] args) {
		String begin = "hit", target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
		int answer = 0;
		// 1. target 이 있나 없나 확인
		for (String word : words) {
			if (word.equals(target)) {
				answer = 1;
				break;
			}
		}
		if (answer != 1) {
			// 1-1. target 이 words 안에 없는 경우
			answer = 0;
			System.out.println(0);
			return;
		}
		
		// 2. target 이 배열 안에 있는 경우
		// begin에서 부터 
		// 한 단어만 다른 단어들 찾아서 Queue 에 입력
		// 그걸로 이제 bfs방식으로 정답 찾아가기
		answer = 0;
		Queue<String> list = new LinkedList<String>();
		list.add(begin);
		int num = 0; // 1 turn의 node 갯수
		int cnt = 0; // 그 turn의 몇개 까지 검사했는지
		int pre = 1;
		
		while(!list.isEmpty()) {
			String tmp = list.remove();
			// 2-1. 꺼낸 값이 목표 문자값인 경우
			// 최초로 목표단어를 찾은 순간이 트리의 최소 깊이 이므로
			// 그 때 저장된 answer 값을 return
			if(tmp.equals(target)) break;
			
			++cnt;
			// 2-2-1. 1글자만 다른 단어 찾기
			for(String s : words) {
				int diff = 0 ;
				for(int i=0; i<begin.length(); ++i) {
					if(tmp.charAt(i)!=s.charAt(i)) {
						++diff;
					}
					if(diff>1) {
						break;
					}
				}
				if(diff==1) {
					// 다른 단어가 1개면 queue에 추가
					list.add(s);
					++num;
				}
			}
			
			// 2-2-2. answer 값 추가를 위한 코드 (너비 탐색 이므로)
			// 전에 num 에 저장해두었던 같은 깊이의 노드의 갯수들 만큼
			// 탐색을 했을 때는, 이제 다음 깊이로 넘어가야 하므로 answer 값을 증가시키고
			// 나머지 조건 값을 초기화 한다.
			if(cnt==pre) {
				pre = num;
				num = 0 ;
				cnt = 0 ;
				++answer;
			}
			
			// 2-2-3. 만약 answer 값이 words.length 보다 큰 경우
			// 모든 단어를 다 돌아봤다는 뜻이므로, 방법이 없음으로 간주
			if(answer>words.length) {
				answer = 0 ;
				break;
			}
			

		}
		System.out.println(answer);

	}

}
