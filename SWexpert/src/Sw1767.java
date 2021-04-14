import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
public class Sw1767 {
	public static int[][] map;
	public static int N;
	public static int maxCore;
	public static int minLine;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("/Users/mcnl/TIL/Algorithm/SWexpert/src/1767.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			N = sc.nextInt();
			maxCore = 0;
			minLine = 1440; 
			LinkedList<Position> coreList = new LinkedList<>();
			
			// 1. input 받기
			map = new int[N][N];
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					map[i][j]=sc.nextInt();
					// 1-1. 코어가 존재하는 경우
					if(map[i][j]==1 ) {
						if((i==0||j==0||i==N-1||j==N-1)) {
							continue;
						}
						else {
							Position pos = new Position(i,j);
							coreList.add(pos);
						}
					}
				}
			}
			
			// 2.core 위치 목록들을 dfs 돌려서 값 찾기
			dfs(coreList,0,0,0);
			
			System.out.printf("#%d %d\n",test_case, minLine);
			

		}

	}
	
	// 2. DFS 돌리기
	public static void dfs(LinkedList<Position> coreList, int index, int coreCnt, int line) {
		// 2-1. 기저사례
		if(index == coreList.size()) {
			// 2-1-1. 가능 코어 갯수가 max값 보다 큰 경우
			if(coreCnt > maxCore) {
				maxCore = coreCnt;
				minLine = line;
			}
			
			// 2-1-2. 코어 갯수가 같은 경우, line 길이 비교
			else if (coreCnt == maxCore) {
				if(line < minLine) {
					minLine  = line;
				}
			}
			return;
		}
		
		// 2-2. 현재 위치에서 동,서,남,북 라인 체크하고 그리기 
		Position current = coreList.get(index);
		for(int i=0; i<4; ++i) {
			if(isDrawable(current, i)) {
				dfs(coreList, index+1, coreCnt+1, line+drawLine(current,i));

				
				
				
				deleteLine(current,i);
				
				
			}
		}
		// 2-3. 최대 코어 갯수가 총 코어 갯수가 아닌 경우, 코어 라인을 그리지 않는 경우도 판단
		if(maxCore < coreList.size()) {
			dfs(coreList, index+1, coreCnt, line);
		}
		
		
	}
	
	// 3. 해당 위치에서 해당 방향으로 라인을 그릴 수 있는 상태인지 체크하기
	public static boolean isDrawable(Position pos, int dir) {
		
		int x = pos.x;
		int y = pos.y;
		
		switch(dir) {
		
		// 3-1. 위에서 아래 방향
		case 0:
			for (int i =0; i<x; ++i) {
				if(map[i][y]==1) {
					return false;
				}
			}
			return true;
			
		// 3-2. 왼쪽에서 오른쪽 방향
		case 1:
			for(int i=0; i<y; ++i) {
				if(map[x][i]==1) {
					return false;
				}
			}
			return true;
			
		// 3-3. 아래에서 위 방향
		case 2:
			for(int i=x+1; i<N; ++i) {
				if(map[i][y]==1) {
					return false;
				}
			}
			return true;
			
		// 3-4. 오른쪽에서 왼쪽 방향
		case 3:
			for(int i=y+1; i<N; ++i) {
				if(map[x][i]==1) {
					return false;
				}
			}
			return true;
		
		}
		return false;	
	}
	
	// 4. Line 그리기
	public static int drawLine(Position pos, int dir) {
		
		if(dir == 0) {
			for(int j = 0; j<pos.x; ++j) {
				map[j][pos.y] = 1;
			}
			return pos.x;
		}
		if(dir == 1) {
			for(int j = 0; j<pos.y; ++j) {
				map[pos.x][j] = 1;
			}
			return pos.y;
		}
		if(dir == 2) {
			for(int j = pos.x+1; j<N; ++j) {
				map[j][pos.y] = 1;
			}
			return N-1-pos.x;
		}
		if(dir == 3) {
			for(int j = pos.y+1; j<N; ++j) {
				map[pos.x][j] = 1;
			}
			return N-1-pos.y;
		}
		return 0;
	}
	

	//5. Line 지우기
	public static void deleteLine(Position pos, int dir) {
			if(dir == 0) {
				for(int j = 0; j<pos.x; ++j) {
					map[j][pos.y] = 0;
				}
			}
			if(dir == 1) {
				for(int j = 0; j<pos.y; ++j) {
					map[pos.x][j] = 0;
				}
			}
			if(dir == 2) {
				for(int j = pos.x+1; j<N; ++j) {
					map[j][pos.y] = 0;
				}
			}
			if(dir == 3) {
				for(int j = pos.y+1; j<N; ++j) {
					map[pos.x][j] = 0;
				}
			}
			
		}

}


class Position{
	int x,y;
	
	Position(int a, int b){
		this.x = a;
		this.y = b;
	}
	
	public String toString() {
		return "x:"+x+",y:"+y;
	}
}
