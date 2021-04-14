import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Bj1068 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Map<Integer, ArrayList<Integer>> tree = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> tmp;
		// tree 구조 : (부모노드,[자식노드1, 자식노드2, ...])
		
		// 1. 입력 받기
		for (int i = 0; i < N; i++) {
			int parent = sc.nextInt();
			if (parent == -1) {
				// 1-1. 루트 노드인 경우 부모노드 입력하지 않기
				continue;
			} else {
				try {
					// 1-2-1. 이미 부모노드가 존재하는 경우 자식 추가
					tree.get(parent).add(i);
				} catch (Exception e) {
					// 1-2-2. 입력받은 값의 자식 노드가 하나도 없었을 경우, 부모노드 만들고 자식 추가
					tmp = new ArrayList<Integer>();
					tmp.add(i);
					tree.put(parent, tmp);
				}
			}
		}
		
		int start = sc.nextInt(); // 제거 시작할 노드 번호
		sc.close();
		sc = null;
		
		// 2. 노드 제거 (BFS, 너비 우선 탐색 방식)
		//    시작 지점 부터 자식을 지우고, 자식노드를 큐에 올리고,
		//    큐에 저장된 노드 꺼내서, 그 노드가 자식노드 지니면 지우고
		//    그 자식노드 큐에 다시 올리고를 반복
		Queue<Integer> target = new LinkedList<Integer>();
		target.add(start);
		while (!target.isEmpty()) {
			int node = target.remove();
			try {
				tmp = tree.remove(node);
				for (int j = 0; j < tmp.size(); j++) {
					target.add(tmp.get(j));
				}
			} catch (Exception e) {
				continue;
			}
		}
		
		// 3. 리프 노드 세기
		Set<Integer> key = tree.keySet(); // 자식노드가 있는 부모 노드 목록
		int leaf = 0;
		for (int n : key) {
			tmp = tree.get(n);
			for (int i = 0; i < tmp.size(); i++) {
				if (tmp.get(i) == start) {
					// 3-1. 꺼낸 자식노드가 제거 시작지점인 경우
					if (tmp.size() == 1) {
						// 3-1-1. 제거 시작지점의 부모가 시작지점만 자식으로 가지고 있다면
						// 		     시작지점이 제거되고, 부모가 리프노드가 되므로 리프노드 추가
						leaf++;
					}
				} else {
					// 3-2. 자식노드 값이 제거 시작지점이 아닌 경우
					boolean check = false;
					for (int m : key) {
						if (tmp.get(i) == m) {
							// 3-2-1. 자식 노드가 있는 경우 리프노드가 아니니까 pass
							check = true;
							break;
						}
					}
					if (!check) {
						// 3-3. check 가 false라는 것은 자식 노드가 없음을 뜻하므로 리프노드 추가
						leaf++;
					}
				}
			}
		}
		System.out.println(leaf);
	}
}
/*
 * 7
 * 
 * 3 6 6 -1 0 6 3
 * 
 * 4
 * 
 * 정답 : 4
 */

/*
 * 5 -1 0 0 1 1 0 정답 : 0
 */