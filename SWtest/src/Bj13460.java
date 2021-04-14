import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj13460 {
	
	public static int N, M;
	public static char[][] board;
	public static boolean[][][][] visited; // red & blue
	public static int min_cnt;
	public static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int[] goal;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 1. Initialize
		N = sc.nextInt();
		M = sc.nextInt();
		
		board = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		goal = new int[2];
		min_cnt = Integer.MAX_VALUE;
		
		// redQ 구조 : y 좌표, x 좌표, 움직인 방향, 현재까지의 방향 회전 수
		// blueQ 구조 : y 좌표, x 좌
		Queue<ArrayList<Integer>> redQ = new LinkedList();
		Queue<ArrayList<Integer>> blueQ = new LinkedList();
		
		int[] first = new int[4];
		
		// 1-1. 처음 red, blue, goal 좌표 저장 및 board 입력
		// red 는 처음 시작을 나타내기 위해 flow 는 쓰레기값으로 -1을 cnt 는 0을 저장 
		for(int i=0; i<N; ++i) {
			String tmp = sc.next();
			board[i] = tmp.toCharArray();
			for(int j=0; j<M; ++j) {
				// 1-1-1. Red 위치가 나타난 경우
				if(board[i][j]=='R') {
					ArrayList<Integer> arr = new ArrayList();
					arr.add(i); first[0] = i;
					arr.add(j); first[1] = j;
					arr.add(-1); // flow dummy
					arr.add(0); // cnt
					redQ.add(arr);
				}
				// 1-1-2. Blue 위치가 나타난 경우
				else if(board[i][j]=='B') {
					ArrayList<Integer> arr = new ArrayList();
					arr.add(i); first[2] = i;
					arr.add(j); first[3] = j;
					blueQ.add(arr);
				}
				// 1-1-3. 목표 구멍 위치가 나타난 경우
				else if(board[i][j]=='O') {
					goal[0] = i;
					goal[1] = j;
				}
			}
		}
		
		// 1-2. 처음 시작하는 좌표 방문 이력 업데이트
		visited[first[0]][first[1]][first[2]][first[3]] = true;
		
		// 2. BFS 시작
		loop: while(!redQ.isEmpty() && !blueQ.isEmpty()) {
			ArrayList<Integer> red = redQ.poll();
			ArrayList<Integer> blue = blueQ.poll();
			
			// 2-1. 각 방향마다 체크
			for(int i=0; i<4; ++i) {
				int[] direc = DIRECTIONS[i];
				
				// 2-1-1. red에 저장된 이전 방향 체크
				// 이전 방향이 현재 방향과 다르면 방향 바뀐 횟수 1 추가
				int pre_flow = red.get(2);
				int cnt = (pre_flow!=i)? red.get(3)+1 : red.get(3);

				if(cnt > 10) {
					continue;
				}
				
				// 2-1-2. Blue 좌표 움직이기
				int bny = blue.get(0); // blue next y 
				int bnx = blue.get(1); // blue next x
				char bn = board[bny][bnx];
				
				// 2-1-2-1. #가 나올 때까지 미끄러져 가고
				// 중간에 구멍이 있으면 해당 방향은 그냥 넘어가기
				boolean bo = false;
				while(bn!='#') {
					bny += direc[0]; // red next y
					bnx += direc[1]; // red next x
					bn = board[bny][bnx];
					if(bn=='O') {
						bo = true;
						break;
					}
				}
				// B가 O에 도달했으면 pass
				if(bo) {
					continue;
				}
				
				// 2-1-3. Red 좌표 움직이기
				int rny = red.get(0); // red next y
				int rnx = red.get(1); // red next x
				char rn = board[rny][rnx];
				
				// 2-1-3-1. #가 나올 때까지 미끄러져 가기
				while(rn!='#') {
					rny += direc[0]; 
					rnx += direc[1]; 
					rn = board[rny][rnx];
					// 2-1-3-2. A가 O에 도착하면 최초로 나온 cnt가 최솟값임으로 
					// 결과 저장 후 BFS 끝내기
					if(rn=='O') {
						min_cnt = cnt;
						break loop;
					}
				}
				
				// 2-1-4. 벽에 부딪힌 상태니까 뒤로 돌려보내기
				rny -= direc[0];
				rnx -= direc[1];
				bny -= direc[0];
				bnx -= direc[1];
				
				// 2-1-5. 충돌하는 경우
				if(rny==bny&&rnx==bnx) {
					// 2-1-5-1. 현재 방향 흐름에 맞춰 해당 되는 공 하나만 원래로 옮기기
					switch(i) {
					case 0:
						if(red.get(0)<blue.get(0)) ++bny; else ++rny; break;
					case 1:
						if(red.get(0)>blue.get(0)) --bny; else --rny; break;
					case 2:
						if(red.get(1)>blue.get(1)) ++rnx; else ++bnx; break;
					case 3:
						if(red.get(1)<blue.get(1)) --rnx; else --bnx; break;
					}
				}
				
				// 2-1-6. 방문 이력이 없을 때만 BFS에 추가
				if(!visited[rny][rnx][bny][bnx]) {
					visited[rny][rnx][bny][bnx] = true;
					// red add
					ArrayList<Integer> red_tmp = new ArrayList();
					red_tmp.add(rny);
					red_tmp.add(rnx);
					red_tmp.add(i);
					red_tmp.add(cnt);
					redQ.add(red_tmp);
					// blue add
					ArrayList<Integer> blue_tmp = new ArrayList();
					blue_tmp.add(bny);
					blue_tmp.add(bnx);
					blueQ.add(blue_tmp);
					
				}
			}
			
		}
		// 3. 결과 출력
		min_cnt = (min_cnt==Integer.MAX_VALUE)? -1 : min_cnt;
		System.out.println(min_cnt);
		
	}
	
}
