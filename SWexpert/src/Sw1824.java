import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sw1824 {
	
	// 이동 방향 : 우,좌,상,하 
	public static final int[][] directions = {{0,1},{0,-1},{-1,0},{1,0}};
	// 당시의 상태를 저장하는 클래스
	public static class Status{
		int row;
		int col;
		int mem;
		int direc;
		
		Status(int r, int c, int m, int dr){
			this.row = r;
			this.col = c;
			this.mem = m;
			this.direc = dr;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("./src/1824.txt"));
		
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			
			// 1. 변수 초기화
			boolean pause = false;
			int row = sc.nextInt();
			int col = sc.nextInt();
			char[][] command = new char[row][col];
			// row, col, mem, direc
			boolean[][][][] visited = new boolean[row][col][16][4];
			
			for(int i=0; i<row; ++i) {
				command[i] = sc.next().toCharArray();
			}
			
			Queue<Status> queue = new LinkedList<>();
			
			// 1-1. 시작상태 넣어주기
			queue.add(new Status(0,0,0,0));
			
			// 2. Loop 시작
			loop : while(!queue.isEmpty()) {
				
				Status current = queue.poll();
				
				// 2-1. 현재 상태 방문 여부 체크
				if(visited[current.row][current.col][current.mem][current.direc]) {
					// 2-1-1. 해당 상태 이전에 방문 했었으면, 어차피 앞에서 검사했다는 거니까 넘어가기
					continue;
				}
				// 2-1-2. 이전 방문 경험 없으면, 이번에 방문한 거 체크해주기
				visited[current.row][current.col][current.mem][current.direc]=true;
				
				
				// 2-2.  command 에 다른 명령 동작 수행
				switch(command[current.row][current.col]) {
				
				// 2-2-1. @ 인 경우 정지 가능하다고 보고 loop 중지
				case '@':
					pause = true;
					break loop;
				// 2-2-2. 보통의 명령어 실행
				case '>':
					current.direc=0;
					break;
				case '<':
					current.direc=1;
					break;
				case '^':
					current.direc=2;
					break;
				case 'v':
					current.direc=3;
					break;
				case '_':
					current.direc=(current.mem==0)? 0:1;
					break;
				case '|':
					current.direc=(current.mem==0)? 3:2;
					break;
				case '.':
					break;
				case '+':
					current.mem = (current.mem==15)? 0:current.mem+1;
					break;
				case '-':
					current.mem = (current.mem==0)? 15:current.mem-1;
					break;
				// 2-2-3. ?인 경우 랜덤 방향이므로 4가지 방향 모두 살펴보기
				case '?':
					for(int d=0; d<4; ++d) {
						int[] direction = directions[d];
						int next_row = current.row + direction[0];
						int next_col = current.col + direction[1];
						next_row = (next_row==row)? 0: (next_row==-1)? row-1: next_row;
						next_col = (next_col==col)? 0: (next_col==-1)? col-1: next_col;
						queue.add(new Status(next_row,next_col,current.mem, d));
					}
					break;
				// 2-2-4. 0~9 인 경우
				default:
					current.mem = command[current.row][current.col]-'0';
				}
				
				
				// 2-3. 다음 동작으로 이동
				int[] direction = directions[current.direc];
				int next_row = current.row + direction[0];
				int next_col = current.col + direction[1];
				next_row = (next_row==row)? 0: (next_row==-1)? row-1: next_row;
				next_col = (next_col==col)? 0: (next_col==-1)? col-1: next_col;
				queue.add(new Status(next_row,next_col,current.mem,current.direc));
				
			}
			
			
			// 3. print
			System.out.printf("#%d %s%n",test_case,(pause? "YES":"NO"));
			
		}

	}
	
}
