
import java.util.ArrayList;
import java.util.Arrays;

public class Stack_Queue_4 {

	public static void main(String[] args) {
		ArrayList<Node> test = new ArrayList<Node>();
		int[] priorities = { 1, 2, 3, 1, 5 };
		int location = 1;
		int N = priorities.length;
		test.add(new Node(priorities[0], 0));
		for (int i = 1; i < N; ++i) {
			test.add(new Node(priorities[i], i));
		}
		// Collections.sort(test);
		Arrays.sort(priorities);

		int i = N - 1;
		int j = 0;
		int order = 1;
		while (i >= 0) {
			if (j >= test.size()) {
				j = 0;
			}
			Node tmp = test.get(j++);
			if (tmp.value >= priorities[i]) {

				test.remove(tmp);

				if (tmp.idx == (location)) {

					break;
				}

				++order;
				--j;
				--i;
			}
		}
		System.out.println(order);
	}
}

class Node {

	int value;
	int idx;

	Node(int v, int i) {
		value = v;
		idx = i;
	}

}
