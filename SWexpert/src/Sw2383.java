import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Sw2383 {

	// 사람 객체 정의
	public static class People implements Comparable<People>{
		public int x, y, start;
		People(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		// 이동 거리 계산 함수
		public void setPath(Stair s) {
			this.start = (int)Math.abs(this.x-s.x) + (int)Math.abs(this.y-s.y);
		}
		
		// People 정렬을 위한 비교 함수
		@Override
		public int compareTo(People o) {
			return Integer.compare(this.start, o.start);
		}
	}
	
	// 계단 객체 정의
	public static class Stair{
		int x, y, height;
		Stair(int x, int y, int h){
			this.x = x ;
			this.y = y;
			this.height = h;
		}
	}
	
	// 공통 변수 정의
	public static ArrayList<Stair> stairs;
	public static ArrayList<People> people;
	public static int min_result;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("./src/2383.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int N = sc.nextInt();
			
			// 1. Initialize
			people = new ArrayList<>();
			stairs = new ArrayList<>();
			
			// 1-1. 사람은 위치만, 계단은 위치와 계단 높이를 저장하여 각 배열에 추가
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					int tmp = sc.nextInt();
					if(tmp==1) {
						people.add(new People(j,i));
					}
					else if(tmp!=0) {
						stairs.add(new Stair(j,i,tmp));
					}
				}
			}
			
			// 1-2. min_result 는 최소 이동 완료 시간 저장하는 곳
			// stair로 이름진 array는 
			// 각 케이스마다 stair1을 지나는 사람 & stair2를 지나는 사람을 구분지어 저장하는 곳
			min_result = Integer.MAX_VALUE;
			ArrayList<People> stair1 = new ArrayList<>();
			ArrayList<People> stair2 = new ArrayList<>();
			
			// 1-3. bit operation으로 중복 순열로 케이스를 반복하기 위한 초기화
			// 사람이 총 6명일 때, 6bit로 사람이 stair1으로 가는지 안 가는지를 표시해야 하기때문에
			// 총 케이스는 000000 ~ 111111 까지 임으로 총 2^6-1 == 63개를 살펴봐야 함
			int cnt = people.size();
			int permutation = (int)Math.pow(2, cnt);
			
			
			// 2. dfs : bit operation을 사용한 dfs
			for(int p=0; p<permutation; ++p) {
				stair1.clear();
				stair2.clear();
				// 2-1. 현재 케이스에서 각 비트를 검사
				for(int i=cnt-1; i>=0; --i) {
					
					// 2-1-1. p에서 i번째 bit가 1인 경우
					// 1번째 계단으로 이동했다고 간주하고 이동 시간 저장 후 stair1 배열에 추가
					if((p&(1<<i))!=0) {
						
						people.get(i).setPath(stairs.get(0));
						stair1.add(people.get(i));
						
					}
					// 2-1-2. p에서 i번째 bit가 0인 경우
					// 2번째 계단으로 이동했다고 간주하고 이동 시간 저장 후 stair2 배열에 추가
					else {
						
						people.get(i).setPath(stairs.get(1));
						stair2.add(people.get(i));
						
					}
					
				}
				
				// 2-2. sort
				// 위에서 계산한 이동 시간 순서대로 정렬
				Collections.sort(stair1);
				Collections.sort(stair2);
				
				
				// 2-3. calculate
				
				// 2-3-1. 정렬된 이동 시간을 이용해서 각 사람들의 총 이동 시간 계산
				int[] paths1 = calPath(stair1,0);
				int[] paths2 = calPath(stair2,1);
				
				// 2-3-2. 만약 둘 중에 null 이 하나라도 있는 경우
				// 두 stairs 중 총 이동 시간이 지금의 min_result 보다 큰 경우 이므로 pass
				if(paths1==null | paths2==null) {
					continue;
				}
				// 2-3-3. 두 paths중에서 마지막 값(제일 마지막으로 처리가 끝난 시간)이 더 큰 값이 이동 완료 시간
				// 그 이동 완료 시간이 min_result 값 보다 작은게 확실하니 그 값을 min_result 로 배정
				else if(paths1[paths1.length-1]>=paths2[paths2.length-1]) {
					min_result = paths1[paths1.length-1];
				}else {
					min_result = paths2[paths2.length-1];
				}
				
			}
			
			// 3. 결과 출력
			System.out.printf("#%d %d%n",test_case,min_result);
			
		}
	}
	
	// 해당 계단에서 최종 이동 시간 계산
	public static int[] calPath(ArrayList<People> current_stair, int idx) {
		if(current_stair.size()==0) {
			// 한 사람도 안 지나가는 경우 쓰레기 값 리턴
			int[] tmp = {Integer.MIN_VALUE};
			return tmp;
		}
		int[] paths = new int[current_stair.size()];
		for(int s=0; s<current_stair.size(); ++s) {
			// 처음 3개는 대기 시간 필요 없음
			if(s<3) {
				paths[s] = current_stair.get(s).start +1 + stairs.get(idx).height;
			}
			else {
				// 3번째 앞에 사람의 이동 완료 시간이 현재 도착 시간 보다 큰 경우 대기 시간 필요
				if(paths[s-3]>current_stair.get(s).start) {
					// 대기 시간에 준비 시간이 포함됨
					paths[s] = paths[s-3] + stairs.get(idx).height;
				}
				// 3명이 안 채워져 있는 경우 준비 후에 바로 내려가면 됨
				else {
					paths[s] = current_stair.get(s).start +1 + stairs.get(idx).height;
				}
			}
			// 만약 계산한 이동 완료 값이 저장된 최소 이동 완료 시간 보다 크면 더 계산 할 것 없음
			if(paths[s]>min_result) {
				return null;
			}
		}
		return paths;
	}
	
}
