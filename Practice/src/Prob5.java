import java.util.Arrays;
import java.util.LinkedList;

// 9:23 start
public class Prob5 {

	public static void main(String[] args) {
		int n = 5 ; 
		// x,y,a(기둥,보),b(삭제,설치)
		int[][] build_frame = {
				{0,0,0,1},
				{2,0,0,1},
				{4,0,0,1},
				{0,1,1,1},
				{1,1,1,1},
				{2,1,1,1},
				{3,1,1,1},
				{2,0,0,0},
				{1,1,1,0},
				{2,2,0,1}
		};
		int[][] answer;
		
		int[][] frame = new int[n][n];
		// 1: 기둥, 2: 기둥 + 보, 3: 보

		for(int[] build : build_frame) {
			
			int x = build[1];
			int y = build[0];
			int kind = build[2];
			int action = build[3];
			
			
			
			// 기둥인 경우 시작
			if(kind == 0) {
				int current = frame[x][y];
				// 기둥 삭제인 경우
				if(action == 0) {
					// 기둥만 있는 경우
					if(current==1) {
						// 왼쪽의 보가 없어야하고, 위에 기둥이 없어야함
						if(
							(y==0 || (y>0 && !(frame[x][y-1] >= 2)))
											&&
							((x>=n-2)||(x<n-2 && (frame[x+1][y]==0 ||frame[x+1][y]==3 )))) 
						{
							// 기둥 삭제
							frame[x][y]=(current==2)?3:0;
							continue;
						}
					}
					
					// 기둥과 보가 같이 있는 경우
					else if(current==2) {
						// 같이 있는 보의 오른쪽에 보가 있거나 기둥이 있고 왼쪽에 보가 있어야함
						if(
							((y!=0) && (frame[x][y-1])>=2) 
							&& 
							(frame[x][y+1]>0)) 
						{
							// 기둥 삭제
							frame[x][y]=(current==2)?3:0;
							continue;
						}
					}
					continue;
				}
				
				// 기둥 설치인 경우 && 현재 기둥이 없는 경우
				if(action==1 && (current ==0 || current == 3) ) {
					
					// 바닥이면 무조건 설치
					if(x==0) {
						
						frame[x][y]=(current==3)?2:1;
						//System.out.println(frame[x][y]+":"+x+","+y);
						continue;
					}
					// 바닥 아닌데, 밑에 기둥이 있는 경우 설치
					else if(frame[x-1][y]==1 || frame[x-1][y]==2) {
						frame[x][y]=(current==3)?2:1;
						continue;
					}
					// 왼쪽 아래에 보가 있으면 설치
					else if(y>0 && frame[x-1][y-1] >=2) {
						frame[x][y]=(current==3)?2:1;
						continue;
					}
					continue;
				}
			}
			// 기둥인 경우 끝
			
			// 1: 기둥, 2: 기둥 + 보, 3: 보
			// 보인 경우
			if(kind == 1) {
				int current = frame[--x][y];
				// 보 삭제인 경우
				if(action == 0 && current >=2) {
					
					// 기둥과 같이 있던 경우 && 오른쪽에 기둥이 있었던 경우만 가능
					// 보가 있다면, 오른쪽이 아무 것도 없을 경우(0)는 없음
					if(current == 2 && frame[x][y+1]<=2) {
						// 보 삭제
						// 기둥 삭제
						frame[x][y]=(current==2)?1:0;
						continue;
					}
					
					// 보만 있는 경우
					else if(current == 3) {
						// 오른쪽이 벽면 이거나 보와 기둥이 같이 있고
						// 왼쪽이 벽면 이거나 보가 없고 위에 기둥이 없는 경우 가능
						if(((y==n-2)||(frame[x][y+1]==2))
								&&
								((y==1)||(frame[x+1][y]==1 || frame[x+1][y]==2))) {
							frame[x][y]=(current==2)?1:0;
							continue;
						}
					}
					continue;
				}
				
				
				// 보 설치인 경우
				else if(action == 1 && current<2) {
					// 왼쪽에 보, 혹은 기둥 있고
					// 오른쪽에 보, 혹은 기둥 있는 경우만 가능
					System.out.println(frame[x][y]+":"+x+","+y);
						if
						(((current==1) || (y>0 && frame[x][y-1]>=1))
								&&
						 (frame[x][y+1]>=1)) 
						{
							// 보 설치
							frame[x][y]=(current==1)?2:3;
							continue;
						}
					}
				
			}
			// 보인 경우 끝
			
		} // 설치 끝
		
		for(int i=frame.length-1; i>=0; --i) {
			System.out.println(Arrays.toString(frame[i]));
		}
		
		LinkedList<int[]> list = new LinkedList<int[]>();
		
		for(int r=0; r<n; ++r) {
			for(int c=0; c<n; ++c) {
				if(frame[r][c]!=0) {
					//System.out.println(frame[r][c]+":"+r+","+c);
					int[] tmp = new int[3];
					tmp[0]=c;
					tmp[1]=r;
					if(frame[r][c]==2) {
						tmp[2]=0;
						list.add(tmp.clone());
						tmp[2]=1;
						list.add(tmp.clone());
					}
					else {
						tmp[2]=(frame[r][c]==1)? 0 : 1;
						list.add(tmp.clone());
					}
				}
			}
		}
		//System.out.println(list.size());
		answer = new int[list.size()][3];
		for(int i=0; i<answer.length; ++i) {
			answer[i]=list.get(i);
			System.out.println(Arrays.toString(answer[i]));
		}
		list.clear();
		
		
	}

}
