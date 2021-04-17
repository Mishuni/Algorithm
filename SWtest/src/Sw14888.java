import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sw14888 {

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static int[] numbers, operates;
	public static int N, result;
	public static int min, max;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. Init
		N = stoi(st.nextToken());
		result =0 ;
		
		numbers = new int[N];
		operates = new int[4];
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		st= new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; ++i) {
			numbers[i] = stoi(st.nextToken());
		}
		
		st= new StringTokenizer(br.readLine());
		Queue<ArrayList<Integer>> queue = new LinkedList();
		
		ArrayList<Integer> start = new ArrayList();
		start.add(numbers[0]);
		for(int i=0; i<4; ++i) {
			operates[i] = stoi(st.nextToken());
			start.add(operates[i]);
		}
		queue.add(start);
		
		// value, +, -, *, /
		// 2. bfs
		int cnt = 1;
		int leaf = 0;
		int level = 0;
		while(!queue.isEmpty()) {
			
			ArrayList<Integer> info = queue.remove();
			--cnt;
			int pre = info.get(0);
			int opnd = numbers[level+1];
			
			for(int i=1; i<=4; ++i) {
				// 2-1. i번째 연산 갯수가 남아있는 경우
				if(info.get(i)>0) {
					// 2-1-1. 마지막 연산자 고르는 경우
					if(level==N-2) {
						// 2-1-1-1. 연산후에 최대,최솟값 찾기
						int res = operate(pre,opnd,i);
						if(res<min) min = res;
						if(res>max) max = res;
					}
					else {
						// 2-1-1-2. i번째 연산자로 연산 후, 연산 값, 현재 남은 연산 갯수
						// 저장하고 queue에 올리기
						ArrayList<Integer> tmp = (ArrayList<Integer>) info.clone();
						tmp.set(0, operate(pre,opnd,i));
						tmp.set(i, tmp.get(i)-1);
						queue.add(tmp);
						++leaf;
					}
				}
			}
			// 2-2. 이전 너비(회차) 끝난 경우 다음 단계로 넘어가기
			if(cnt==0) {
				cnt= leaf;
				leaf = 0;
				++level;
			}
		}
		System.out.println(max);
		System.out.println(min);
		
	}
	
	public static int operate(int a, int b, int oper) {
		
		switch(oper) {
		
		case 1:
			return a+b;
		case 2:
			return a-b;
		case 3:
			return a*b;
		case 4:
			return a/b;
		
		}
		return 0;
		
	}

}
