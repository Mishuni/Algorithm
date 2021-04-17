import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Bj14890 {
	public static int N,L,cnt, check, path;
	public static int[][] map;
	public static boolean possible;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		L = stoi(st.nextToken());
		
		map = new int[N][N];
		cnt = 0;
		
		for(int i=0; i<N; ++i) {
			check = 0;
			path = 0;
			possible = true;
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; ++j) {
				int tmp  = stoi(st.nextToken());
				map[i][j]= tmp; 
				// first
				if(j==0) {
					path=1; continue;
				}
				int pre = map[i][j-1];
				if(possible) {
					checkPossible(pre,tmp,j);
				}
			}
			if(possible && check==0) {
				++cnt;
			}
		}
		
		for(int i=0; i<N; ++i) {
			check = 0;
			path = 0;
			possible = true;
			
			for(int j=0; j<N; ++j) {
				int tmp = map[j][i];
				// first
				if(j==0) {
					path=1; continue;
				}
				int pre = map[j-1][i];
				if(possible) {
					checkPossible(pre,tmp,j);
				}
			}
			if(possible && check==0) {
				++cnt;
			}
		}
		
		System.out.println(cnt);
		
	}
	
	public static void checkPossible ( int pre, int tmp, int j ) {
		if(check>0) {
			if(pre!=tmp) {
				possible = false;
			}
			else {
				--check;
			}
		}
		else {
					
			if(pre==tmp) {
				++path;
			}
			else if(pre-tmp==1) {
				check = L - 1;
				path = 0;
			}
			else if(pre-tmp==-1) {
				if(path>=L) {
					path = 1;
				}
				else {
					possible =false;
				}
			}
			else {
				possible = false;
			}
		}
	}
}
