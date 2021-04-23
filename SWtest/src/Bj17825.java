import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bj17825 {

	// look - up tabel
	static final int[][] lookup = {
			// value , 1 step , ~ , 5 step
			{ 0, 1, 2, 3, 4, 5}, // 0
			{ 2, 2, 3, 4, 5, 6}, // 1
			{ 4, 3, 4, 5, 6, 7}, // 2
			{ 6, 4, 5, 6, 7, 8}, // 3
			{ 8, 5, 6, 7, 8, 9}, // 4
			{10,21,22,23,24,30}, // 5 (10)
			{12, 7, 8, 9,10,11},
			{14, 8, 9,10,11,12},
			{16, 9,10,11,12,13},
			{18,10,11,12,13,14},
			{20,28,29,24,30,31}, // 10 (20)
			{22,12,13,14,15,16},
			{24,13,14,15,16,17},
			{26,14,15,16,17,18},
			{28,15,16,17,18,19},
			{30,27,26,25,24,30}, // 15 (30)
			{32,17,18,19,20,32},
			{34,18,19,20,32,32},
			{36,19,20,32,32,32},
			{38,20,32,32,32,32},
			{40,32,32,32,32,32}, // 20 (40)
			{13,22,23,24,30,31},
			{16,23,24,30,31,20},
			{19,24,30,31,20,32},
			{25,30,31,20,32,32}, // 24 (25)
			{26,24,30,31,20,32},
			{27,25,24,30,31,20},
			{28,26,25,24,30,31},
			{22,29,24,30,31,20},
			{24,24,30,31,20,32},
			{30,31,20,32,32,32},
			{35,20,32,32,32,32},
			{0, 32,32,32,32,32}
	};
	
	static int max_result ; 
	
	static int move(int status) {
		int result = 0;
		int[] pos = new int[4];
		
		for(int d=0; d<10; ++d) {
			// 1 turn
			int dice = dices[d];
			int mal = (status>>2*d) & (3);
			int cur = pos[mal]; // mal's position
			
			// if this mal arrived finish point
			if(cur==32) {
				return -1;
			}
			
			// find next position
			int next = lookup[cur][dice];
			
			// if it will arrive the finish point
			if(next==32) {
				pos[mal] = 32;
				continue;
			}
			
			// check next possible
			for(int i=0; i<4; ++i) {
				if(i==mal) continue;
				// can go
				if(pos[i]==next) {
					return -1;
				}
			}
			
			pos[mal] = next;
			result += lookup[next][0];
		}
		
		
		return result;
	}
	
	static int[] dices = new int[10];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		// 입력 받기
		for(int i=0; i<10; ++i) {
			
			dices[i] = Integer.parseInt(st.nextToken());
			
		}
		max_result = 0;
		// (1<<20) 까지 ? dfs
		for(int i=0; i<(1<<20); ++i) {
			
			int status = i;
			int ret = move(status);
			
			if(ret>max_result) {
				max_result = ret;
			}
			
		}
		
		// 결과 출력
		System.out.println(max_result);
		
	}


}
