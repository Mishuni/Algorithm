
import java.util.Scanner;
import java.io.FileInputStream;

public class Sw2117
{
    static int N,M;
    static int[][] map;
    static int max_cnt;
    
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("./src/2117.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T;
		T=sc.nextInt();
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			M = sc.nextInt();
            map = new int[N][N];
            max_cnt = 0;
            
            for(int i=0; i<N; ++i) {
            	for(int j=0; j<N; ++j) {
            		map[i][j] = sc.nextInt();
            	}
            }
            
            int limit = (N+1);
            for(int k=limit; k>0; --k) {
            	
            	int peak = 2*k -1;
            	int cost = k*k+(k-1)*(k-1);
            	
            	for(int r=0; r<N; ++r) {
            		for(int c=0; c<N; ++c) {
            			int cnt = 0;
            			for(int i=1; i<=peak; i=i+2) {
                    		// center : r , c
            				// diff = (peak - i)/2
            				// startr = r - diff , r+diff
            				// startc = c - i/2   ~ i
            				int diff = (peak-i)/2;
            				int starty = r -diff;
            				int starty_bottom = r +diff;
            				int startx = c - (i/2);
            				for(int step=0; step<i; ++step) {
            					int nextx = startx+step;
            					// top
            					if(starty>=0 && starty<N && nextx<N && nextx>=0) {
            						if(map[starty][nextx]==1) {
            							++cnt;
            						}
            					}
            					// bottom
            					if(i!=peak&&starty_bottom>=0 && starty_bottom<N && nextx<N && nextx>=0) {
            						if(map[starty_bottom][nextx]==1) {
            							++cnt;
            						}
            					}
            				}
                    	}
            			if(cnt>max_cnt) {
            				if(cost<=cnt*M) {
            					max_cnt = cnt;
            				}
            			}
            		}
            	}
            }
            
            System.out.printf("#%d %d%n",test_case,max_cnt);
		}
	}
}