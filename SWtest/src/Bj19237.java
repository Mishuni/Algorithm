import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.ArrayList;


class Shark  {
	
	int num;
	int y,x;
	int direc;
	int[][] priority;
	
	Shark(int n, int y, int x){
		this.num = n;
		this.y = y; this.x = x;
		this.priority = new int[4][4];
	}
	
}

public class Bj19237 {
	
	public static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int N,M,K, alive, cnt;
	public static int[][] map;
	public static Shark[] sharks;
	public static boolean[] dead;

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. init
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		alive = M;
		cnt = 0 ;
		
		map = new int[N][N];
		sharks = new Shark[M+1];
		dead = new boolean[M+1];
		// set sharks
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; ++j) {
				int tmp = stoi(st.nextToken());
				map[i][j] = tmp;
				sharks[tmp] = new Shark(tmp,i,j);
			}
		}
		// set direc
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; ++i) {
			int tmp = stoi(st.nextToken());
			sharks[i].direc = tmp-1;
		}
		// set prior
		for(int i=1; i<=M; ++i) {
			for(int j=0; j<4; ++j) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<4; ++c) {
					sharks[i].priority[j][c]=stoi(st.nextToken())-1;
				}
			}
		}
		
		while(alive!=1) {
			++cnt;
			
			// move
			
			
			// remvoe duplicate
			
			// remove smell
			
			
			// test
			alive--;
			
		}
		
		
		System.out.println(cnt);
		
	}

}
