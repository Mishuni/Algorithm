import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class Bj3190 {
	
	public static final int[][] DIRECTIONS = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// Initialize
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] board = new int[N][N];
		
		for(int i=0; i<K; ++i) {
			board[sc.nextInt()-1][sc.nextInt()-1] = 1;
		}
		
		int L = sc.nextInt();
		
		int time = 1;
		int direc = 0;
		
		int step = 0;
		// 처음 위치 추가
		Deque<ArrayList<Integer>> bam = new ArrayDeque();
		ArrayList<Integer> tmp = new ArrayList();
		tmp.add(0);
		tmp.add(0);
		bam.add(tmp);
		board[0][0] = 2;
		
		loop: while(step<L) {
			
			// 다음 시간, 방향 찾기
			int next_time = sc.nextInt();
			String next_direc = sc.next();
			
			int[] flow = DIRECTIONS[direc];
			
			// 다음 시간 전까지 원래의 방향으로 움직이기
			for(;time<=next_time;++time) {
				
				if(!move(board,bam,flow,N)) {
					break loop;
				}
				
			}
			
			// next
			++step;
			direc = (next_direc.equals("D"))? ((direc==3)? 0 : direc +1) : ((direc==0)? 3: direc-1);
			
			if(step==L) {
				
				flow = DIRECTIONS[direc];
				while(true) {
					if(!move(board,bam,flow,N)) {
						break;
					}
					++time;
				}
			}
			
		}
		
		
		System.out.println(time);

	}
	
	// 뱀이 움직이는 함수
	public static boolean move(int[][] board, Deque<ArrayList<Integer>> bam, int[] flow, int N) {
		
		ArrayList<Integer> head = bam.peek();
		int nexty = head.get(0)+flow[0];
		int nextx = head.get(1)+flow[1];
		
		// 다음 위치가 보드 범위 안에 있으면
		if(nexty>=0 && nexty<N && nextx>=0 && nextx<N) {
			// 자기 자신을 부딪힌 경우
			if(board[nexty][nextx]==2) {
				return false;
			}
			
			// 머리 이동 완료
			ArrayList<Integer> next = new ArrayList();
			next.add(nexty);
			next.add(nextx);
			bam.addFirst(next);
			
			
			// 다음 장소에 사과가 있는 경우 먹기
			if(board[nexty][nextx]==1) {
				board[nexty][nextx]=2;
			}
			// 다음 장소에 아무 것도 없는 경우 꼬리 옮기기
			else {
				ArrayList<Integer> tail = bam.removeLast();
				board[tail.get(0)][tail.get(1)] = 0;
				board[nexty][nextx]=2;
			}
			
		}
		// 보드 범위 안에 없으면 벽에 부딪힌 것이므로 이동 실패
		else {
			return false;
		}
		
		return true;
	}

}
