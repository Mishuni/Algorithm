import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sw2477 {
	
	// 1. 손님의 상태를 저장할 클래스 정의
	public static class Customer implements Comparable<Customer>{

		int number;
		int mediate;
		int recep;
		
		Customer(int n,int mediate, int recep){
			this.number = n;
			this.mediate = mediate;
			this.recep = recep;
		}
		
		// 1-2. reception 이후에 순서 정리를 위한 함수
		@Override
		public int compareTo(Customer o) {
			
			// 1-2-1. reception 끝난 시간이 같은 경우 창구 번호순으로 정렬
			if(this.mediate==o.mediate) {
				return Integer.compare(this.recep,o.recep);
			}
			// 1-2-2. 끝난 시간에 따라 오름차순 정렬
			return Integer.compare(this.mediate, o.mediate);
		}
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("./src/2477.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			// 2. Initialize : 초기화
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			int rct = sc.nextInt(); // reception target
			int rpt = sc.nextInt(); // repair target
			int sum = 0;
			Customer[] people = new Customer[K]; 
			
			// 각 창구의 처리 시간 저장
			int[] receptions = input(N,sc);
			int[] repairs = input(M,sc);
			
			// 각 창구의 처리 중인 가장 마지막 시간을 저장하는 곳
			int[] max_recep = new int[N+1];
			int[] max_repair = new int[M+1];
			
			// 3. 사람들의 도착 시간 정보를 순서대로 받고 
			//    그에 따른 reception 끝나는 시간 계산
			for(int i=0; i<K; ++i) {
				int start = sc.nextInt();
				int min = Integer.MAX_VALUE;
				int min_pos = 1 ;
				
				// 3-1. 각 창구를 순서대로 체크
				for(int r=1; r<=N; ++r) {
					
					// 3-1-1. 처리 끝나는 시간이 시작 시간보다 작은 경우
					// 그곳에서 reception 처리하고 끝나는 시간으로 customer 저장 하고 체크 종료
					if(max_recep[r]<=start) {
						max_recep[r]=start+receptions[r];
						people[i]= new Customer(i+1,max_recep[r],r);
						break;
					}
					
					// 3-1-2. 모든 창구를 바로 못가는 경우를 대비해
					// 그 중에서 가장 빨리 끝나는 창구 찾기
					if(max_recep[r]<min) {
						min_pos = r;
						min = max_recep[r];
					}
					
					// 3-1-3. 모든 창구를 바로 못가고 기다려야 하는 경우
					// 가장 빨리 처리가 끝나는 창구에서 reception 처리하고 customer 저장
					if(r==N){
						max_recep[min_pos] = min + receptions[min_pos]; 
						people[i]= new Customer(i+1,max_recep[min_pos],min_pos);
					}
				}
			}
			
			// 4. Sort people
			// 위 클래스에서 지정한 비교 방식대로 손님 재정렬
			Arrays.sort(people);
			
			// 5. 정렬된 순서로 repair 창구 처리 시작
			for(int i=0; i<K; ++i) {
				Customer current = people[i];
				int start = current.mediate;
				int min = Integer.MAX_VALUE;
				int min_pos = 1 ;
				
				// 5-1. 각 창구를 순서대로 체크
				for(int r=1; r<=M; ++r) {
					
					// 5-1-1. 처리 끝나는 시간이 시작 시간보다 작은 경우
					// 그곳에서 repair 처리 하고 종료
					// 대신, customer의 reception 창구와 현재 repair 창구가 목표 창구들과 같다면
					// 결과 값에 해당 손님 번호를 더하기
					if(max_repair[r]<=start) {
						max_repair[r]=start+repairs[r];
						if(current.recep==rct && r==rpt) {
							sum+=current.number;
						}
						break;
					}
					
					// 5-1-2. 모든 창구를 바로 못가는 경우를 대비해
					// 그 중에서 가장 빨리 끝나는 창구 찾기
					if(max_repair[r]<min) {
						min_pos = r;
						min = max_repair[r];
					}
					
					// 5-2-3. 모든 창구를 바로 못가고 기다려야 하는 경우
					// 가장 빨리 처리가 끝나는 창구에서 repair 처리하고
					// customer의 reception 창구와 현재 repair 창구가 목표 창구들과 같다면
					// 결과 값에 해당 손님 번호를 더하기
					if(r==M){
						max_repair[min_pos] = min + repairs[min_pos]; 
						if(current.recep==rct && min_pos==rpt) {
							sum+=current.number;
						}
					}
				}
			}
			
			// 6. sum에 더해진 값이 없으면 -1, 아니면 결과값 출력
			sum = (sum==0)? -1: sum;
			System.out.printf("#%d %d%n",test_case,sum);
			
			
		}

	}
	
	// 창구 처리 시간 입력받는 함수
	public static int[] input(int num, Scanner sc) {
		int[] list = new int[num+1];
		for(int i=1; i<=num; ++i) {
			list[i] = sc.nextInt();
		}
		return list;
	}

}
