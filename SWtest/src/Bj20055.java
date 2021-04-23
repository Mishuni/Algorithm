import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;

public class Bj20055 {
	static int N,K;
	static int result, zero_cnt;
	static int[] belt;
	static boolean[] robot;
	
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static void move() {
		
		// final down
		robot[N-1] = false;
				
		// belt move 1 step
		int last = belt[2*N-1];
		for(int i=2*N-1; i>0; --i) {
			belt[i] = belt[i-1];
		}
		belt[0] = last;
		
		// robot move 1 step to final
		if(robot[N-2]) {
			// after down
			robot[N-2] = false;
			
		}
		// move other robots
		for(int i=N-3; i>=0; --i) {
			if(robot[i]) {
				// if can step one more
				if(!robot[i+2]&& belt[i+2]>0) {
					// if not final turn true;
					if(i!=N-3) {
						robot[i+2]=true;
					}
					belt[i+2]--;
					if(belt[i+2]==0) {
						++zero_cnt;
					//robot[i] = false;
					}
				}
				else {
					robot[i+1] = true;
				}
				
				// turn false
				robot[i] = false;
			}
		
			
		}
		
		// put new one it can
		if(belt[0]>0) {
			robot[0] = true;
			belt[0]--;
			if(belt[0]==0) {
				++zero_cnt;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		belt = new int[2*N];
		robot = new boolean[N];
		result = 0;
		zero_cnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<2*N; ++i) {
			belt[i] = stoi(st.nextToken());
		}
		
		while(zero_cnt<K) {
			++result;
			
			move();
			
		}
		
		System.out.println(result);
	}
}
