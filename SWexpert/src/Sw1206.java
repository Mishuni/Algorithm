import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sw1206 {

	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("./1206.txt"));

		Scanner sc = new Scanner(System.in);
		int T=10;
				
		for(int test_case = 1; test_case <= T; test_case++)
		{	
			LinkedList<Integer> queue = new LinkedList<>();
			int total = sc.nextInt();
			int sum = 0;
			
			// 0. 전체 갯수가 4보다 작은 경우는 건물이 모두 0인 경우
			if(total<=4) {
				
			}
			// 1. 조망권 탐색 시작
			else {
				
				// 1-1. 처음 5개 먼저 받기
				int i = 0;
				for(; i<5; ++i) {
					queue.add(sc.nextInt());
				}
				
				// 1-2. 처음 5개 부터 끝까지 조망권 탐색하기
				while(i<total+1 && queue.size()==5) {
					int current = queue.get(2); // middle
					int left_one = queue.get(0);
					int left_two = queue.get(1);
					int one = queue.get(3);
					int two = queue.get(4);
					
					// 1-2-1. 마지막 단계에서는 탐색 후 건물 높이 추가,삭제 필요 없음
					if(i==total) {
						if(current>=one && current>=two && current>=left_one && current>=left_two) {
							int[] around = { left_one, left_two, one, two };
							Arrays.sort(around);
							sum += current - around[around.length-1];
						}
						break;
					}
					
					// 1-2-2. 현재 중간에 위치한 건물이 양옆에 다른 건물들 보다 제일 높이가 높은 경우
					if(current>=one && current>=two && current>=left_one && current>=left_two) {
						// 양 옆중 가장 높은 높이를 현재에서 빼주면 그게 조망권 확보된 갯수
						int[] around = { left_one, left_two, one, two };
						Arrays.sort(around);
						sum += current - around[around.length-1];
						
						// 1-2-2-1. 남은 건물의 갯수가 3개이상이면, 3칸 앞으로 가서 다음 탐색 시작
						//  (현재 중간에서 오른쪽 두번째까지의 건물은 조망권 확보가 안되므로 넘어가기)
						if(total-i>3) {
							for(int cnt=0; cnt<3; ++cnt) {
								queue.remove();
								queue.add(sc.nextInt());
							}
							i+=3;
						}
						// 1-2-2-2. 남은 건물이 3개가 안되면 그냥 1칸씩 옮기기
						else {
							queue.remove();
							queue.add(sc.nextInt());
							++i;
						}
						
					}
					// 1-2-3. 중간 건물이 최대 높이가 아니면 그냥 1칸씩 옮기기
					else {
						queue.remove();
						queue.add(sc.nextInt());
						++i;
					}
					
				}
				
			}
			// 2. 결과 출력
			System.out.printf("#%d %d%n ",test_case, sum);
			
		}

	}
	

}
