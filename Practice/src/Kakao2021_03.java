import java.util.Arrays;

public class Kakao2021_03 {
	public static void main(String[] args) {

		String[][] places = {
				{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, 
				{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, 
				{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, 
				{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, 
				{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
		};
		System.out.println(Arrays.toString(solution(places)));
		
	}
	
	// 1. 방향 설정
	public static final int[][] DIRECTIONS = {
			{1,0}, // 아래 방향
			{0,1},  // 오른쪽 방향
	};
	
	// 위 DIRECTIONS 으로 커버가 되지 않는 방향 (아래->왼쪽, 왼쪽->아래)
	public static final int[][] SPECIAL_DIREC = {
			{1,0}, // 아래 방향
			{0,-1} // 왼쪽 방향
	};
	
	public static int[] solution(String[][] places) {
		int[] answer = new int[places.length];
		int index = 0;
		
		// 2. 각 대기실 마다 테스트 진행
		for(String[] place : places) {
			boolean check = true;
			Loop: 
			// 2-1. 왼쪽에서 오른쪽으로, 위에서 아래로 기준점 이동
			for(int x=0; x<place[0].length(); ++x) {
				for(int y=0; y<place.length; ++y) {
					char current = place[y].charAt(x);
					// 2-1-1. 기준점이 P인 경우 거리두기 테스트 시작
					if(current=='P') {
						int[] pos = new int[] {y,x};
						// 2-1-1-1. 거리두기 테스트가 false가 나온경우
						// 무조건 거리두기 실패임으로 테스트 종료
						if(!dfs(pos,place)) {
							answer[index++]=0;
							check = false;
							break Loop;
						}
					}
				}
			}
			// 2-2. 기준점을 다 옮겨봤는데 false가 한번도 안나온 경우 거리두기 성공
			if(check) answer[index++]=1;
		}
		return answer;
	}
	
	// 3. 거리두기 성공 여부 판단 함수
	// P-P 가 연달아 나온 경우 혹은 P-O-P 가 나온 경우 false 리턴
	public static boolean dfs( int[] pos, String[] place) {
		
		// 3-1. 기준점에서 아래, 오른쪽 방향 자유롭게 이동하여 테스트
		for(int[] direc : DIRECTIONS) {
			int[] next_pos = new int[] {pos[0]+direc[0],pos[1]+direc[1]};
			if(!checkNext(next_pos,place.length)) continue;
			// 3-1-1. 기준점 바로 옆칸이 P인 경우 실패로 간주
			if(!move(next_pos,place)) return false;
			char next = place[next_pos[0]].charAt(next_pos[1]);
			// 3-1-2. 기준점 옆칸이 O인 경우 그 다음 칸이 P이면 실패로 간주
			if(next=='O') {
				for(int[] direc2 : DIRECTIONS) {
					int[] next_pos2 = new int[]{next_pos[0]+direc2[0],next_pos[1]+direc2[1]};
					if(!move(next_pos2,place)) return false;
				}
			}
		}
		
		// 3-2. 위 for 문으로 커버가 되지 않는 방향 테스트
		for(int in =0; in<2; ++in) {
			int[] direc = SPECIAL_DIREC[in];
			int[] next_pos = new int[] {pos[0]+direc[0],pos[1]+direc[1]};
			if(!checkNext(next_pos,place.length)) continue;
			if(!move(next_pos,place)) return false;
			char next = place[next_pos[0]].charAt(next_pos[1]);
			
			if(next=='O') {
				int[] direc2 = (in==0)? SPECIAL_DIREC[1] : SPECIAL_DIREC[0];
				int[] next_pos2 = new int[]{next_pos[0]+direc2[0],next_pos[1]+direc2[1]};
				if(!(move(next_pos2,place))) return false;
			}
		}
		
		return true;
	}
	// 4. 옆칸으로 이동한 위치가 P이면 false 리턴
	public static boolean move(int[] pos, String[] place) {
		if(!checkNext(pos,place.length)) return true;
		char next2 = place[pos[0]].charAt(pos[1]);
		if(next2=='P') {
			return false;
		}
		return true;
	}
	// 5. 해당 좌표로 이동 가능한지 판단
	public static boolean checkNext(int[] pos, int n) {
		if(pos[0]>=n || pos[0]<0) return false;
		if(pos[1]>=n || pos[1]<0) return false;
		return true;
	}

}
